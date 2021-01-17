package com.sal3awy.weather.city.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sal3awy.weather.city.cities
import com.sal3awy.weather.city.sohagCityEntity
import com.sal3awy.weather.core.mock
import com.sal3awy.weather.modules.city.domain.interactor.SearchCityInteractor
import com.sal3awy.weather.modules.city.domain.repository.CityRepository
import com.sal3awy.weather.core.whenever
import io.reactivex.Single
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class SearchCityInteractorTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val cityRepository = mock<CityRepository>()

    private val searchCityUseCases by lazy { SearchCityInteractor(cityRepository) }

    @Test
    fun testSearchCityUseCases_Completed() {
        whenever(cityRepository.getCities())
            .thenReturn(Single.just(emptyList()))

        searchCityUseCases.build("")
            .test()
            .assertComplete()
    }

    @Test
    fun testCryptoListUseCases_Error() {
        val response = Throwable("Error response")
        whenever(cityRepository.getCities())
            .thenReturn(Single.error(response))

        searchCityUseCases.build("")
            .test()
            .assertError(response)

    }

    @Test
    fun testCryptoListUseCases_getCryptoList_response() {
        val response = cities()
        whenever(cityRepository.getCities())
            .thenReturn(Single.just(response))

//        val expectedList = arrayListOf(sohagCityEntity)

        searchCityUseCases.build("sohag")
            .test()
            .assertOf {
                assertTrue(it.values()[0].contains(sohagCityEntity))
            }
    }
}