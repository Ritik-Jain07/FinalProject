package com.example.finalapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.finalapp.model.modelclass.NewsModel
import com.example.finalapp.saveditem.AppRoomDatabase
import com.example.finalapp.saveditem.DatabaseBuilder
import java.util.concurrent.Executors

class BookmarkViewModel(application: Application):AndroidViewModel(application) {
    private val mutableLiveDataNewsRoom: MutableLiveData<List<NewsModel>> =
        MutableLiveData<List<NewsModel>>()
    private val mutableLiveDataerror: MutableLiveData<String> = MutableLiveData<String>()
    private lateinit var roomDatabaseBuilder: AppRoomDatabase

    fun getRoomData(): MutableLiveData<List<NewsModel>> {
        roomDatabaseBuilder = DatabaseBuilder.getInstance(getApplication<Application>().applicationContext)
        Executors.newSingleThreadExecutor().execute {
            val _databaseList = roomDatabaseBuilder.newsDao().getAllData() // get list from database
            mutableLiveDataNewsRoom.postValue(_databaseList)
        }


        return mutableLiveDataNewsRoom
    }

}
