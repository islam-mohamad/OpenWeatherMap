package com.sal3awy.weather.modules.fivedaysforecasts.domain.interactor

import com.sal3awy.weather.BuildConfig
import com.sal3awy.weather.base.domian.SingleUseCase
import com.sal3awy.weather.modules.fivedaysforecasts.domain.entity.ForecastEntity
import com.sal3awy.weather.modules.fivedaysforecasts.domain.repository.ForecastsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetFiveDaysForecastsUseCase @Inject constructor(private val forecastsRepository: ForecastsRepository) :
    SingleUseCase<Long, List<ForecastEntity>>() {
    override fun build(params: Long): Single<List<ForecastEntity>> {
        return forecastsRepository.requestFiveDaysForecasts(params).flattenAsFlowable { it }
            .map { foreCast ->
                foreCast.apply {
                    weather?.forEach { weather ->
                        weather.icon = "${BuildConfig.WEATHER_ICON_URL}${weather.icon}.png"
                    }
                }
            }.toList()
    }
}