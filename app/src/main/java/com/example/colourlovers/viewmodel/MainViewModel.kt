package com.example.colourlovers.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.colourlovers.model.ColourResponse
import com.example.colourlovers.model.ColourResponseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

@RequiresApi(Build.VERSION_CODES.M)
class MainViewModel(private val repositorey: ColourRepositorey): ViewModel() {
    init {
        viewModelScope.launch {
            repositorey.getColours()
        }
    }

    val colours : LiveData<List<ColourResponseItem>>
    get() = repositorey.colours


    fun updateLike(isliked:Boolean,id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            repositorey.updateliked(isliked,id)

        }
    }

}