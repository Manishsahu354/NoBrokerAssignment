package com.manish.androidassignment.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.manish.androidassignment.R
import com.manish.androidassignment.data.model.ResponseModelItem
import java.util.*

class ResponseAdapter(
    private var responseList: MutableList<ResponseModelItem>,
    private val listener: ItemClickListener
) : RecyclerView.Adapter<ResponseAdapter.ResponseViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResponseViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.response_item_layout, parent, false)

        return ResponseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResponseViewHolder, position: Int) {

        val response = responseList[position]



        holder.apply {
            Glide.with(responseImage).load(response.image).into(responseImage)

            responseTitle.text = response.title
            responseSubtitle.text = response.subTitle

            responseItem.setOnClickListener {
                listener.onItemClicked(response)
            }
        }
    }

    override fun getItemCount(): Int {
        return responseList.size
    }

    fun setData(newData: MutableList<ResponseModelItem>) {
        responseList = newData
        notifyDataSetChanged()
    }


    fun filterList(filteredList: MutableList<ResponseModelItem>) {
        responseList = filteredList
        notifyDataSetChanged()
    }


    class ResponseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val responseImage: ImageView = itemView.findViewById(R.id.responseImage)
        val responseTitle: TextView = itemView.findViewById(R.id.responseTitle)
        val responseSubtitle: TextView = itemView.findViewById(R.id.responseSubtitle)
        val responseItem: ConstraintLayout = itemView.findViewById(R.id.responseItem)
    }


}