package com.example.finalapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.finalapp.model.NewsModel
import com.example.finalapp.saveditem.DatabaseBuilder
import java.util.concurrent.Executors

class BookmarkViewModel(application: Application):AndroidViewModel(application) {


    val liveDataRoom: MutableLiveData<List<NewsModel>> = MutableLiveData<List<NewsModel>>()
    private val context = getApplication<Application>().applicationContext
    private val roomDatabaseBuilder = DatabaseBuilder.getInstance(context)

    /**
     * Function to fetch data from database
     * Parameters passed : None
     * Return Type : MutableLiveData<List<NewsModel>>
     */
    fun getDataFromDatabase():MutableLiveData<List<NewsModel>>{
        Executors.newSingleThreadExecutor().execute(){
            val list = roomDatabaseBuilder.newsDao().getAllData()
            liveDataRoom.postValue(list)
        }
        return liveDataRoom
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


