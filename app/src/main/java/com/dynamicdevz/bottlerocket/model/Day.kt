package com.dynamicdevz.bottlerocket.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Day(
    val dayOfTheWeek: Int,
    val low: Int,
    val high: Int,
    val weatherType: String,
    @Expose
    @SerializedName("hourlyWeather")
    val hourlyWeatherList: List<HourlyWeather>
)
