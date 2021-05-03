package com.example.finalapp.saveditem

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.finalapp.model.modelclass.DataBaseNewsModel
import com.example.finalapp.model.modelclass.NewsModel

@Database(entities = [NewsModel::class], version = 3)
abstract class AppRoomDatabase:RoomDatabase() {

    abstract fun newsDao(): NewsDao
}
