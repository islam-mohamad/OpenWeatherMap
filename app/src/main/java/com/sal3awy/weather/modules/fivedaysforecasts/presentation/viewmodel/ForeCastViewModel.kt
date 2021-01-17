package com.sal3awy.weather.modules.fivedaysforecasts.presentation.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.sal3awy.weather.R
import com.sal3awy.weather.core.presentation.SingleLiveEvent
import com.sal3awy.weather.modules.fivedaysforecasts.domain.entity.ForecastEntity
import com.sal3awy.weather.modules.fivedaysforecasts.domain.interactor.GetFiveDaysForecastsInteractor
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Named

class ForeCastViewModel @ViewModelInject constructor(
    private val getFiveDaysForecastsInteractor: GetFiveDaysForecastsInteractor,
    @Named("ioScheduler") private val ioScheduler: Scheduler,
    @Named("mainScheduler") private val mainScheduler: Scheduler
) :
    ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val isLoading =
        SingleLiveEvent<Boolean>()
    val errorRes = SingleLiveEvent<Int>()
    val citForecastsLiveData =
        SingleLiveEvent<List<ForecastEntity>>()

    fun getFiveDaysForecasts(id: Long) {
        getFiveDaysForecastsInteractor.build(id)
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .doOnSubscribe { isLoading.postValue(true) }
            .doOnTerminate { isLoading.postValue(false) }
            .subscribe(
                { citForecastsLiveData.value = it },
                { errorRes.value = R.string.get_forecast_error })
            .addTo(compositeDisposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
