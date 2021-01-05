package com.sal3awy.weather.modules.fivedaysforecasts.data.repository

import com.sal3awy.weather.BuildConfig
import com.sal3awy.weather.modules.fivedaysforecasts.data.model.mapper.toEntity
import com.sal3awy.weather.modules.fivedaysforecasts.data.sources.ForeCastRemoteDS
import com.sal3awy.weather.modules.fivedaysforecasts.domain.entity.ForecastEntity
import com.sal3awy.weather.modules.fivedaysforecasts.domain.repository.ForecastsRepository
import io.reactivex.Single
import javax.inject.Inject

class ForecastsRepositoryImpl @Inject constructor(private val remoteDS: ForeCastRemoteDS) :
    ForecastsRepository {
    override fun requestFiveDaysForecasts(id: Long): Single<List<ForecastEntity>> {
        return remoteDS.requestFiveDaysForecasts(id, BuildConfig.APPID)
            .map { response ->
                response.forecasts?.map { it.toEntity() } ?: emptyList()
            }
    }
}