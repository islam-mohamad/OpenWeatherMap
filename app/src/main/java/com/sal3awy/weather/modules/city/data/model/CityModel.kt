package com.sal3awy.weather.modules.city.data.model

import com.google.gson.annotations.SerializedName

data class CityModel(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("coord")
    val coordinates: CoordinatesModel
)

data class CoordinatesModel(
    @SerializedName("lat")
    val latitude: Double,
    @SerializedName("lon")
    val longitude: Double
)