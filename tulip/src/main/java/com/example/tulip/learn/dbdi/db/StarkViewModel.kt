package com.example.tulip.learn.dbdi.db

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StarkViewModel @Inject constructor(val repository: ProductRepository, val dispatcher: CoroutineDispatcher = Dispatchers.IO) : ViewModel() {


    private val _uiState: MutableStateFlow<StarkUiState> = MutableStateFlow(StarkUiState.Initial)
    val uiState = _uiState.asStateFlow()


    fun addProducts() {

        viewModelScope.launch(dispatcher) {

            _uiState.update {
                StarkUiState.Loading
            }

            repository.deleteProduct(1234)

            val result = repository.insertProduct(
                Product(
                    id = 1234,
                    name = "Wooden Table",
                    description = "Foldable Steel",
                    category = "Furniture",
                    price = 1250.0,
                    isFavorite = false
                )
            )

            result.onSuccess {
                val productsResult = repository.getAllProducts()

                productsResult.onSuccess { products ->
                    _uiState.update { StarkUiState.Success(products) }
                }
            }.onFailure {
                _uiState.update { StarkUiState.Failure(it.toString()) }
            }
        }
    }
}

sealed class StarkUiState {


    object Initial : StarkUiState()
    object Loading : StarkUiState()

    data class Success(val products: List<Product>) : StarkUiState()

    data class Failure(val message: String) : StarkUiState()


}