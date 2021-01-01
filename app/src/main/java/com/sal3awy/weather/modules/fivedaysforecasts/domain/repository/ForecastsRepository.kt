package com.sal3awy.weather.modules.fivedaysforecasts.domain.repository

import com.sal3awy.weather.modules.fivedaysforecasts.domain.entity.ForecastEntity
import io.reactivex.Single

interface ForecastsRepository {
    fun requestFiveDaysForecasts(id: Long): Single<List<ForecastEntity>>
}