package com.sal3awy.weather.modules.city.data.model.mapper

import com.sal3awy.weather.modules.city.domain.entity.CityEntity
import com.sal3awy.weather.modules.city.domain.entity.CoordinatesEntity
import com.sal3awy.weather.modules.city.data.model.CityModel
import com.sal3awy.weather.modules.city.data.model.CoordinatesModel

fun CityModel.toEntity() =
    CityEntity(
        id,
        name,
        country,
        coordinates.toEntity()
    )

fun CoordinatesModel.toEntity() =
    CoordinatesEntity(
        latitude,
        longitude
    )