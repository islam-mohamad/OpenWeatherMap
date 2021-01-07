package com.sal3awy.weather.modules.city.data.repository

import com.sal3awy.weather.modules.city.domain.entity.CityEntity
import com.sal3awy.weather.modules.city.domain.repository.CityRepository
import com.sal3awy.weather.modules.city.data.sources.CitiesLocalDS
import com.sal3awy.weather.modules.city.data.model.mapper.toEntity
import io.reactivex.Single
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(private val citiesLocalDS: CitiesLocalDS) :
    CityRepository {
    override fun getCities(): Single<List<CityEntity>> {
        return citiesLocalDS.getCities().map { citiesList ->
            citiesList.map { it.toEntity() }
        }
    }
}