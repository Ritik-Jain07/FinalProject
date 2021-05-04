package com.example.finalapp.model

import com.google.gson.annotations.SerializedName

/**
 * Data class made for receiving the call of the API
 */
data class DataModel(
    @SerializedName("data")
    var data:ArrayList<NewsModel>
    )