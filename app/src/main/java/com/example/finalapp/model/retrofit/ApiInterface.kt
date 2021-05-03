package com.example.finalapp.model.retrofit

import com.example.finalapp.model.modelclass.DataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("news")
    fun getNews(
        @Query("access_key")key:String,
        @Query("languages") language:String,
//        @Query("countries")countries:String,
        @Query("categories")category:String
        ): Call<DataModel>

    @GET("news")
        fun getKeywordNews(
        @Query("access_key")key:String,
        @Query("languages") language:String,
//        @Query("countries")countries:String,
        @Query("categories")category:String,
        @Query("keyword")search:String
        ): Call<DataModel>

//    fun getCategoryNews(
//        @Query("access_key")key:String,
//        @Query("languages") language:String,
//
//    ): Call<List<DataModel>>
}