package com.codingwithmitch.daggerhiltplayground.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [BlogCacheEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class BlogDatabase: RoomDatabase() {

    abstract val blogDao: BlogDao

    companion object {
        const val DATABASE_NAME = "blog_db"
    }
}