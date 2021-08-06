package com.dynamicdevz.bottlerocket.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class City(
    @Expose
    @SerializedName("modification date")
    val modificationDate: String,
    @Expose
    @SerializedName("admin2 code")
    val admin2Code: Int,
    @Expose
    @SerializedName("country code")
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
    @SerializedName("feature class")
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
