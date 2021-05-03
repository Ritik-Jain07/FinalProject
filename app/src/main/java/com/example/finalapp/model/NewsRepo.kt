package com.example.finalapp.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.finalapp.model.modelclass.DataModel
import com.example.finalapp.model.retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepo {

    val TAG = NewsRepo::class.java.simpleName
    private val KEY = "163d19643270bc5175c96f6475eda9f8"
    val mutableList: MutableLiveData<DataModel> = MutableLiveData()
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

        })}

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


