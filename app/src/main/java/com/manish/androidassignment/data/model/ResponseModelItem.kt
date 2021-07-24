package com.manish.androidassignment.data.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.manish.androidassignment.util.Constants
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseModelItem(

    @SerializedName("image")
    val image: String?,
    @SerializedName("subTitle")
    val subTitle: String?,
    @SerializedName("title")
    val title: String?
):Parcelable