package com.codingwithmitch.daggerhiltplayground.model

import com.codingwithmitch.daggerhiltplayground.firebase.CityRemoteEntity

data class Blog(
    val id: Int,
    val title: String,
    val body: String,
    val image: String,
    val category: String,
    val city: CityRemoteEntity? = null
)
