package com.sal3awy.weather.modules.fivedaysforecasts.data.model

import com.google.gson.annotations.SerializedName


data class ForecastsResponse(
    @SerializedName("code")
    val code: String?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("cnt")
    val count: Long?,
    @SerializedName("list")
    val forecasts: List<ForecastModel>?
)

data class ForecastModel(
    @SerializedName("clouds")
    val clouds: CloudsModel?,
    @SerializedName("dt")
    val date: Long?,
    @SerializedName("dt_txt")
    val dateText: String?,
    @SerializedName("main")
    val details: ForecastDetailsModel?,
    @SerializedName("snow")
    val snow: SnowModel?,
    @SerializedName("weather")
    val weather: List<WeatherModel>?,
    @SerializedName("wind")
    val wind: WindModel?
)

data class CloudsModel(
    @SerializedName("all")
    val cloudiness: Long?
)

data class ForecastDetailsModel(
    @SerializedName("grnd_level")
    val grandLevel: Double?,
    @SerializedName("humidity")
    val humidity: Long?,
    @SerializedName("pressure")
    val pressure: Double?,
    @SerializedName("sea_level")
    val seaLevel: Double?,
    @SerializedName("temp")
    val temperature: Double?,
    @SerializedName("temp_max")
    val maximumTemperature: Double?,
    @SerializedName("temp_min")
    val minimumTemperature: Double?
)

data class SnowModel(
    @SerializedName("3h")
    val volumeForLastThreeHours: Double?
)

data class WeatherModel(
    @SerializedName("description")
    val description: String?,
    @SerializedName("icon")
    val icon: String?,
    @SerializedName("id")
    val id: Long?,
    @SerializedName("main")
    val state: String?
)

data class WindModel(
    @SerializedName("deg")
    val degree: Double?,
    @SerializedName("speed")
    val speed: Double?
)