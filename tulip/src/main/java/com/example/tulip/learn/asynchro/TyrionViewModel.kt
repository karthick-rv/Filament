package com.example.tulip.learn.asynchro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TyrionViewModel(val dispatcher: CoroutineDispatcher = Dispatchers.IO): ViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Initial)
    val uiState: StateFlow<UiState> = _uiState

    init {
//        fetchUser()
    }


    fun fetchUser(){
        viewModelScope.launch(dispatcher) {
            fetchUserAsync()
        }
    }

    private suspend fun fetchUserAsync() {
        withContext(dispatcher){
            _uiState.value = UiState.Loading
            delay(2000)

            val user = User("Karthick", "1231241244", "abc@gmail.com", Address("street", "city", "state", "country", "pinCode"))

            _uiState.value = UiState.Success(user)
        }
    }

}


sealed class UiState{

    object Initial: UiState()
    object Loading: UiState()

    data class Success(val user: User): UiState()
}