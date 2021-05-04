package com.example.finalapp.apiretrofit

import com.example.finalapp.model.DataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    /**
     * Abstract Function to fetch news from network without search keyword
     * Return type : List(DataModel)
     */
    @GET("news")
    fun getNews(
        @Query("access_key")key:String,
        @Query("languages") language:String,
        @Query("categories")category:String
        ): Call<DataModel>

    /**
     * Abstract Function to fetch news from network with search keyword
     * Return type : List(DataModel)
     */
    @GET("news")
        fun getKeywordNews(
        @Query("access_key")key:String,
        @Query("languages") language:String,
        @Query("categories")category:String,
        @Query("keyword")search:String
        ): Call<DataModel>

}