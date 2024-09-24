package com.example.filament.presentation.quiz

import com.example.filament.data.repository.QuizRepository
import com.example.filament.data.service.QuizApi
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class QuizViewModelTest{

    val quizApi: QuizApi = mockk<QuizApi>()
    val quizViewModel: QuizViewModel = QuizViewModel(QuizRepository(quizApi))

    @Test
    fun viewModel_should_be_initialized_with_default_data(){
        runBlocking {
            quizViewModel.updateAnswer("")

            val quizState = quizViewModel.quizUiState.first() as QuizUiState.Success

            assert(quizState.data.totalQuestions == 10)
        }
    }



}