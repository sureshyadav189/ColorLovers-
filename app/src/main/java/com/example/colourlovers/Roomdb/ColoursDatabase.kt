package com.example.colourlovers.Roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.colourlovers.model.ColourResponseItem

@Database(entities = [ColourResponseItem::class], version = 1)
abstract class ColoursDatabase : RoomDatabase(){
    abstract fun ColourDao():RoomDao

    companion object{
        private var INSTANCE : ColoursDatabase?=null
        fun getdatabase(context:Context): ColoursDatabase {
            if(INSTANCE==null){
                INSTANCE = Room.databaseBuilder(context,
                        ColoursDatabase::class.java,
                        "coloursDb"
                    ).build()
                }
            return INSTANCE!!
        }
    }

}