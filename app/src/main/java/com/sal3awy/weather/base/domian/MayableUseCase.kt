package com.sal3awy.weather.base.domian

import io.reactivex.Maybe


abstract class MayableUseCase<in Params, Type> where Type : Any {

    abstract fun build(params: Params): Maybe<Type>
}