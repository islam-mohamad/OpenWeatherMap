package com.sal3awy.weather.modules.city.domain.interactor

import android.util.Log
import com.sal3awy.weather.modules.city.domain.entity.CityEntity
import com.sal3awy.weather.modules.city.domain.repository.CityRepository
import com.sal3awy.weather.modules.city.domain.usecases.SearchCityUseCase
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class SearchCityInteractor @Inject constructor(private val cityRepository: CityRepository) :
    SearchCityUseCase{
    override fun build(cityName: String): Single<List<CityEntity>> {
        return cityRepository.getCities().flatMap {citiesList->
            Flowable.fromIterable(citiesList).filter {city->
                city.name.contains(cityName)
            }.toList()
        }
    }
}
