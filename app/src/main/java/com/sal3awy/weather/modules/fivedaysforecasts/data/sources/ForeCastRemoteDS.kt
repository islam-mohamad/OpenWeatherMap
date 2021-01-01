package com.sal3awy.weather.modules.fivedaysforecasts.data.sources

import com.sal3awy.weather.modules.fivedaysforecasts.data.model.ForecastsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ForeCastRemoteDS {
    @GET("/data/2.5/forecast")
    fun requestFiveDaysForecasts(
        @Query("id") id: Long,
        @Query("APPID") apiKey: String
    ): Single<ForecastsResponse>
}