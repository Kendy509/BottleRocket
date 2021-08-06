package com.dynamicdevz.bottlerocket.di

import androidx.lifecycle.ViewModel
import com.dynamicdevz.bottlerocket.model.WeatherRepositoryImpl
import com.dynamicdevz.bottlerocket.view.adapters.CityAdapter
import com.dynamicdevz.bottlerocket.view.adapters.ForecastWeatherAdapter
import com.dynamicdevz.bottlerocket.view.adapters.WeeklyWeatherAdapter
import com.dynamicdevz.bottlerocket.viewmodel.providers.ViewModelProviderImpl

object DI {
    private fun provideRepo() = WeatherRepositoryImpl()

    var context: WeatherApplication? = null

    fun <T : ViewModel> provideViewModel(viewModelClass: Class<T>) =
        ViewModelProviderImpl(provideRepo(), provideRepo()).create(
            viewModelClass
        )

    fun provideForeCastAdapter() = ForecastWeatherAdapter(emptyList())
    fun provideWeeklyAdapter() = WeeklyWeatherAdapter(emptyList(), null)
    fun provideCityAdapter() = CityAdapter(emptyList(), null)
}