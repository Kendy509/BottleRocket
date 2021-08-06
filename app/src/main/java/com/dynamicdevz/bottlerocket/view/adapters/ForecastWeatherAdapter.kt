package com.dynamicdevz.bottlerocket.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dynamicdevz.bottlerocket.R
import com.dynamicdevz.bottlerocket.databinding.CityForecastLayoutBinding
import com.dynamicdevz.bottlerocket.model.HourlyWeather
import com.dynamicdevz.bottlerocket.util.processWeatherType

class ForecastWeatherAdapter(var dataset: List<HourlyWeather>):
    RecyclerView.Adapter<ForecastWeatherAdapter.ForecastWeatherViewHolder>(){

        class ForecastWeatherViewHolder(private val binding: CityForecastLayoutBinding):
            RecyclerView.ViewHolder(binding.root){
            fun onBind(dataitem: HourlyWeather){
                binding.temp.text = dataitem.temperature.toString()
                binding.wind.text = dataitem.windSpeed.toString()
                binding.humidity.text = binding.root.context.getString(R.string.forecast_chance_rain_ph,
                    dataitem.humidity.times(100))
                binding.chanceOfRain.text = binding.root.context.getString(R.string.forecast_chance_rain_ph,
                    dataitem.rainChance)
                binding.time.text = if (dataitem.hour in 1..12)
                    binding.root.context.getString(R.string.forecast_hour_am_ph, dataitem.hour)
                else binding.root.context.getString(R.string.forecast_hour_pm_ph, dataitem.hour)
                binding.weatherIcon.processWeatherType(dataitem.weatherType)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastWeatherViewHolder {
        return ForecastWeatherViewHolder(
            CityForecastLayoutBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false)
        )
    }

    override fun onBindViewHolder(holder: ForecastWeatherViewHolder, position: Int) {
        holder.onBind(dataset[position])
    }

    override fun getItemCount() =  dataset.size
}