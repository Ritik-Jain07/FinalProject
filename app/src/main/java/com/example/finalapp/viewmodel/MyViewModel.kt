package com.example.finalapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.finalapp.model.DataModel
import com.example.finalapp.repo.NewsRepo
import com.example.finalapp.model.NewsModel
import com.example.finalapp.saveditem.DatabaseBuilder
import java.util.concurrent.Executors

class MyViewModel(application: Application): AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    var newsRepo: NewsRepo? = null
    var mutableLiveData :LiveData<DataModel>?=null
    private val roomDatabaseBuilder = DatabaseBuilder.getInstance(context)
    init {
        newsRepo = NewsRepo()
        mutableLiveData= newsRepo!!.mutableList
    }

    /**
     * Function to get data from Api based on category
     * Parameters Passed : Category -> {general, health, science etc...}
     */
    fun getDataFromNetwork(category: String){
        newsRepo!!.getData(category)
    }

    /**
     * Function to get data from Api based on category and keyword
     * Parameters Passed : Category -> {general, health, science etc...} and keyword by user
     */

    fun getDataFromNetworkWithKeyword(category: String,keyword:String){
        newsRepo!!.getDataWithKeyword(category,keyword)
    }

    /**
     * Function to add data in database
     * Parameters passed : data : NewsModel type
     * Return Type : None
     */

    fun addAsFav(newsData: NewsModel){
        Executors.newSingleThreadExecutor().execute {
            roomDatabaseBuilder.newsDao().insertData(
                NewsModel(
                    title = newsData.title,
                    url = newsData.url,
                    image = newsData.image,
                    published = newsData.published,
                    source = newsData.source,
                    description = newsData.description,
                    isFav = true))
        }
    }

    /**
     * Function to delete data in database
     * Parameters passed : data : NewsModel type
     * Return Type : None
     */
    fun deleteFav(newsData: NewsModel) {
        Executors.newSingleThreadExecutor().execute {
            roomDatabaseBuilder.newsDao().deleteData(
                NewsModel(
                    title = newsData.title,
                    url = newsData.url,
                    image = newsData.image,
                    published = newsData.published,
                    source = newsData.source,
                    description = newsData.description,
                    isFav = false
                )
            )
        }
    }
}