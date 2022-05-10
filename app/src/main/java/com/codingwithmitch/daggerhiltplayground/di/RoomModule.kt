package com.codingwithmitch.daggerhiltplayground.di

import android.app.Application
import androidx.room.Room
import com.codingwithmitch.daggerhiltplayground.room.BlogDao
import com.codingwithmitch.daggerhiltplayground.room.BlogDatabase
import com.codingwithmitch.daggerhiltplayground.room.Converters
import com.codingwithmitch.daggerhiltplayground.utils.GsonParser
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): BlogDatabase {
        return Room.databaseBuilder(
            app,
            BlogDatabase::class.java,
            BlogDatabase.DATABASE_NAME
        )
            .addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }
}