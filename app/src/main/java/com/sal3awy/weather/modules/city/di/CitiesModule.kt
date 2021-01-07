package com.sal3awy.weather.modules.city.di

import android.content.Context
import com.sal3awy.weather.modules.city.data.repository.CityRepositoryImpl
import com.sal3awy.weather.modules.city.data.sources.CitiesLocalDS
import com.sal3awy.weather.modules.city.domain.repository.CityRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
abstract class CitiesModule {

    @ActivityScoped
    @Binds
    abstract fun cityRepo(impl: CityRepositoryImpl): CityRepository

    companion object {
        @ActivityScoped
        @Provides
        fun citiesLocalDS(@ActivityContext context: Context): CitiesLocalDS = CitiesLocalDS(context)
    }
}