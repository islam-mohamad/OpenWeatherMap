package com.sal3awy.weather.modules.city.domain.interactor

import com.sal3awy.weather.base.domian.SingleUseCase
import com.sal3awy.weather.modules.city.domain.entity.CityEntity
import com.sal3awy.weather.modules.city.domain.repository.CityRepository
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class SearchCityUseCase @Inject constructor(private val cityRepository: CityRepository) :
    SingleUseCase<String, List<CityEntity>>() {
    override fun build(params: String): Single<List<CityEntity>> {
        return cityRepository.getCities().flatMap {citiesList->
            Flowable.fromIterable(citiesList).filter {city->
                city.name.contains(params)
            }.toList()
        }
    }
}