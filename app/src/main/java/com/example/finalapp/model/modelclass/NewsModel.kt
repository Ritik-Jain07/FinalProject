package com.example.finalapp.model.modelclass


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