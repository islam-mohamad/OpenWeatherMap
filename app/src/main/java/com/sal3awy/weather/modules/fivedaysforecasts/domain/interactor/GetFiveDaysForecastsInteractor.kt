package com.sal3awy.weather.modules.fivedaysforecasts.domain.interactor

import com.sal3awy.weather.BuildConfig
import com.sal3awy.weather.modules.fivedaysforecasts.domain.entity.ForecastEntity
import com.sal3awy.weather.modules.fivedaysforecasts.domain.repository.ForecastsRepository
import com.sal3awy.weather.modules.fivedaysforecasts.domain.usecases.GetFiveDaysForecastsUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetFiveDaysForecastsInteractor @Inject constructor(private val forecastsRepository: ForecastsRepository) :
    GetFiveDaysForecastsUseCase {
    override fun build(cityId: Long): Single<List<ForecastEntity>> {
        return forecastsRepository.requestFiveDaysForecasts(cityId).flattenAsFlowable { it }
            .map { foreCast ->
                foreCast.apply {
                    weather?.forEach { weather ->
                        weather.icon = "${BuildConfig.WEATHER_ICON_URL}${weather.icon}.png"
                    }
                }
            }.toList()
    }
}