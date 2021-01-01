package com.sal3awy.weather.modules.fivedaysforecasts.presentation.viewmodel

import com.sal3awy.weather.R
import com.sal3awy.weather.base.presentation.BaseViewModel
import com.sal3awy.weather.core.SingleLiveEvent
import com.sal3awy.weather.modules.fivedaysforecasts.di.get5DaysForecastsUseCase
import com.sal3awy.weather.modules.fivedaysforecasts.di.ioScheduler
import com.sal3awy.weather.modules.fivedaysforecasts.di.mainScheduler
import com.sal3awy.weather.modules.fivedaysforecasts.domain.entity.ForecastEntity
import com.sal3awy.weather.modules.fivedaysforecasts.domain.interactor.GetFiveDaysForecastsUseCase
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.addTo

class ForeCastViewModel(
    private val getFiveDaysForecastsUseCase: GetFiveDaysForecastsUseCase = get5DaysForecastsUseCase,
    private val io: Scheduler = ioScheduler,
    private val main: Scheduler = mainScheduler
) :
    BaseViewModel() {

    val isLoading = SingleLiveEvent<Boolean>()
    val errorRes = SingleLiveEvent<Int>()
    val citForecastsLiveData = SingleLiveEvent<List<ForecastEntity>>()

    fun getFiveDaysForecasts(id: Long) {
        getFiveDaysForecastsUseCase.build(id)
            .subscribeOn(io)
            .observeOn(main)
            .doOnSubscribe { isLoading.postValue(true) }
            .doOnTerminate { isLoading.postValue(false) }
            .subscribe(
                { citForecastsLiveData.value = it },
                { errorRes.value = R.string.get_forecast_error })
            .addTo(compositeDisposable)
    }
}