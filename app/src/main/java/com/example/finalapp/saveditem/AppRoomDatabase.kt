package com.example.finalapp.saveditem

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.finalapp.model.modelclass.DataBaseNewsModel

@Database(entities = arrayOf(DataBaseNewsModel::class), version = 1)
abstract class AppRoomDatabase:RoomDatabase() {

    abstract fun newsDao(): NewsDao
}
