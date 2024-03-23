package com.example.scanbite_v1_camera_function.ui.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface NutritionixApiService {
    @GET("v2/search/instant")
    fun searchFoods(
        @Query("query") query: String,
        @Header("x-app-id") appId: String,
        @Header("x-app-key") appKey: String
    ): Call<SearchResponse>
}