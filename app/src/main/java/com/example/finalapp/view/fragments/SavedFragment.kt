package com.example.finalapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalapp.viewmodel.BookmarkViewModel
import com.example.finalapp.R
import com.example.finalapp.adapter.CustomAdapter
import kotlinx.android.synthetic.main.fragment_saved.*

class SavedFragment:Fragment() {

    val TAG = SavedFragment::class.java.simpleName
    private lateinit var bookmarkViewModel: BookmarkViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_saved,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookmarkViewModel = ViewModelProvider(this).get(BookmarkViewModel::class.java)


        bookmarkViewModel.getDataFromDatabase()
        bookmarkViewModel.liveDataRoom.observe(viewLifecycleOwner,{ data ->
            saved_recyclerView.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = CustomAdapter(activity!!,data){ roomData ->
                    if(roomData.isFav){
                        bookmarkViewModel.addAsFav(roomData)
                    }else{
                        bookmarkViewModel.deleteFav(roomData)
                    }
                }
                if(data.isEmpty()){
                Toast.makeText(activity,"No Data Found",Toast.LENGTH_LONG).show()
            }
            }
        })
}
}