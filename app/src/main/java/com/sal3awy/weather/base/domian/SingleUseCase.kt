package com.sal3awy.weather.base.domian

import io.reactivex.Single

/**
 * Created by Youssef Ebrahim on 9/26/18.
 */
abstract class SingleUseCase<in Params, Type> where Type : Any {

    abstract fun build(params: Params): Single<Type>
}