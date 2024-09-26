package com.example.tulip.learn.asynchro

import com.example.tulip.utils.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class TyrionViewModelTest {

    lateinit var tyrionViewModel: TyrionViewModel


    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val dispatcher = StandardTestDispatcher() // can use this dispatcher directly as well

    @Before
    fun setup() {
        tyrionViewModel = TyrionViewModel(mainDispatcherRule.dispatcher)

    }

    @Test
    fun should_emit_loading_when_fetch_user_called() = runTest {
        assert(tyrionViewModel.uiState.value == UiState.Initial)

        tyrionViewModel.fetchUser()

        advanceTimeBy(50)

        assert(tyrionViewModel.uiState.value == UiState.Loading)

        advanceUntilIdle()

        assert(
            tyrionViewModel.uiState.value == UiState.Success(
                User(
                    "Karthick",
                    "1231241244",
                    "abc@gmail.com",
                    Address("street", "city", "state", "country", "pinCode")
                )
            )
        )
    }
}