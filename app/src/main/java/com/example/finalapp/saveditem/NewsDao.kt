package com.example.finalapp.saveditem

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.finalapp.model.modelclass.DataBaseNewsModel
import com.example.finalapp.model.modelclass.NewsModel

@Dao
interface NewsDao {

    @Query("SELECT * FROM News_Info")
    fun getAllData(): List<NewsModel>

    @Insert
    fun insertData(data: NewsModel)

    @Delete
    fun deleteData(data: NewsModel)
}