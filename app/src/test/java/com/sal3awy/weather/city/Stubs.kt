package com.sal3awy.weather.city

import com.sal3awy.weather.modules.city.domain.entity.CityEntity
import com.sal3awy.weather.modules.city.domain.entity.CoordinatesEntity

val sohagCityEntity = CityEntity(1, "sohag", "EG", CoordinatesEntity(1650.0, 445.0))
fun cities() = listOf(
    sohagCityEntity,
    CityEntity(1, "cairo", "EG", CoordinatesEntity(300.0, 500.0)),
    CityEntity(1, "london", "EN", CoordinatesEntity(400.0, 600.0))
)
