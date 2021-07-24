package com.manish.androidassignment.data.network

import com.manish.androidassignment.util.Constants.RESPONSE_END_URL
import com.manish.androidassignment.data.model.ResponseModel
import com.manish.androidassignment.data.model.ResponseModelItem
import retrofit2.Response
import retrofit2.http.GET

interface ResponseApi {

    @GET(RESPONSE_END_URL)
    suspend fun getRemoteResponse(
    ): Response<MutableList<ResponseModelItem>>

}