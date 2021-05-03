package com.example.finalapp.model

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.finalapp.R
import com.example.finalapp.WebActivity
import com.example.finalapp.model.modelclass.DataBaseNewsModel
import com.example.finalapp.model.modelclass.NewsModel
import com.example.finalapp.viewmodel.MyViewModel

class CustomAdapter(private val context: Context, var list: List<NewsModel>,private val onItemClick: (NewsModel) -> Unit,):
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val image: ImageView = view.findViewById(R.id.imageView)
        val title: TextView = view.findViewById(R.id.textView)
        val published:TextView=view.findViewById(R.id.textView2)
        val source: TextView = view.findViewById(R.id.textView1)
        val fav:ImageView = view.findViewById(R.id.favourites)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = list[position].title
        holder.source.text = list[position].source
        holder.published.text=list[position].published
        val img = list[position].image
        Glide.with(context).load(img
        ).placeholder(R.drawable.ic_launcher_foreground).into(holder.image)

        if(list[position].isFav){
            holder.fav.setImageResource(R.drawable.ic_bookmark_added)
        }else{
            holder.fav.setImageResource(R.drawable.ic_bookmark_remove)
        }
        holder.title.setOnClickListener {
            val intent = Intent(holder.itemView.context, WebActivity::class.java)
            intent.putExtra("url", list[position].url)
            holder.itemView.context.startActivity(intent)
        }

        holder.image.setOnClickListener {
            list[position].isFav=!list[position].isFav
            notifyItemChanged(position)
            onItemClick(list[position])}
    }

    override fun getItemCount(): Int {
        return list.size
    }

}