package com.example.finalapp.model.modelclass

import com.google.gson.annotations.SerializedName

data class DataModel(
    @SerializedName("data")
    var data:ArrayList<NewsModel>
    )