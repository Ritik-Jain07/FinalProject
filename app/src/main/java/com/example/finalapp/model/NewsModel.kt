package com.example.finalapp.model

/**
 * Data class made for receiving the call of the API of the individual data
 * Also made the table for Database
 * Title is made as the primary key of the table
 * Rest Column details contain: {url, image, description, source, and published}
 */

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "News_Info")
data class NewsModel(
    @PrimaryKey
    @SerializedName("title")
    var title:String,
    @ColumnInfo
    @SerializedName("url")
    var url: String?,
    @SerializedName("image")
    var image: String?,
    @SerializedName("description")
    var description:String,
    @SerializedName("source")
    var source:String,
    @SerializedName("published_at")
    var published:String,

    var isFav:Boolean = false

)