package com.codingwithmitch.daggerhiltplayground.firebase

import com.codingwithmitch.daggerhiltplayground.room.BlogCacheEntity

data class BlogRemoteEntity(
    val id: Int? = null,
    val title: String = "",
    val body: String = "",
    val image: String = "",
    val category: String = "",
    val city: CityRemoteEntity? = null
) {
    fun toBlogCacheEntity(): BlogCacheEntity {
        return BlogCacheEntity(
            id = id,
            title = title,
            body = body,
            image = image,
            category = category,
            city = city
        )
    }
}
