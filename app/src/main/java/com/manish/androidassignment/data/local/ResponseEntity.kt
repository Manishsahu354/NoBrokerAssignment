package com.manish.androidassignment.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.manish.androidassignment.data.model.ResponseModelItem
import com.manish.androidassignment.util.Constants

@Entity(tableName = Constants.RESPONSE_MODEL_TABLE)
class ResponseEntity(
    val responseModelList: MutableList<ResponseModelItem>
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}