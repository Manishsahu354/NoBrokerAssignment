package com.manish.androidassignment.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface ResponseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(responseEntity: ResponseEntity)

    @Query("SELECT * FROM response_model_table")
    suspend fun getAllLocalData(): ResponseEntity
}