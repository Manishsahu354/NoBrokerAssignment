package com.manish.androidassignment.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.manish.androidassignment.data.model.ResponseModelItem

class ResponseTypeConvertor {

    val gson = Gson()

    @TypeConverter
    fun articleToString(responseModelList: MutableList<ResponseModelItem>): String {
        return gson.toJson(responseModelList)
    }

    @TypeConverter
    fun stringToArticle(data: String): MutableList<ResponseModelItem> {
        val listType = object : TypeToken<MutableList<ResponseModelItem>>() {}.type
        return gson.fromJson(data, listType)
    }

}