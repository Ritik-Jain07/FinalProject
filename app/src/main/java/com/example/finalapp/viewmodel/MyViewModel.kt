package com.example.finalapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.finalapp.model.modelclass.DataBaseNewsModel
import com.example.finalapp.model.modelclass.DataModel
import com.example.finalapp.model.NewsRepo
import com.example.finalapp.model.modelclass.NewsModel
import com.example.finalapp.saveditem.DatabaseBuilder
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
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

//    fun loadData(category: String): LiveData<DataModel> {
//        if (mutableLiveData == null) {
//            mutableLiveData = newsRepo!!.getData(category)
//        }
//        return mutableLiveData!!
//    }
//
    fun getDataFromNetwork(category: String){
        newsRepo!!.getData(category)
    }

    fun getDataFromNetworkWithKeyword(category: String,keyword:String){
        newsRepo!!.getDataWithKeyword(category,keyword)
    }

    fun addAsFav(newsdata: NewsModel){
        Executors.newSingleThreadExecutor().execute {
            roomDatabaseBuilder.newsDao().insertData(
                NewsModel(
                    title = newsdata.title,
                    url = newsdata.url,
                    image = newsdata.image,
                    published = newsdata.published,
                    source = newsdata.source,
                    description = newsdata.description,
                    isFav = true))
    }
//    fun addSavedNews(newsdata: DataBaseNewsModel) {
//        Executors.newSingleThreadExecutor().execute {
//            roomDatabaseBuilder.newsDao().insertData(
//                DataBaseNewsModel(
//                    title = newsdata.title,
//                    url = newsdata.url,
//                    image = newsdata.image
//                )
//            )
//        }
//    }
}

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
//                    isFav = false
                )
            )
        }
    }
}