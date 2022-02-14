package com.example.colourlovers.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class MainViewModelFactorey(private val repositorey: ColourRepositorey):ViewModelProvider.Factory {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
     return MainViewModel(repositorey) as T
    }


}