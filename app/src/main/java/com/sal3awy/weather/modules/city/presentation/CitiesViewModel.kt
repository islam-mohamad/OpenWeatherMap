package com.sal3awy.weather.modules.city.presentation

import androidx.hilt.lifecycle.ViewModelInject
import com.sal3awy.weather.R
import com.sal3awy.weather.base.presentation.BaseViewModel
import com.sal3awy.weather.core.presentation.SingleLiveEvent
import com.sal3awy.weather.modules.city.domain.entity.CityEntity
import com.sal3awy.weather.modules.city.domain.interactor.SearchCityUseCase
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Named

class CitiesViewModel @ViewModelInject constructor(
    private val searchCityUseCase: SearchCityUseCase,
    @Named("ioScheduler") private val ioScheduler: Scheduler,
    @Named("mainScheduler") private val mainScheduler: Scheduler
) : BaseViewModel() {
    private val searchSubject = PublishSubject.create<String>()
    val citiesListLiveData = SingleLiveEvent<List<CityEntity>>()
    val errorLiveData = SingleLiveEvent<Int>()

    init {
        initSearch()
    }

    fun search(cityName: String) {
        searchSubject.onNext(cityName)
    }

    private fun initSearch() {
        searchSubject.debounce(300, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMapSingle {
                searchCityUseCase.build(it).onErrorReturn { emptyList() }
            }
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .subscribe(
                { citiesListLiveData.value = it },
                { errorLiveData.value = R.string.something_went_wrong })
            .addTo(compositeDisposable)
    }
}