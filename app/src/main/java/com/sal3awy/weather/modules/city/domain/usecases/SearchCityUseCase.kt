package com.sal3awy.weather.modules.city.domain.usecases

import com.sal3awy.weather.modules.city.domain.entity.CityEntity
import io.reactivex.Single

interface SearchCityUseCase {
    fun build(cityName: String) : Single<List<CityEntity>>
}