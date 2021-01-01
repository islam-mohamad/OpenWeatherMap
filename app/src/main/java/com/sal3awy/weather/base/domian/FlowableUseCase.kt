package com.sal3awy.weather.base.domian

import io.reactivex.Flowable

abstract class FlowableUseCase<in Params, Type> where Type : Any {

    abstract fun build(params: Params): Flowable<Type>
}