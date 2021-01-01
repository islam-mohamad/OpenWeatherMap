package com.sal3awy.weather.modules.fivedaysforecasts.data.model.mapper

import com.sal3awy.weather.modules.fivedaysforecasts.data.model.*
import com.sal3awy.weather.modules.fivedaysforecasts.domain.entity.*

fun ForecastModel.toEntity() =
    ForecastEntity(
        clouds?.toEntity(), date, dateText, details?.toEntity(),
        snow?.toEntity(), weather?.map { it.toEntity() }, wind?.toEntity()
    )

fun CloudsModel.toEntity() = CloudsEntity(cloudiness)

fun ForecastDetailsModel.toEntity() = ForecastDetailsEntity(
    grandLevel,
    humidity,
    pressure,
    seaLevel,
    temperature,
    maximumTemperature,
    minimumTemperature
)

fun SnowModel.toEntity() = SnowEntity(volumeForLastThreeHours)

fun WeatherModel.toEntity() = WeatherEntity(description, icon, id, state)

fun WindModel.toEntity() = WindEntity(degree, speed)