package com.example.finalapp.view.fragments

import android.content.Context
import android.content.Intent
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
import com.example.finalapp.WebActivity
import com.example.finalapp.model.modelclass.DataBaseNewsModel
import com.example.finalapp.model.modelclass.NewsModel
import com.example.finalapp.saveditem.AppRoomDatabase
import com.example.finalapp.saveditem.DatabaseBuilder
import com.example.finalapp.saveditem.DatabaseBuilder.getInstance
import kotlinx.android.synthetic.main.fragment_general.*
import java.util.concurrent.Executors

class GeneralFragment: Fragment() {
    val dataList = ArrayList<NewsModel>()
    private lateinit var myViewModel: MyViewModel
    val TAG = GeneralFragment::class.java.simpleName
    private lateinit var customAdapter: CustomAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        customAdapter = CustomAdapter(activity!!, dataList){ newsData ->
//                if (newsData.isFav) {
//                    // insert here
//                    myViewModel.addAsFav(newsData)
//                }
//        }
        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        btn.setOnClickListener {
            val application = requireActivity().application
            if (!netConnectivity(application)) {
                Toast.makeText(activity, "No Internet", Toast.LENGTH_LONG).show()
            }
            val query: String = ed_text.text.toString()
            myViewModel.getDataFromNetworkWithKeyword("general", query)
            myViewModel.mutableLiveData?.observe(viewLifecycleOwner, Observer { list ->
                general_recyclerView.also {
                    it.layoutManager = LinearLayoutManager(requireContext())
                    it.setHasFixedSize(true)
                    it.adapter = CustomAdapter(activity!!, list.data) { newsData ->
                        if (newsData.isFav) {
                            // insert here
                            myViewModel.deleteFav(newsData)
                            newsData.isFav = false

                        }else{
                            myViewModel.addAsFav(newsData)
                            newsData.isFav= true
                        }
                    }
                }
            }
            )
        }
// Hit Normal APi without search
        myViewModel.getDataFromNetwork("general")
        myViewModel.mutableLiveData?.observe(viewLifecycleOwner, Observer { list ->
            general_recyclerView.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = CustomAdapter(activity!!, list.data){ newsData ->
                    if (newsData.isFav) {
                        // insert here
                        myViewModel.deleteFav(newsData)
                        newsData.isFav = false
                    }else{
                        myViewModel.addAsFav(newsData)
                        newsData.isFav= true
                    }
                }
            }
            }
        )




        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_general, container, false)
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