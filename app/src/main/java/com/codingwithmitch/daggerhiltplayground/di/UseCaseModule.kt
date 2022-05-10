package com.codingwithmitch.daggerhiltplayground.di

import com.codingwithmitch.daggerhiltplayground.repository.MainRepository
import com.codingwithmitch.daggerhiltplayground.use_case.GetFromApi
import com.codingwithmitch.daggerhiltplayground.use_case.GetFromFirebase
import com.codingwithmitch.daggerhiltplayground.use_case.PostToFirebase
import com.codingwithmitch.daggerhiltplayground.use_case.UseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideUseCase(repository: MainRepository): UseCase {
        return UseCase(
            getFromApi = GetFromApi(repository),
            getFromFirebase = GetFromFirebase(repository),
            postToFirebase = PostToFirebase(repository)
        )
    }
}