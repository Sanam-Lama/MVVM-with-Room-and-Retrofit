package com.example.news.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * It is a helper class where we create an instance of Retrofit, a third party library that
 * helps us to simplify our network calls.
 * We define the base url and use the retrofit object created here to call the apis through repository
 */

object RetrofitHelper {
//    private val BASE_URL = "https://data.cityofnewyork.us/"
    private val BASE_URL = "https://newsapi.org/v2/"

    fun getInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}