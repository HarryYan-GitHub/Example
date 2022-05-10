package com.codingwithmitch.daggerhiltplayground.firebase

import android.util.Log
import com.codingwithmitch.daggerhiltplayground.room.BlogCacheEntity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirebaseDatabase {

    private val firestore = FirebaseFirestore.getInstance()
    private val blogCollection = firestore.collection("BLOG_COLLECTION")

    suspend fun get(): List<BlogRemoteEntity> {
        return try {
            blogCollection.get().await().toObjects(BlogRemoteEntity::class.java)
        } catch (e: Exception) {
            Log.i("Super","Exception occurred.")
            emptyList()
        }
    }

    fun post(blog: BlogRemoteEntity, name: String) {
        blogCollection.document(name).set(blog)
    }
}