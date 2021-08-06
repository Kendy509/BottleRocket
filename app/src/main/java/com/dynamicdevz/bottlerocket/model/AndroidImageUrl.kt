package com.dynamicdevz.bottlerocket.model

import com.google.gson.annotations.Expose

data class AndroidImageUrl(
    @Expose
    val modificationDate: String,
    @Expose
    val admin2Code: Int,
    @Expose
    val countryCode: String,
    @Expose
    val population: Int,
    @Expose
    val asciiname: String,
    @Expose
    val geonameid: Int,
    @Expose
    val dem: Int,
    @Expose
    val featureClass: String,
    @Expose
    val imageURLs: ImageUrl,
    @Expose
    val timezone: String,
    @Expose
    val name: String,
    @Expose
    val elevation: Int,
    @Expose
    val latitude: Double,
    @Expose
    val longitude: Double
)
