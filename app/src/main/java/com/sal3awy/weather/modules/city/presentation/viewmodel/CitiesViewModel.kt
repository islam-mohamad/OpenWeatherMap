package com.sal3awy.weather.modules.city.presentation.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.sal3awy.weather.R
import com.sal3awy.weather.core.presentation.SingleLiveEvent
import com.sal3awy.weather.modules.city.domain.entity.CityEntity
import com.sal3awy.weather.modules.city.domain.usecases.SearchCityUseCase
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit
import javax.inject.Named

class CitiesViewModel @ViewModelInject constructor(
    private val searchCityUseCase: SearchCityUseCase,
    @Named("ioScheduler") private val ioScheduler: Scheduler,
    @Named("mainScheduler") private val mainScheduler: Scheduler,
    @Named("computationScheduler") private val computationScheduler: Scheduler
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val searchSubject = BehaviorSubject.createDefault("")
    val citiesListLiveData = SingleLiveEvent<List<CityEntity>>()
    val errorLiveData = SingleLiveEvent<Int>()
    val isLoadingLiveData = SingleLiveEvent<Boolean>()

    init {
        initSearch()
        isLoadingLiveData.postValue(true)
    }

    fun search(cityName: String) {
        isLoadingLiveData.postValue(true)
        searchSubject.onNext(cityName)
    }

    private fun initSearch() {
        searchSubject.debounce(300, TimeUnit.MILLISECONDS, computationScheduler)
            .distinctUntilChanged()
            .switchMapSingle {
                searchCityUseCase.build(it).onErrorReturn { emptyList() }
            }
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .doOnNext { isLoadingLiveData.postValue(false) }
            .subscribe(
                { citiesListLiveData.value = it },
                { errorLiveData.value = R.string.something_went_wrong })
            .addTo(compositeDisposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}