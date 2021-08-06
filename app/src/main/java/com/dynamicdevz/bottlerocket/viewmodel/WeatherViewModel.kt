package com.dynamicdevz.bottlerocket.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dynamicdevz.bottlerocket.model.HourlyWeather
import com.dynamicdevz.bottlerocket.model.WeatherRepository
import com.dynamicdevz.bottlerocket.model.WeatherRepositoryImpl
import kotlinx.coroutines.*

class WeatherViewModel(private val weatherRepository: WeatherRepository): ViewModel() {

    private val _weather =  MutableLiveData<WeatherRepositoryImpl.CityDetailsState>()
    val weather: LiveData<WeatherRepositoryImpl.CityDetailsState> = _weather

    private val _hourly = MutableLiveData<List<HourlyWeather>>()
    val hourly: LiveData<List<HourlyWeather>> = _hourly

    private val scope = CoroutineScope(Job() + Dispatchers.IO)

    fun searchCity(cityId: Int){
        scope.launch {
            _weather.postValue(
                weatherRepository.getCityDetails(cityId)
            )
        }
    }

    fun filterByDay(position: Int){
        scope.launch(Dispatchers.Default) {
            val hourly = (_weather.value as WeatherRepositoryImpl.CityDetailsState.Response)
                .cityDetails
                .weather
                .days[position]
                .hourlyWeatherList
            println(hourly)
            _hourly.postValue(hourly)
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}