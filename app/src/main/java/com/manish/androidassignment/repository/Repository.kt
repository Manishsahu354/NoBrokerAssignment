package com.manish.androidassignment.repository

import androidx.lifecycle.LiveData
import com.manish.androidassignment.data.local.ResponseDao
import com.manish.androidassignment.data.local.ResponseEntity
import com.manish.androidassignment.data.model.ResponseModelItem
import com.manish.androidassignment.data.network.ResponseApi
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    private val responseApi: ResponseApi,
    private val responseDao: ResponseDao
) {

    suspend fun getRemoteResponse(): Response<MutableList<ResponseModelItem>> {
        return responseApi.getRemoteResponse()
    }

    suspend fun insertData(responseEntity: ResponseEntity) {
        responseDao.insertData(responseEntity)
    }

    suspend fun getAllLocalData(): ResponseEntity {
        return responseDao.getAllLocalData()
    }
}