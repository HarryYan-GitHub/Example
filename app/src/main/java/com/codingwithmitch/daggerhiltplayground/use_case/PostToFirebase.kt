package com.codingwithmitch.daggerhiltplayground.use_case

import com.codingwithmitch.daggerhiltplayground.firebase.BlogRemoteEntity
import com.codingwithmitch.daggerhiltplayground.repository.MainRepository

class PostToFirebase(
    private val repository: MainRepository
) {

    operator fun invoke(blog: BlogRemoteEntity, name: String) {
        repository.postBlogToFirebase(blog, name)
    }
}