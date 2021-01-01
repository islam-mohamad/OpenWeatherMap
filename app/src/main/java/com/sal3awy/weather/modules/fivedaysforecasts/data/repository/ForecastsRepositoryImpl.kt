package com.sal3awy.weather.modules.fivedaysforecasts.data.repository

import com.sal3awy.weather.BuildConfig
import com.sal3awy.weather.modules.fivedaysforecasts.data.model.mapper.toEntity
import com.sal3awy.weather.modules.fivedaysforecasts.data.sources.ForeCastRemoteDS
import com.sal3awy.weather.modules.fivedaysforecasts.di.foreCastRemoteDS
import com.sal3awy.weather.modules.fivedaysforecasts.domain.entity.ForecastEntity
import com.sal3awy.weather.modules.fivedaysforecasts.domain.repository.ForecastsRepository
import io.reactivex.Single

class ForecastsRepositoryImpl(private val remoteDS: ForeCastRemoteDS = foreCastRemoteDS) :
    ForecastsRepository {
    override fun requestFiveDaysForecasts(id: Long): Single<List<ForecastEntity>> {
        return remoteDS.requestFiveDaysForecasts(id, BuildConfig.APPID)
            .map { response ->
                response.forecasts?.map { it.toEntity() } ?: emptyList()
            }
    }
}