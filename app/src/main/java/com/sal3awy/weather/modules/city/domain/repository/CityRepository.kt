package com.sal3awy.weather.modules.city.domain.repository

import com.sal3awy.weather.modules.city.domain.entity.CityEntity
import io.reactivex.Single

interface CityRepository {
    fun getCities(): Single<List<CityEntity>>
}