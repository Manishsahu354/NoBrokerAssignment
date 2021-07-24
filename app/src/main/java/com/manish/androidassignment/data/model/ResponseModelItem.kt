package com.manish.androidassignment.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.manish.androidassignment.util.Constants


data class ResponseModelItem(

    @SerializedName("image")
    val image: String?,
    @SerializedName("subTitle")
    val subTitle: String?,
    @SerializedName("title")
    val title: String?
)