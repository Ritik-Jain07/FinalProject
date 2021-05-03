package com.example.finalapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.finalapp.model.CustomAdapter
import com.example.finalapp.model.modelclass.NewsModel

class BookmarksAdapter(private val context: Context,private val list:List<NewsModel>):
    RecyclerView.Adapter<BookmarksAdapter.ViewHolder>(){
    var onItemClick: ((NewsModel) -> Unit)? = null
    var onTitleClick: ((NewsModel) -> Unit)? = null

    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.imageView)
        val title: TextView = view.findViewById(R.id.textView)
        val published: TextView =view.findViewById(R.id.textView2)
        val source: TextView = view.findViewById(R.id.textView1)
        val fav: ImageView = view.findViewById(R.id.favourites)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarksAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BookmarksAdapter.ViewHolder, position: Int) {
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
        holder.title.setOnClickListener { onTitleClick!!.invoke(list[position]) }
        holder.image.setOnClickListener { onItemClick!!.invoke(list[position]) }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}