package com.manish.androidassignment.ui.adapters

import com.manish.androidassignment.data.model.ResponseModelItem

interface ItemClickListener {
    fun onItemClicked(responseItem: ResponseModelItem)
}