package com.manish.androidassignment.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.manish.androidassignment.R
import com.manish.androidassignment.data.model.ResponseModel
import com.manish.androidassignment.data.model.ResponseModelItem

class ResponseAdapter(private var responseList:MutableList<ResponseModelItem>):RecyclerView.Adapter<ResponseAdapter.ResponseViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResponseViewHolder {
        val view:View= LayoutInflater.from(parent.context)
            .inflate(R.layout.response_item_layout,parent,false)

        return ResponseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResponseViewHolder, position: Int) {
       holder.apply {
           Glide.with(responseImage).load(responseList[position].image).into(responseImage)

           responseTitle.text = responseList[position].title
           responseSubtitle.text = responseList[position].subTitle
       }
    }

    override fun getItemCount(): Int {
        return responseList.size
    }

    fun setData(newData:MutableList<ResponseModelItem>){
        responseList = newData
        notifyDataSetChanged()
    }


    class ResponseViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val responseImage: ImageView = itemView.findViewById(R.id.responseImage)
        val responseTitle: TextView = itemView.findViewById(R.id.responseTitle)
        val responseSubtitle: TextView = itemView.findViewById(R.id.responseSubtitle)
    }
}