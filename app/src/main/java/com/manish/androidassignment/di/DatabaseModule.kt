package com.manish.androidassignment.di

import android.content.Context
import androidx.room.Room
import com.manish.androidassignment.util.Constants.ROOM_DATABASE_NAME
import com.manish.androidassignment.data.local.ResponseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): ResponseDatabase = Room.databaseBuilder(
        context,
        ResponseDatabase::class.java,
        ROOM_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideDao(database: ResponseDatabase) = database.responseDao()


}