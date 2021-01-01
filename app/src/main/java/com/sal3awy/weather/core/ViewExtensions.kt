package com.sal3awy.weather.core

import android.view.View

fun View.visible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}