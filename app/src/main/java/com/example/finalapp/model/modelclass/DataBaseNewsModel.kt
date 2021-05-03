package com.example.finalapp.model.modelclass

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "News_Info")
data class DataBaseNewsModel(
        @PrimaryKey(autoGenerate = true)
        var id:Int? = 0,
        var title: String,
        var url: String? = null,
        var image: String? = null,
//        var desc: String? = null,
        var isFav: Boolean? = false
)