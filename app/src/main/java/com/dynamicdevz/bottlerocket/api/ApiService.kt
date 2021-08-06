package com.dynamicdevz.bottlerocket.api

import com.dynamicdevz.bottlerocket.model.CityDetails
import com.dynamicdevz.bottlerocket.model.CitySearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("cities")
    suspend fun getCityList(
        @Query("search") city: String
    ): Response<CitySearchResponse>

    @GET("cities")
    suspend fun getCityList(
        @Query("search") city: String,
        @Query("pageCount") pageCount: Int,
        @Query("pageNumber") pageNumber: Int
    ): Response<CitySearchResponse>

    @GET("cities/{cityId}")
    suspend fun getCityDetails(
        @Path("cityId") cityId: Int
    ): Response<CityDetails>
}