package com.example.finalapp.saveditem

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.finalapp.model.NewsModel

/**
 * Dao interface made for fetching queries from the database
 */

@Dao
interface NewsDao {

    /**
     * Function to fetch all record from the table "News_Info"
     * return type : List<NewsModel>
     */
    @Query("SELECT * FROM News_Info")
    fun getAllData(): List<NewsModel>

    /**
     * Function to insert a record in the table "News_Info"
     * Parameters passed : data: NewsModel to be inserted
     */
    @Insert
    fun insertData(data: NewsModel)

    /**
     * Function to delete a record from the table "News_Info"
     * Parameters passed : data: NewsModel to be deleted
     */
    @Delete
    fun deleteData(data: NewsModel)
}