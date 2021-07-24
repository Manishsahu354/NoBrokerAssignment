package com.manish.androidassignment.repository

import androidx.lifecycle.LiveData
import com.manish.androidassignment.data.local.ResponseDao
import com.manish.androidassignment.data.local.ResponseEntity
import com.manish.androidassignment.data.model.ResponseModelItem
import com.manish.androidassignment.data.network.ResponseApi
import javax.inject.Inject

class Repository @Inject constructor(
    private val responseApi: ResponseApi,
    private val responseDao: ResponseDao
) {

    suspend fun getRemoteResponse(): MutableList<ResponseModelItem>? {
        return responseApi.getRemoteResponse().body()
    }

    suspend fun insertData(responseEntity: ResponseEntity){
        responseDao.insertData(responseEntity)
    }

    fun getAllLocalData():  LiveData<ResponseEntity>{
        return responseDao.getAllLocalData()
    }
}