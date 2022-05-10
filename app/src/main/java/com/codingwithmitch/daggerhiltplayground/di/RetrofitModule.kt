package com.codingwithmitch.daggerhiltplayground.di

import com.codingwithmitch.daggerhiltplayground.retrofit.BlogRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofit(): BlogRetrofit {
        return Retrofit.Builder()
            .baseUrl("https://open-api.xyz./placeholder/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BlogRetrofit::class.java)
    }
}