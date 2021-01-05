package com.sal3awy.weather.modules.fivedaysforecasts.presentation.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import com.sal3awy.weather.R
import com.sal3awy.weather.base.presentation.BaseViewModel
import com.sal3awy.weather.core.presentation.SingleLiveEvent
import com.sal3awy.weather.modules.fivedaysforecasts.domain.entity.ForecastEntity
import com.sal3awy.weather.modules.fivedaysforecasts.domain.interactor.GetFiveDaysForecastsUseCase
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.addTo
import javax.inject.Named

class ForeCastViewModel @ViewModelInject constructor(
    private val getFiveDaysForecastsUseCase: GetFiveDaysForecastsUseCase,
    @Named("ioScheduler") private val ioScheduler: Scheduler,
    @Named("mainScheduler") private val mainScheduler: Scheduler
) :
    BaseViewModel() {

    val isLoading =
        SingleLiveEvent<Boolean>()
    val errorRes = SingleLiveEvent<Int>()
    val citForecastsLiveData =
        SingleLiveEvent<List<ForecastEntity>>()

    fun getFiveDaysForecasts(id: Long) {
        getFiveDaysForecastsUseCase.build(id)
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .doOnSubscribe { isLoading.postValue(true) }
            .doOnTerminate { isLoading.postValue(false) }
            .subscribe(
                { citForecastsLiveData.value = it },
                { errorRes.value = R.string.get_forecast_error })
            .addTo(compositeDisposable)
    }
}
