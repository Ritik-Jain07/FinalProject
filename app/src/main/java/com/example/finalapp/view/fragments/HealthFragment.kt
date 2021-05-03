package com.example.finalapp.view.fragments

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
import kotlinx.android.synthetic.main.fragment_health.*

class HealthFragment:Fragment() {

    private lateinit var myViewModel: MyViewModel


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        btn_health.setOnClickListener() {
            var query: String = ed_text_health.text.toString()
            myViewModel.getDataFromNetworkWithKeyword("health", query)
            myViewModel.mutableLiveData?.observe(viewLifecycleOwner, Observer { list ->
//            Log.i("DATA",it.data.toString())
                health_recyclerView.also {
                    it.layoutManager = LinearLayoutManager(requireContext())
                    it.setHasFixedSize(true)
                    it.adapter = CustomAdapter(activity!!, list.data)
                }
            })
        }
        myViewModel.getDataFromNetwork("health")
        myViewModel.mutableLiveData?.observe(viewLifecycleOwner, Observer { list ->
//            Log.i("DATA",it.data.toString())
            health_recyclerView.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = CustomAdapter(activity!!, list.data)
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_health,container,false)
    }
}