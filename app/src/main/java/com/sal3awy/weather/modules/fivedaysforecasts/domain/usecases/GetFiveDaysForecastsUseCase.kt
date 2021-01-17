package com.sal3awy.weather.modules.fivedaysforecasts.domain.usecases

import com.sal3awy.weather.modules.fivedaysforecasts.domain.entity.ForecastEntity
import io.reactivex.Single

interface GetFiveDaysForecastsUseCase {
    fun build(cityId: Long): Single<List<ForecastEntity>>
}