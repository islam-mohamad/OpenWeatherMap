package com.sal3awy.weather.modules.fivedaysforecasts.di

import com.sal3awy.weather.BuildConfig.BASE_URL
import com.sal3awy.weather.modules.fivedaysforecasts.data.repository.ForecastsRepositoryImpl
import com.sal3awy.weather.modules.fivedaysforecasts.data.sources.ForeCastRemoteDS
import com.sal3awy.weather.modules.fivedaysforecasts.domain.interactor.GetFiveDaysForecastsUseCase
import com.sal3awy.weather.modules.fivedaysforecasts.domain.repository.ForecastsRepository
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


val retrofitClient: Retrofit by lazy {
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}

val okHttpClient: OkHttpClient by lazy {
    OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()
}

val httpLoggingInterceptor by lazy {
    HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}

val ioScheduler: Scheduler by lazy {
    Schedulers.io()
}

val mainScheduler: Scheduler by lazy {
    AndroidSchedulers.mainThread()
}

val foreCastRemoteDS: ForeCastRemoteDS by lazy {
    retrofitClient.create(ForeCastRemoteDS::class.java)
}

val forecastsRepo: ForecastsRepository by lazy {
    ForecastsRepositoryImpl()
}

val get5DaysForecastsUseCase: GetFiveDaysForecastsUseCase by lazy {
    GetFiveDaysForecastsUseCase()
}