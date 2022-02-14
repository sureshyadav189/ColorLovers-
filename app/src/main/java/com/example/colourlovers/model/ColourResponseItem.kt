package com.example.colourlovers.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coloursDb")
data class ColourResponseItem(
    val apiUrl: String,
    val badgeUrl: String,
    val dateCreated: String,
    val description: String,
    val hex: String,
    //val hsv: Hsv,
    @PrimaryKey
    val id: Int,
    val imageUrl: String,
    val numComments: Int,
    val numHearts: Float,
    val numViews: Int,
    val numVotes: Int,
    val rank: Int,
    //val rgb: Rgb,
    val title: String,
    val url: String,
    val userName: String,
    var is_liked:Boolean= true
)