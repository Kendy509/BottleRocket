package com.dynamicdevz.bottlerocket.model

interface WeatherRepository {

    suspend fun getCityDetails(geoId: Int): WeatherRepositoryImpl.CityDetailsState

    interface CitySearchRepository: WeatherRepository {
        suspend fun getCityList(input: String): WeatherRepositoryImpl.CityState
        fun storeCityDetails(geoNameId: Int)
    }
}