package com.example.colourlovers.Roomdb

import androidx.room.*
import com.example.colourlovers.model.ColourResponseItem

@Dao
interface RoomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertColours(colours:List<ColourResponseItem>)

    @Query("SELECT * from coloursDb")
    suspend fun getColours():List<ColourResponseItem>


    @Query("UPDATE coloursDb SET is_liked = :isliked WHERE id = :Id")
    suspend fun update(isliked:Boolean,Id:Int)
}