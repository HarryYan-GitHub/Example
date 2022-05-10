package com.codingwithmitch.daggerhiltplayground.use_case

import com.codingwithmitch.daggerhiltplayground.model.Blog
import com.codingwithmitch.daggerhiltplayground.repository.MainRepository
import com.codingwithmitch.daggerhiltplayground.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetFromFirebase(
    private val repository: MainRepository
) {

    operator fun invoke(): Flow<Resource<List<Blog>>> {
        return repository.getBlogsFromFirebase()
    }
}