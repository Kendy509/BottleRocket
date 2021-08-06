package com.dynamicdevz.bottlerocket.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dynamicdevz.bottlerocket.model.WeatherRepository
import com.dynamicdevz.bottlerocket.model.WeatherRepositoryImpl
import kotlinx.coroutines.*

class CityViewModel(private val repository: WeatherRepository.CitySearchRepository): ViewModel() {

    private val _city = MutableLiveData<WeatherRepositoryImpl.CityState>()
    val city: LiveData<WeatherRepositoryImpl.CityState> = _city

    private val scope = CoroutineScope(Job() + Dispatchers.IO)

    fun searchCities(cityInput: String){
        println("cityViewmodel $cityInput")
        scope.launch {
            _city.postValue(
                repository.getCityList(cityInput)
            )
        }
    }
}