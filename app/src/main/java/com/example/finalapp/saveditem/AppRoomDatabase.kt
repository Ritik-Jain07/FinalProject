package com.example.finalapp.saveditem

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.finalapp.model.NewsModel

/**
 * Abstract Class for Room Database contains one abstract function newsDao() for fetching the queries
 */

@Database(entities = [NewsModel::class], version = 3)
abstract class AppRoomDatabase:RoomDatabase() {

    abstract fun newsDao(): NewsDao
}
