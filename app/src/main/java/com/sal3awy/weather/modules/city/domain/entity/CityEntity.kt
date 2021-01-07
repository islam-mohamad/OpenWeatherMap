package com.sal3awy.weather.modules.city.domain.entity

data class CityEntity(
    val id: Long,
    val name: String,
    val country: String,
    val coordinates: CoordinatesEntity
)

data class CoordinatesEntity(
    val latitude: Double,
    val longitude: Double
)