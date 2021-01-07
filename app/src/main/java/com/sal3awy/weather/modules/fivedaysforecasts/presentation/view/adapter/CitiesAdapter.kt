package com.sal3awy.weather.modules.fivedaysforecasts.presentation.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sal3awy.weather.R
import com.sal3awy.weather.modules.city.domain.entity.CityEntity
import kotlinx.android.synthetic.main.item_city.view.*

class CitiesAdapter constructor(context: Context) :
    RecyclerView.Adapter<CitiesAdapter.CitiesViewHolder>() {
    var onPositiveActionClicked: ((CityEntity) -> Unit)? = null
    var CitiesList = listOf<CityEntity>()
        private set

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
        return CitiesViewHolder(view)
    }

    override fun getItemId(position: Int): Long {
        return CitiesList[position].id
    }


    override fun getItemCount(): Int = CitiesList.size

    override fun onBindViewHolder(holder: CitiesViewHolder, position: Int) {
        val videoUIModel = CitiesList[position]
        holder.bindCity(videoUIModel, onPositiveActionClicked)
    }

    fun swap(list: List<CityEntity>) {
        CitiesList = list
        notifyDataSetChanged()
    }

    class CitiesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindCity(
            item: CityEntity,
            onActionClicked: ((CityEntity) -> Unit)? = null
        ) = with(item) {
            with(view) {
                cityNameTV.text = name
                cityCountryTV.text = country
                setOnClickListener { onActionClicked?.invoke(item) }
            }
        }
    }

}