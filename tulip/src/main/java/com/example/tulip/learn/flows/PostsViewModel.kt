package com.example.tulip.learn.flows

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow

class PostsViewModel : ViewModel() {

    val normalFlow : Flow<List<Post>> = flow {


    }


    private val _stateFlow : MutableStateFlow<List<Post>> = MutableStateFlow(emptyList())
    val stateFlow = _stateFlow.asStateFlow()



    private val _sharedFlow : MutableSharedFlow<List<Post>> = MutableSharedFlow()
    val sharedFlow = _sharedFlow.asSharedFlow()




}