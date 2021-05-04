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
import com.example.finalapp.adapter.CustomAdapter
import com.example.finalapp.viewmodel.MyViewModel
import com.example.finalapp.R
import com.example.finalapp.saveditem.AppRoomDatabase
import kotlinx.android.synthetic.main.fragment_entertainment.*

class EntertainmentFragment:Fragment() {

    private lateinit var myViewModel: MyViewModel
    private lateinit var customAdapter: CustomAdapter
    private lateinit var roomDatabaseBuilder: AppRoomDatabase

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        btn_enter.setOnClickListener() {
            val application = requireActivity().application
            if (!netConnectivity(application)) {
                Toast.makeText(activity, "No Internet", Toast.LENGTH_LONG).show()
            }
            val query: String = ed_text_enter.text.toString()
            myViewModel.getDataFromNetworkWithKeyword("entertainment", query)
            myViewModel.mutableLiveData?.observe(viewLifecycleOwner, Observer { list ->
                entertainment_recyclerView.also {
                    it.layoutManager = LinearLayoutManager(requireContext())
                    it.setHasFixedSize(true)
                    it.adapter = CustomAdapter(activity!!, list.data) { newsData ->
                        if (newsData.isFav) {
                            // insert here
                            myViewModel.addAsFav(newsData)
                        }else{
                            myViewModel.deleteFav(newsData)
                        }
                }
            }
            })
            }
        myViewModel.getDataFromNetwork("entertainment")
        myViewModel.mutableLiveData?.observe(viewLifecycleOwner, Observer { list ->
//            Log.i("DATA",it.data.toString())
            entertainment_recyclerView.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = CustomAdapter(activity!!, list.data) { newsData ->
                    if (newsData.isFav) {
                        // insert here
                        myViewModel.addAsFav(newsData)
                    }else{
                        myViewModel.deleteFav(newsData)
                    }
                }
            }
        })
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_entertainment,container,false)
    }

    /**
     * Function to check the net connectivity
     * Parameters passed : context
     * return type : boolean
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