package com.dynamicdevz.bottlerocket.viewmodel.providers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dynamicdevz.bottlerocket.model.WeatherRepository
import com.dynamicdevz.bottlerocket.viewmodel.CityViewModel
import com.dynamicdevz.bottlerocket.viewmodel.WeatherViewModel

class ViewModelProviderImpl(private val weatherRepository: WeatherRepository,
private val citySearchRepository: WeatherRepository.CitySearchRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass){
            WeatherViewModel::class.java ->
                WeatherViewModel(weatherRepository) as T
            CityViewModel::class.java ->
                CityViewModel(citySearchRepository) as T
            else ->
                throw IllegalAccessException("Incorrect ViewModel")
        }
    }

}