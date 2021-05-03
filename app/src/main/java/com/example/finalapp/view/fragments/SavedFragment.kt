package com.example.finalapp.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalapp.BookmarkViewModel
import com.example.finalapp.BookmarksAdapter
import com.example.finalapp.R
import com.example.finalapp.model.modelclass.NewsModel
import com.example.finalapp.saveditem.AppRoomDatabase
import com.example.finalapp.saveditem.DatabaseBuilder
import kotlinx.android.synthetic.main.fragment_saved.*
import java.util.concurrent.Executors

class SavedFragment:Fragment() {

    val TAG = SavedFragment::class.java.simpleName
    private lateinit var roomDatabaseBuilder:AppRoomDatabase
    private lateinit var bookmarkAdapter:BookmarksAdapter
    private lateinit var bookmarkViewModel: BookmarkViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_saved,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        roomDatabaseBuilder = DatabaseBuilder.getInstance(activity!!)

        bookmarkViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(activity!!.application)
            .create(BookmarkViewModel::class.java)

        saved_recyclerView.layoutManager = LinearLayoutManager(activity!!.applicationContext, RecyclerView.VERTICAL, false)
        val roomDatabaseBuilder = DatabaseBuilder.getInstance(activity!!.applicationContext)
        bookmarkViewModel.getRoomData().observe(viewLifecycleOwner, {
//            if (it.isEmpty()) {
//                tvNodata.visibility = View.VISIBLE
//                imgnodata.visibility = View.VISIBLE
//                return@observe
//            }
            bookmarkAdapter = BookmarksAdapter(activity!!.applicationContext, list = it)
            saved_recyclerView.adapter = bookmarkAdapter
            bookmarkAdapter.onItemClick = { newsdata ->
                if (newsdata.isFav!!) {
                    Executors.newSingleThreadExecutor().execute {

                        roomDatabaseBuilder.newsDao().deleteData(
                            NewsModel(
                                title = newsdata.title,
                                url = newsdata.url,
                                image = newsdata.image,
                                published = newsdata.published,
                                source = newsdata.source,
                                description = newsdata.description,
                                isFav = false
                            )
                        )
                    }// remove from table

                    newsdata.isFav = false
                } else {
                    Executors.newSingleThreadExecutor().execute {

                        roomDatabaseBuilder.newsDao().insertData(
                            NewsModel(
                                title = newsdata.title,
                                url = newsdata.url,
                                image = newsdata.image,
                                published = newsdata.published,
                                source = newsdata.source,
                                description = newsdata.description,
                                isFav = true
                            )
                        )
                    }// inert val first time

                    newsdata.isFav = true
                }
                bookmarkAdapter.notifyDataSetChanged()
            }

//        Log.i(TAG,roomDatabaseBuilder.newsDao().getAllData().toString())
    })
}
}