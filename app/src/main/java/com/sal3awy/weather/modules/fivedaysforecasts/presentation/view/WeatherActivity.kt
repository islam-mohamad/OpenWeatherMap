package com.sal3awy.weather.modules.fivedaysforecasts.presentation.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.google.android.material.snackbar.Snackbar
import com.sal3awy.weather.R
import com.sal3awy.weather.core.presentation.visible
import com.sal3awy.weather.modules.fivedaysforecasts.domain.entity.ForecastEntity
import com.sal3awy.weather.modules.fivedaysforecasts.presentation.viewmodel.ForeCastViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_weather.*

private const val TAG = "WeatherActivity"

@AndroidEntryPoint
class WeatherActivity : AppCompatActivity() {

    private val viewModel: ForeCastViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        val cityId  = intent?.getLongExtra(CITY_ID__PARAM, 0)
        cityId?.let {
            viewModel.getFiveDaysForecasts(it)
            viewModelObservers()
        }?:run {
            Toast.makeText(this, "Invalid City Id", Toast.LENGTH_SHORT).show()
            finish()
        }
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

    companion object {
        private const val CITY_ID__PARAM = "CITY_ID__PARAM"
        fun startInstance(activity: Activity, params: Long) {
            val intent = Intent(activity, WeatherActivity::class.java)
            intent.putExtra(CITY_ID__PARAM, params)
            activity.startActivity(intent)
        }
    }
}