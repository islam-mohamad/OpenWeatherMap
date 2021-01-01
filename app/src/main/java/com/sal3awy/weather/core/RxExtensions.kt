package com.sal3awy.weather.core

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Disposable?.addTo(composite: CompositeDisposable) {
    if (this != null)
        composite.add(this)
}