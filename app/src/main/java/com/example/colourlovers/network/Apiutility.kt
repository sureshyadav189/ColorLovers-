package com.example.colourlovers.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Apiutility {
    val BASE_URL = "https://www.colourlovers.com/"
    fun getInstance():Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}