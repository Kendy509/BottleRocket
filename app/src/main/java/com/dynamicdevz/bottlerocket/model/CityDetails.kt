package com.dynamicdevz.bottlerocket.model

import com.google.gson.annotations.Expose

data class CityDetails(
    @Expose
    val city: City,
    @Expose
    val weather: Weather
)
