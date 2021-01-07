package com.sal3awy.weather.modules.city.data.sources

import android.content.Context
import com.google.gson.Gson
import com.sal3awy.weather.core.data.loadJSONFromAsset
import com.sal3awy.weather.modules.city.data.model.CityModel
import io.reactivex.Single
import java.io.InputStream
import javax.inject.Inject

class CitiesLocalDS @Inject constructor(private val context: Context) {
    private var cityList: List<CityModel>? = null
    fun getCities(): Single<List<CityModel>> {
        return Single.create { emitter ->
            if (cityList.isNullOrEmpty()) {
                try {
                    val inputStream: InputStream = context.assets.open("city_list.json")
                    val json = loadJSONFromAsset(inputStream)
                    cityList = Gson().fromJson(json, Array<CityModel>::class.java).toList()
                    emitter.onSuccess(cityList ?: emptyList())
                } catch (error: Exception) {
                    emitter.tryOnError(error)
                }
            } else {
                emitter.onSuccess(cityList ?: emptyList())
            }
        }
    }
}