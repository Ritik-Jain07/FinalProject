package com.example.finalapp.saveditem

import android.content.Context
import androidx.room.Room

/**
 * Singleton class made for creating an instance of Room Database
 */

object DatabaseBuilder {

    private var INSTANCE: AppRoomDatabase? = null

    /**
     * Function for creating an instance of Database
     */

    fun getInstance(context: Context): AppRoomDatabase {
        if (INSTANCE == null) {
            synchronized(AppRoomDatabase::class) {
                INSTANCE =
                    buildRoomDB(
                        context
                    )
            }
        }
        return INSTANCE!!
    }

    /**
     * Function for creating the database for Room
     * parameters passed : context
     */

    private fun buildRoomDB(context: Context): AppRoomDatabase? {
        return Room.databaseBuilder(context, AppRoomDatabase::class.java, "news_db")
            .fallbackToDestructiveMigration().build()
    }
}