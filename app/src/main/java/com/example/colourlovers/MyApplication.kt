package com.example.colourlovers

import android.app.Application
import com.example.colourlovers.Roomdb.ColoursDatabase
import com.example.colourlovers.network.Apiutility
import com.example.colourlovers.network.ColoursApi
import com.example.colourlovers.viewmodel.ColourRepositorey

class MyApplication: Application() {
    lateinit var repositorey: ColourRepositorey

    override fun onCreate() {
        super.onCreate()
        val apiinterface = Apiutility.getInstance().create(ColoursApi::class.java)
        val database  = ColoursDatabase.getdatabase(applicationContext)
        repositorey = ColourRepositorey(apiinterface,database,applicationContext)
    }
}