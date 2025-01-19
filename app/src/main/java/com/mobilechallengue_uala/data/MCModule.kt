package com.mobilechallengue_uala.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val apiService: ApiService = Retrofit.Builder()
        .baseUrl("https://gist.githubusercontent.com/hernan-uala/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)
}
