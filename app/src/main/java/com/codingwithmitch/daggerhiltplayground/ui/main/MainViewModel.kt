package com.codingwithmitch.daggerhiltplayground.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingwithmitch.daggerhiltplayground.repository.MainRepository
import com.codingwithmitch.daggerhiltplayground.ui.BlogsEvent
import com.codingwithmitch.daggerhiltplayground.ui.UserEvent
import com.codingwithmitch.daggerhiltplayground.use_case.UseCase
import com.codingwithmitch.daggerhiltplayground.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {

//    private val _dataState = MutableLiveData<BlogsEvent>()
//    val dataState: LiveData<BlogsEvent> = _dataState

    private val _dataState = MutableStateFlow(BlogsEvent())
    val dataState: StateFlow<BlogsEvent> = _dataState.asStateFlow()

    init {
        downloadFromFirebase()
    }

    fun onEvent(userEvent: UserEvent) {
        when (userEvent) {
            is UserEvent.DownloadFromApi -> {
//                downloadFromApi()
            }
            is UserEvent.DownloadFromFirebase -> {
                downloadFromFirebase()
            }
            is UserEvent.PostBlog -> {
                useCase.postToFirebase(userEvent.blog, userEvent.name)
            }
        }
    }

//    private fun downloadFromApi() {
//        viewModelScope.launch {
//            useCase.getFromApi().onEach { result ->
//                when (result) {
//                    is Resource.Loading -> {
//                        _dataState.value = BlogsEvent(
//                            blogs = result.data ?: emptyList(),
//                            isLoading = true
//                        )
//                    }
//                    is Resource.Error -> {
//                        _dataState.value = BlogsEvent(
//                            error = result.message ?: "Something went wrong",
//                            blogs = result.data ?: emptyList()
//                        )
//                    }
//                    is Resource.Success -> {
//                        _dataState.value = BlogsEvent(
//                            blogs = result.data ?: emptyList()
//                        )
//                    }
//                }
//            }.launchIn(viewModelScope)
//        }
//    }

    private fun downloadFromFirebase() {
        viewModelScope.launch {
            useCase.getFromFirebase().onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _dataState.value = BlogsEvent(
                            blogs = result.data ?: emptyList(),
                            isLoading = true
                        )
                    }
                    is Resource.Error -> {
                        _dataState.value = BlogsEvent(
                            error = result.message ?: "Something went wrong",
                            blogs = result.data ?: emptyList()
                        )
                    }
                    is Resource.Success -> {
                        _dataState.value = BlogsEvent(
                            blogs = result.data ?: emptyList()
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}