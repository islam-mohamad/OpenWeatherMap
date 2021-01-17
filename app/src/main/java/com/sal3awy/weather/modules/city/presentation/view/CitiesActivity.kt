package com.sal3awy.weather.modules.city.presentation.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.sal3awy.weather.R
import com.sal3awy.weather.core.presentation.visible
import com.sal3awy.weather.modules.city.presentation.viewmodel.CitiesViewModel
import com.sal3awy.weather.modules.fivedaysforecasts.presentation.view.WeatherActivity
import com.sal3awy.weather.modules.fivedaysforecasts.presentation.view.adapter.CitiesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_cities.*
import kotlinx.android.synthetic.main.activity_weather.*

@AndroidEntryPoint
class CitiesActivity : AppCompatActivity() {
    private val viewModel: CitiesViewModel by viewModels()
    private val adapter by lazy { CitiesAdapter(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities)
        initViews()
        actions()
        viewModelObservers()
//        viewModel.search("")
    }

    private fun initViews() {
        searchET.addTextChangedListener {
            viewModel.search(it.toString())
        }
        citiesRV.layoutManager = LinearLayoutManager(this@CitiesActivity)
        citiesRV.addItemDecoration(
            DividerItemDecoration(
                this@CitiesActivity,
                LinearLayoutManager.VERTICAL
            )
        )
        citiesRV.adapter = adapter

    }

    private fun actions() {
        adapter.onPositiveActionClicked = {
            WeatherActivity.startInstance(this, it.id)
        }
    }

    private fun viewModelObservers() {
        viewModel.isLoadingLiveData.observe(this, ::setLoading)
        viewModel.citiesListLiveData.observe(this) { adapter.swap(it) }
        viewModel.errorLiveData.observe(this, ::showError)
    }

    private fun showError(stringRes: Int) {
        Snackbar.make(weatherParentView, stringRes, Snackbar.LENGTH_SHORT).show()
    }

    private fun setLoading(isLoading: Boolean) {
        citiesProgress.visible(isLoading)
    }
}