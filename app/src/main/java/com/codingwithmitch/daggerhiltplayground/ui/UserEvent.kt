package com.codingwithmitch.daggerhiltplayground.ui

import com.codingwithmitch.daggerhiltplayground.firebase.BlogRemoteEntity

sealed class UserEvent {
    object DownloadFromApi : UserEvent()
    object DownloadFromFirebase : UserEvent()
    data class PostBlog(
        val blog: BlogRemoteEntity,
        val name: String
    ) : UserEvent()
}
