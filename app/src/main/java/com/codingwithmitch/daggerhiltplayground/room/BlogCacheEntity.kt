package com.codingwithmitch.daggerhiltplayground.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.codingwithmitch.daggerhiltplayground.firebase.CityRemoteEntity
import com.codingwithmitch.daggerhiltplayground.model.Blog

@Entity(tableName = "blogs")
data class BlogCacheEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "body")
    val body: String,
    @ColumnInfo(name = "category")
    val category: String,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "city")
    val city: CityRemoteEntity? = null
) {
    fun toBlog(): Blog {
        return Blog(
            id = id!!,
            title = title,
            body = body,
            category = category,
            image = image,
            city = city
        )
    }
}
