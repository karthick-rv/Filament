package com.example.tulip.learn.dbdi.db

import com.example.tulip.utils.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class StarkViewModelTest{

    private lateinit var starkViewModel: StarkViewModel


    private lateinit var productRepository: ProductRepository


    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup(){
        productRepository = mockk{
            coEvery { deleteProduct(1234)  } returns Unit
            coEvery { insertProduct(Product(
                id = 1234,
                name = "Wooden Table",
                description = "Foldable Steel",
                category = "Furniture",
                price = 1250.0,
                isFavorite = false
            )) } returns Result.success(Unit)
            coEvery { getAllProducts() } returns Result.success(listOf(Product(
                id = 1234,
                name = "Wooden Table",
                description = "Foldable Steel",
                category = "Furniture",
                price = 1250.0,
                isFavorite = false
            )))
        }
        starkViewModel = StarkViewModel(productRepository, mainDispatcherRule.dispatcher)
    }


    @Test
    fun `test add products`() = runTest{

        assertEquals(StarkUiState.Initial, starkViewModel.uiState.value)

        starkViewModel.addProducts()

        advanceUntilIdle()

        assertEquals(StarkUiState.Success(listOf(Product(
            id = 1234,
            name = "Wooden Table",
            description = "Foldable Steel",
            category = "Furniture",
            price = 1250.0,
            isFavorite = false
        ))), starkViewModel.uiState.value)

    }



}