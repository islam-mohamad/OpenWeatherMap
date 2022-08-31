package com.sal3awy.weather.base.domian

import io.reactivex.Completable


abstract class CompletableUseCase<in Params> {
    abstract fun build(params: Params): Completable
}