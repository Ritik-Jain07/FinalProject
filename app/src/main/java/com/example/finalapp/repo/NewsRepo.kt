package com.example.finalapp.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.finalapp.apiretrofit.ApiClient
import com.example.finalapp.model.DataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepo {

    val TAG = NewsRepo::class.java.simpleName
    private val KEY = "163d19643270bc5175c96f6475eda9f8"
    val mutableList: MutableLiveData<DataModel> = MutableLiveData()

    /**
     * Function getData() to fetch API without keyword
     * Parameters passed : category -> {business, general, health etc...}
     */
    fun getData(category: String) {
        val call = ApiClient.getClient.getNews(KEY, "en", category)
        call.enqueue(object : Callback<DataModel> {
            override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                Log.i(TAG, response.body().toString())
                mutableList.postValue(response.body())
            }

            override fun onFailure(call: Call<DataModel>, t: Throwable) {
                Log.e(TAG, t.localizedMessage.toString())
            }
        })
    }

    /**
     * Function getDataWithKeyword() to fetch API without keyword
     * Parameters passed : category -> {business, general, health etc...} and keyword -> entered by the user
     */

        fun getDataWithKeyword(category: String, keyword: String) {

            val call = ApiClient.getClient.getKeywordNews(KEY, "en", category, keyword)
            call.enqueue(object : Callback<DataModel> {
                override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                    Log.i(TAG, response.body().toString())
                    mutableList.postValue(response.body())
                }

                override fun onFailure(call: Call<DataModel>, t: Throwable) {
                    Log.e(TAG, t.localizedMessage.toString())
                }

            })
        }
    }


