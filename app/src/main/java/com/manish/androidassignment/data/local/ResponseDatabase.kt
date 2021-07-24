package com.manish.androidassignment.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.manish.androidassignment.data.model.ResponseModelItem

@Database(
    entities =[ResponseModelItem::class],
    version = 1,
    exportSchema = false
)
abstract class ResponseDatabase:RoomDatabase() {

    abstract fun responseDao():ResponseDao
}