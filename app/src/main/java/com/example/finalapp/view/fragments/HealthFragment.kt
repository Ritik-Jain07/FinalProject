package com.example.finalapp.view.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalapp.model.CustomAdapter
import com.example.finalapp.viewmodel.MyViewModel
import com.example.finalapp.R
import com.example.finalapp.model.modelclass.DataBaseNewsModel
import com.example.finalapp.saveditem.AppRoomDatabase
import com.example.finalapp.saveditem.DatabaseBuilder
import kotlinx.android.synthetic.main.fragment_health.*
import java.util.concurrent.Executors

class HealthFragment:Fragment() {

    private lateinit var roomDatabaseBuilder: AppRoomDatabase
    private lateinit var myViewModel: MyViewModel
    private lateinit var customAdapter:CustomAdapter


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        roomDatabaseBuilder = DatabaseBuilder.getInstance(activity!!)
        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
//        customAdapter = CustomAdapter(activity!!, dataList){ newsdata ->
//            if (newsdata.isFav) {
//                Executors.newSingleThreadExecutor().execute {
//                    roomDatabaseBuilder.newsDao().deleteData(
//                        DataBaseNewsModel(
//                            title = newsdata.title,
//                            url = newsdata.url,
//                            image = newsdata.image,
////                                        desc = newsdata.description,
//                            isFav = false
//                        )
//                    )
//                }
//                // remove from tabale
//
//                newsdata.isFav = false
//            } else {
//                Executors.newSingleThreadExecutor().execute {
//                    roomDatabaseBuilder.newsDao().insertData(
//                        DataBaseNewsModel(
//                            title = newsdata.title,
//                            url = newsdata.url,
//                            image = newsdata.image,
////                                desc = newsdata.description,
//                            isFav = true
//                        )
//                    )
//                }
//                // inert val first time
//
//                newsdata.isFav = true
//            }
//
//            activity!!.runOnUiThread {
//                customAdapter.notifyDataSetChanged()
//            }
//        }

        btn_health.setOnClickListener() {
            val application = requireActivity().application
            if (!netConnectivity(application)) {
                Toast.makeText(activity, "No Internet", Toast.LENGTH_LONG).show()
            }
            var query: String = ed_text_health.text.toString()
            myViewModel.getDataFromNetworkWithKeyword("health", query)
            myViewModel.mutableLiveData?.observe(viewLifecycleOwner, Observer { list ->
//            Log.i("DATA",it.data.toString())
                health_recyclerView.also {
                    it.layoutManager = LinearLayoutManager(requireContext())
                    it.setHasFixedSize(true)
                    it.adapter = CustomAdapter(activity!!, list.data){ newsData ->
                        if (newsData.isFav) {
                            // insert here
                            myViewModel.addAsFav(newsData)
                        }
                    }
                }
            })
        }
        myViewModel.getDataFromNetwork("health")
        myViewModel.mutableLiveData?.observe(viewLifecycleOwner, Observer { list ->
//            Log.i("DATA",it.data.toString())
            health_recyclerView.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = CustomAdapter(activity!!, list.data) { newsData ->
                    if (newsData.isFav) {
                        // insert here
                        myViewModel.addAsFav(newsData)
                    }
                }
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_health,container,false)
    }

    /*Function to check the net connectivity
    Parameters passed : context
    return type : boolean
    */

    private fun netConnectivity(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
        return false
    }
}