package com.sal3awy.weather.modules.fivedaysforecasts.di

import com.sal3awy.weather.modules.fivedaysforecasts.data.repository.ForecastsRepositoryImpl
import com.sal3awy.weather.modules.fivedaysforecasts.data.sources.ForeCastRemoteDS
import com.sal3awy.weather.modules.fivedaysforecasts.domain.interactor.GetFiveDaysForecastsInteractor
import com.sal3awy.weather.modules.fivedaysforecasts.domain.repository.ForecastsRepository
import com.sal3awy.weather.modules.fivedaysforecasts.domain.usecases.GetFiveDaysForecastsUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.Retrofit

@Module
@InstallIn(ActivityComponent::class)
abstract class FiveDaysForecastModule {

    @ActivityScoped
    @Binds
    abstract fun forecastsRepo(impl: ForecastsRepositoryImpl): ForecastsRepository

    @ActivityScoped
    @Binds
    abstract fun getFiveDaysForecastsUseCase(interactor: GetFiveDaysForecastsInteractor): GetFiveDaysForecastsUseCase

    companion object {
        @ActivityScoped
        @Provides
        fun foreCastRemoteDS(retrofit: Retrofit): ForeCastRemoteDS {
            return retrofit.create(ForeCastRemoteDS::class.java)
        }
    }
}