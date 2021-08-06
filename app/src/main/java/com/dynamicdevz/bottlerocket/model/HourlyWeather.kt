package com.dynamicdevz.bottlerocket.model

data class HourlyWeather(
    val windSpeed: Double,
    val temperature: Int,
    val weatherType: String,
    val humidity: Double,
    val hour: Int,
    val rainChance: Double
)
