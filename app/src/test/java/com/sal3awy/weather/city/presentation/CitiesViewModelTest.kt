package com.sal3awy.weather.city.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.sal3awy.weather.city.sohagCityEntity
import com.sal3awy.weather.core.mock
import com.sal3awy.weather.modules.city.domain.entity.CityEntity
import com.sal3awy.weather.modules.city.domain.usecases.SearchCityUseCase
import com.sal3awy.weather.modules.city.presentation.viewmodel.CitiesViewModel
import com.sal3awy.weather.core.whenever
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*

class CitiesViewModelTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val searchCityUseCases =
        mock<SearchCityUseCase>()
    private val observerCities =
        mock<Observer<List<CityEntity>>>()

    private val viewModel by lazy {
        CitiesViewModel(
            searchCityUseCases,
            Schedulers.trampoline(),
            Schedulers.trampoline(),
            Schedulers.trampoline()
        )
    }

    @Before
    fun initTest() {
        reset(searchCityUseCases, observerCities)
    }

    @Test
    fun tes_search_returns_list_contains_the_keyword() {
        val response = listOf(sohagCityEntity)
        whenever(
            searchCityUseCases.build(
                ArgumentMatchers.anyString()
            )
        )
            .thenReturn(Single.just(response))
        viewModel.citiesListLiveData.observeForever(observerCities)

        viewModel.search("sohag")

        verify(searchCityUseCases).build("sohag")
        val argumentCaptor = ArgumentCaptor.forClass(List::class.java)
        argumentCaptor.run {
            verify(observerCities, times(2)).onChanged(capture() as List<CityEntity>?)
            assertTrue(value.contains(sohagCityEntity))
        }
    }


    @Test
    fun testSearchCity_completes_even_errors() {
        val errorMessage = "Error response"
        val response = Throwable(errorMessage)
        whenever(
            searchCityUseCases.build(
                ArgumentMatchers.anyString()
            )
        )
            .thenReturn(Single.error(response))
        viewModel.citiesListLiveData.observeForever(observerCities)

        viewModel.search("sohag")

        verify(searchCityUseCases).build("sohag")
        val argumentCaptor = ArgumentCaptor.forClass(List::class.java)
        argumentCaptor.run {
            verify(observerCities, times(2)).onChanged(capture() as List<CityEntity>?)
            assertEquals(value, emptyList<CityEntity>())
        }
    }
}