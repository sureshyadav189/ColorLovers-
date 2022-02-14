package com.example.colourlovers.network

import com.example.colourlovers.model.ColourResponse
import com.example.colourlovers.model.ColourResponseItem
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ColoursApi {

    @GET(value = "api/colors/&format=json")
    suspend fun getColours():Response<List<ColourResponseItem>>

}