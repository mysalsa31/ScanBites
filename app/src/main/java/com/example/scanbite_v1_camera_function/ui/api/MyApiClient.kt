package com.example.scanbite_v1_camera_function.ui.api

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class MyApiClient {
    private val service: NutritionixApiService
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://trackapi.nutritionix.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(NutritionixApiService::class.java)
    }

    fun searchFoods(query: String, appId: String, appKey: String, onResponse: (List<FoodItem>?) -> Unit, onFailure: (Throwable) -> Unit) {
        val call = service.searchFoods(query, appId, appKey)
        call.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                if (response.isSuccessful) {
                    onResponse(response.body()?.results)
                } else {
                    val errorBody = response.errorBody()?.string() ?: ""
                    onFailure(Throwable("Failed to get response. Error: $errorBody"))
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                onFailure(t)
            }
        })
    }
}