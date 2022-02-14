package com.example.colourlovers.viewmodel

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.colourlovers.Roomdb.ColoursDatabase
import com.example.colourlovers.Roomdb.RoomDao
import com.example.colourlovers.model.ColourResponse
import com.example.colourlovers.model.ColourResponseItem
import com.example.colourlovers.network.ColoursApi
import com.example.colourlovers.network.SafeApi
import com.example.colourlovers.util.myutil

class ColourRepositorey(
    private val api: ColoursApi,
    private val ColorDatabase: ColoursDatabase,
    private val applicationContext: Context
):SafeApi() {

    private val colourlivedata=MutableLiveData<List<ColourResponseItem>>()
    val colours:LiveData<List<ColourResponseItem>>
    get() = colourlivedata

    @RequiresApi(Build.VERSION_CODES.M)
    suspend fun getColours(){
        if(myutil.isInterNetAvailable(applicationContext)){
            val result = api.getColours()
            if(result.body()!=null){
                ColorDatabase.ColourDao().insertColours(result.body()!!)
                //colourlivedata.postValue(result.body())
                val colour = ColorDatabase.ColourDao().getColours()
                //val colorlist = ColourResponse(colour)
                colourlivedata.postValue(colour)
            }
        }
        else{
            val colour = ColorDatabase.ColourDao().getColours()
            //val colorlist = ColourResponse(colour)
            colourlivedata.postValue(colour)
        }

    }

    suspend fun updateliked(isliked:Boolean,id:Int){
        ColorDatabase.ColourDao().update(isliked,id)
    }
}