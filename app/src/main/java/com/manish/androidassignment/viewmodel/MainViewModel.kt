package com.manish.androidassignment.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.manish.androidassignment.data.local.ResponseEntity
import com.manish.androidassignment.data.model.ResponseModelItem
import com.manish.androidassignment.repository.Repository
import com.manish.androidassignment.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
):AndroidViewModel(application){

    var responseData: MutableLiveData<NetworkResult<MutableList<ResponseModelItem>>> = MutableLiveData()

    fun getResponseData(){
        viewModelScope.launch {
            responseData.value = NetworkResult.Loading()

            if (hasInternetConnection()){
                val response = repository.getRemoteResponse()
                responseData.value = NetworkResult.Success(response!!)

                val responseEntity = ResponseEntity(response)
                repository.insertData(responseEntity)

            }else{
                val listData = repository.getAllLocalData().value?.responseModelList
                if (!listData.isNullOrEmpty()){
                    responseData.value = NetworkResult.Success(listData)
                }else{
                    responseData.value = NetworkResult.Error("No Internet Connection")
                }
            }
        }
    }

    private fun hasInternetConnection():Boolean{
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        )as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when{
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false;
        }
    }
}