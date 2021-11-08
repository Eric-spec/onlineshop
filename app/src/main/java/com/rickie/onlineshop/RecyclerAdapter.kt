package com.rickie.onlineshop

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import kotlinx.android.synthetic.main.single_item.view.*
import java.util.*
import kotlin.collections.ArrayList

//below line connects to model
//it creates a list of items

class RecyclerAdapter(var context: Context, var newsList: ArrayList<model>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    //holds the 3 Textviews and 1 imageview in single item
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    //below lines link to single item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = newsList[position] //
        //it automatically loops to other position in  news list
        holder.itemView.txt_title.text = item.title
        holder.itemView.txt_date.text = "KES: "+item.date
        holder.itemView.txt_desc.text = item.description
        //holder.itemView.txt_title.text = item.title
        //holder.itemView.imageView.setImageResource(item.image)
        Glide.with(context)
            .load(item.image)
            .override(300,200)
            .into(holder.itemView.imageView)

        holder.itemView.setOnClickListener {
            Toast.makeText(context,"Clicked", Toast.LENGTH_LONG).show()
            //share preferences
            val prefs:SharedPreferences = context.getSharedPreferences("store",Context.MODE_PRIVATE)

            val editor: SharedPreferences.Editor = prefs.edit()
            editor.putString("title", item.title)
            editor.putString("description", item.description)
            editor.putString("cost", item.date)
            editor.putString("image_url", item.image)
            editor.apply()

            val i = Intent(context, SingleActivity::class.java)
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(i)

        }

    }
     //count how many items are in that list
    override fun getItemCount() = newsList.size
}