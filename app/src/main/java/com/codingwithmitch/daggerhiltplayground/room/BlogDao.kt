package com.codingwithmitch.daggerhiltplayground.room

import androidx.room.*

@Dao
interface BlogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(blogEntity: BlogCacheEntity): Long

    @Query("SELECT * FROM blogs")
    suspend fun get(): List<BlogCacheEntity>

    @Query("DELETE FROM blogs")
    suspend fun delete()
}