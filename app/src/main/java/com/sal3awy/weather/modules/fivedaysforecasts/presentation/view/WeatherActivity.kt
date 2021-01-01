package com.sal3awy.weather.modules.fivedaysforecasts.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.google.android.material.snackbar.Snackbar
import com.sal3awy.weather.R
import com.sal3awy.weather.core.visible
import com.sal3awy.weather.modules.fivedaysforecasts.domain.entity.ForecastEntity
import com.sal3awy.weather.modules.fivedaysforecasts.presentation.viewmodel.ForeCastViewModel
import kotlinx.android.synthetic.main.activity_weather.*
import kotlin.math.log

private const val TAG = "WeatherActivity"

class WeatherActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this).get(ForeCastViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        viewModel.getFiveDaysForecasts(347796)
        viewModelObservers()
    }

    private fun viewModelObservers() {
        viewModel.isLoading.observe(this, ::setLoading)
        viewModel.errorRes.observe(this, ::showError)
        viewModel.citForecastsLiveData.observe(this, ::bindForeCast)
    }

    private fun setLoading(isLoading: Boolean) {
        wheatherProgress.visible(isLoading)
    }

    private fun showError(stringRes: Int) {
        Snackbar.make(weatherParentView, stringRes, Snackbar.LENGTH_SHORT).show()
    }

    private fun bindForeCast(forecastsList: List<ForecastEntity>) {
        var listLog = ""
        forecastsList.forEach { listLog += "\n$it" }
        Log.d(TAG, "cityForecast: $listLog")
        forecastTV.text = listLog
    }
}