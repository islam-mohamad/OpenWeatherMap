package com.sal3awy.weather.modules.fivedaysforecasts.domain.entity

data class ForecastEntity(
    val clouds: CloudsEntity?,
    val date: Long?,
    val dateText: String?,
    val details: ForecastDetailsEntity?,
    val snow: SnowEntity?,
    val weather: List<WeatherEntity>?,
    val wind: WindEntity?
)

data class CloudsEntity(
    val cloudiness: Long?
)

data class ForecastDetailsEntity(
    val grandLevel: Double?,
    val humidity: Long?,
    val pressure: Double?,
    val seaLevel: Double?,
    val temperature: Double?,
    val maximumTemperature: Double?,
    val minimumTemperature: Double?
)

data class SnowEntity(
    val volumeForLastThreeHours: Double?
)

data class WeatherEntity(
    val description: String?,
    var icon: String?,
    val id: Long?,
    val state: String?
)

data class WindEntity(
    val degree: Double?,
    val speed: Double?
)