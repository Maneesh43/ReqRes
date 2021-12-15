package com.example.reqres.adapters

import android.content.Context
import android.content.Intent
import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.reqres.R
import com.example.reqres.UserActivity
import com.example.reqres.network.models.BaseClass
import com.example.reqres.network.models.UserClass

class ItemAdapter(val context: Context, val dataset:List<UserClass>):
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(view:View):RecyclerView.ViewHolder(view){
        val imageView=view.findViewById<ImageView>(R.id.userImage)
        val text1=view.findViewById<TextView>(R.id.textView1)
        val text2=view.findViewById<TextView>(R.id.textView2)
        val card=view.findViewById<CardView>(R.id.cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout=LayoutInflater.from(parent.context).inflate(R.layout.recycler_item,parent,false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item=dataset[position]
//        Binding data to ui
        Glide.with(context).load(item.avatar).into(holder.imageView)
        holder.text1.text=item.lastName
        holder.text2.text=item.firstName
//        Easy to implement in onBindViewHolder but not recommended as it gets called whenever new cell needs data for simplicity purposes i'm setting listener here
        holder.card.setOnClickListener {
            Intent(context,UserActivity::class.java).also { it.putExtra("id",item.id) }.also{context.startActivity(it)}
        }
    }

    override fun getItemCount(): Int {
       return dataset.size
    }

}