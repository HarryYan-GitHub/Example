package com.codingwithmitch.daggerhiltplayground.repository

import android.util.Log
import com.codingwithmitch.daggerhiltplayground.firebase.BlogRemoteEntity
import com.codingwithmitch.daggerhiltplayground.firebase.FirebaseDatabase
import com.codingwithmitch.daggerhiltplayground.model.Blog
import com.codingwithmitch.daggerhiltplayground.retrofit.BlogRetrofit
import com.codingwithmitch.daggerhiltplayground.room.BlogDao
import com.codingwithmitch.daggerhiltplayground.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class MainRepository(
    private val blogDao: BlogDao,
    private val firebaseDb: FirebaseDatabase
) {

    fun getBlogsFromFirebase(): Flow<Resource<List<Blog>>> = flow {
        emit(Resource.Loading())

        val cachedBlogs = blogDao.get().map { it.toBlog() }
        emit(Resource.Loading(data = cachedBlogs))

        try {
            val remoteBlogs = firebaseDb.get()
            blogDao.delete()
            remoteBlogs.map { blogDao.insert(it.toBlogCacheEntity()) }
        } catch (e: HttpException) {
            emit(Resource.Error(
                data = cachedBlogs,
                message = "Something went wrong"
            ))
        } catch (e: IOException) {
            emit(Resource.Error(
                data = cachedBlogs,
                message = "Something went wrong"
            ))
        }

        val newBlogs = blogDao.get().map { it.toBlog() }
        emit(Resource.Success(
            data = newBlogs
        ))
    }

//    suspend fun getBlogsFromApi(): Flow<Resource<List<Blog>>> = flow {
//        emit(Resource.Loading())
//
//        val cachedBlogs = blogDao.get().map { it.toBlog() }
//        emit(Resource.Loading(data = cachedBlogs))
//
//        try {
//            val remoteBlogs = blogRetrofit.get()
//            blogDao.delete()
//            remoteBlogs.map { blogDao.insert(it.toBlogCacheEntity()) }
//        } catch (e: HttpException) {
//            emit(
//                Resource.Error(
//                    message = "Something went wrong",
//                    data = cachedBlogs
//                )
//            )
//        } catch (e: IOException) {
//            emit(
//                Resource.Error(
//                    message = "Couldn't reach to the server, please check your internet connection",
//                    data = cachedBlogs
//                )
//            )
//        }
//
//        val newBlogs = blogDao.get().map { it.toBlog() }
//        emit(Resource.Success(data = newBlogs))
//    }

    fun postBlogToFirebase(blog: BlogRemoteEntity, name: String) {
        firebaseDb.post(blog, name)
    }
}