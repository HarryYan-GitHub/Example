package com.codingwithmitch.daggerhiltplayground.ui

import com.codingwithmitch.daggerhiltplayground.model.Blog

data class BlogsEvent(
    val blogs: List<Blog> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
