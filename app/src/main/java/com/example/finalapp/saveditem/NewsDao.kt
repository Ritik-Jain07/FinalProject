package com.example.finalapp.saveditem

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.finalapp.model.modelclass.DataBaseNewsModel

@Dao
interface NewsDao {

    @Query("SELECT * FROM News_Info")
    fun getAllData(): List<DataBaseNewsModel>

    @Insert
    fun insertData(data: DataBaseNewsModel)

    @Delete
    fun deleteData(data: DataBaseNewsModel)
}