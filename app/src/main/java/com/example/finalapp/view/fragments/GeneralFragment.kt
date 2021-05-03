package com.example.finalapp.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalapp.model.CustomAdapter
import com.example.finalapp.viewmodel.MyViewModel
import com.example.finalapp.R
import com.example.finalapp.WebActivity
import com.example.finalapp.model.modelclass.NewsModel
import com.example.finalapp.saveditem.AppRoomDatabase
import kotlinx.android.synthetic.main.fragment_general.*

class GeneralFragment: Fragment() {
    val dataList = ArrayList<NewsModel>()
    var roomDatabaseBuilder: AppRoomDatabase? = null
    private lateinit var myViewModel: MyViewModel
    val TAG = GeneralFragment::class.java.simpleName
    private lateinit var customAdapter: CustomAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        customAdapter = CustomAdapter(activity!!, dataList)
        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        btn.setOnClickListener {
            val query: String = ed_text.text.toString()
            myViewModel.getDataFromNetworkWithKeyword("general", query)
            myViewModel.mutableLiveData?.observe(viewLifecycleOwner, Observer { list ->
                general_recyclerView.also {
                    it.layoutManager = LinearLayoutManager(requireContext())
                    it.setHasFixedSize(true)
                    it.adapter = CustomAdapter(activity!!, list.data)
                }
            })
        }
        myViewModel.getDataFromNetwork("general")
        myViewModel.mutableLiveData?.observe(viewLifecycleOwner, Observer { list ->
            general_recyclerView.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = CustomAdapter(activity!!, list.data)
            }
        })


//        bookmarkAdded()

//        customAdapter.onItemClick = { newsdata ->
//            val application = requireActivity().application
//                val myViewModel =
//                    ViewModelProvider(this).get(MyViewModel(application)::class.java).also {
//                        it.addSavedNews(
//                            DataBaseNewsModel(
//                                title = newsdata.title,
//                                url = newsdata.url,
//                                image = newsdata.image,
//                            )
//                        )
//                        Toast.makeText(activity, "News Saved Successfully", Toast.LENGTH_LONG).show()
//                    }
//            }


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_general, container, false)
    }
//
//    override fun onItemClicked(
//        position: Int,
//        url_adapter: String,
//        title_adapter: String,
//        desc_adapter: String
//    ) {
//        val intent = Intent(this , WebActivity::class.java)
//        intent.apply {
//            putExtra("url_news" , url_adapter)
//            putExtra("title_news" , title_adapter)
//            putExtra("desc_news" , desc_adapter)
//        }
//    }
}

//    private fun bookmarkAdded() {
//        customAdapter.onItemClick = { newsdata ->
//            val application = requireActivity().application
//            if (newsdata.isFav) {
//                val myViewModel = ViewModelProvider(this).get(MyViewModel(application)::class.java).also {
//                    it.addSavedNews(
//                        DataBaseNewsModel(
//                            title = newsdata.title,
//                            url = newsdata.url,
//                            image = newsdata.image,
//                        )
//                    )
//                }
//            }
//            Toast.makeText(activity, "News Saved Successfully", Toast.LENGTH_LONG).show()
//        }
//    }
//}




//bookmark.setOnClickListener {
//    bookmark_click.setColorFilter(resources.getColor(R.color.red))
//    val application = requireActivity().application
//    val homeViewModel =
//            ViewModelProvider(this).get(HomeViewModel(application)::class.java).also {
//                it.addSavedNewsDetails(
//                        SavedNews(
//                                news_title = titleData.toString(),
//                                news_desc = descriptionData,
//                                news_time = timeData
//                        )
//                )
//            }
//    Toast.makeText(activity, "News Saved Successfully", Toast.LENGTH_LONG).show()
//}
//    private fun setupUI() {
//
//        Executors.newSingleThreadExecutor().execute {
//
//            val _databaseList = roomDatabaseBuilder!!.newsDao().getAllData()
//
//            if (_databaseList.size > 0) {  // set data to true if any marked fav
//                for (item in dataList) {
//                    for (data in _databaseList) {
//                        if (item.title == data.title) {
//                            item.isFav = true
//                        }
//                    }
//                }
//            }
//            getActivity()!!.runOnUiThread{
//                customAdapter.notifyDataSetChanged()
//            }
//
//            customAdapter.onItemClick = { newsdata ->
//
//                if (newsdata.isFav) {
//                    Executors.newSingleThreadExecutor().execute {
//                        roomDatabaseBuilder!!.newsDao().deleteData(
//                                DataBaseNewsModel(
//                                        title = newsdata.title,
//                                        url = newsdata.url,
//                                        image = newsdata.image,
////                                        desc = newsdata.description,
//                                        isFav = false
//                                )
//                        )
//                    }
//                    // remove from tabale
//
//                    newsdata.isFav = false
//                }
//                else {
//                    Executors.newSingleThreadExecutor().execute {
//                        roomDatabaseBuilder!!.newsDao().insertData(DataBaseNewsModel(
//                                title = newsdata.title,
//                                url = newsdata.url,
//                                image = newsdata.image,
////                                desc = newsdata.description,
//                                isFav = true
//                        )
//                        )
//                    }
//                    // inert val first time
//
//                    newsdata.isFav = true
//                }
//
//                getActivity()!!.runOnUiThread {
//                    customAdapter.notifyDataSetChanged()
//                }
//            }
//
////            customAdapter.onTitleClick = { newsModel ->
////                val intent = Intent(this@SubCategoryActivity, WebViewActivity::class.java)
////                intent.putExtra("url", newsModel.url)
////                startActivity(intent)
////            }
//        }
//
//    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//    }


//    myViewModel.loadData("general").observe(this, object: Observer<List<DataModel>> {
//
//        override fun onChanged(t: List<DataModel>?) {
//            CustomAdapter.addData(t!!)
//            CustomAdapter.notifyDataSetChanged()
//        }
//    })
//    lateinit var recyclerView:RecyclerView
//    private lateinit var customAdapter:CustomAdapter
//    private var viewModelInstance:MyViewModel?=null
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_general,container,false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        viewModelInstance = ViewModelProviders.of(activity!!).get(MyViewModel::class.java)
//
//        setupUI()
//
//        viewModelInstance!!.getData().observe()
//    }
//
//    private fun setupUI(){
//        val linearLayoutManager = LinearLayoutManager(activity!!, LinearLayoutManager.VERTICAL,false)
//    general_recyclerView.layoutManager = linearLayoutManager
//
//        customAdapter = CustomAdapter(this, dataList)
//    general_recyclerView.adapter= this.customAdapter
//    }


//    myViewModel.loadData("general").observe(this, object: Observer<List<DataModel>> {
//
//        override fun onChanged(t: List<DataModel>?) {
//            val customAdapter:CustomAdapter= CustomAdapter.addData(t!!)
//            CustomAdapter.notifyDataSetChanged()
//        }
//
//    })
