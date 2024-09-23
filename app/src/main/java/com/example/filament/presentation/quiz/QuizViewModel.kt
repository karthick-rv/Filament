package com.example.filament.presentation.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filament.core.api.ApiInstance
import com.example.filament.core.util.Result
import com.example.filament.data.repository.QuizRepository
import com.example.filament.data.service.QuizApi
import com.example.filament.domain.models.QuizDomainModel
import com.example.filament.domain.models.toQuizDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class QuizViewModel : ViewModel() {

    private var _quizUiState: MutableStateFlow<QuizUiState> = MutableStateFlow(createData())
    val quizUiState: StateFlow<QuizUiState> = _quizUiState


    private var _timerCount: MutableStateFlow<Int> = MutableStateFlow(0)
    val timerCount: StateFlow<Int> = _timerCount


    private var fetchJob: Job? = null

    init {
        fetchQuiz()
    }


    private fun createData(): QuizUiState {
        return QuizUiState.Success(
            message = "",
            data = QuizDomainModel(questionNumber = 0, totalQuestions = 10, quizList = emptyList())
        )
    }

    fun updateAnswer(answer: String) {

        val value = quizUiState.value as QuizUiState.Success
        val model = value.data

        _quizUiState.update { uiState ->
            when(uiState){
                is QuizUiState.Error -> uiState
                is QuizUiState.Success -> {
                    uiState.copy(data = model.copy(quizList = model.quizList.map {
                        it.copy(options = it.options.map { it.copy(isCorrectAnswer = answer == it.option) })
                    }))
                }
            }
        }
    }
        fun nextQuiz() {
            viewModelScope.launch {
                startTimer()
                val model = quizUiState.value as QuizUiState.Success
                _quizUiState.update { uiState ->

                    when(uiState){
                        is QuizUiState.Error -> uiState
                        is QuizUiState.Success -> {
                            uiState.copy(data = model.data.copy(questionNumber = model.data.questionNumber + 1,
                                quizList = model.data.quizList.map {
                                    it.copy(options = it.options.map { option -> option.copy(isCorrectAnswer = null) })
                                }))
                        }
                    }


                }
                stopTimer()
            }
        }

        private fun fetchQuiz() {
            val repository = QuizRepository(ApiInstance.createService(QuizApi::class.java))
            fetchJob?.cancel()

            fetchJob = viewModelScope.launch(Dispatchers.IO) {
                repository.getQuizCollection().collect { result ->

                    when(result){
                        is Result.Error -> {
                            QuizUiState.Error(result.message)
                        }
                        is Result.Success -> {
                            _quizUiState.update {uiState ->
                                when(uiState){
                                    is QuizUiState.Error -> uiState
                                    is QuizUiState.Success -> {
                                        val domainModel = toQuizDomainModel(result.data)
                                        uiState.copy(
                                            data = uiState.data.copy(questionNumber = domainModel.questionNumber,
                                                totalQuestions = domainModel.totalQuestions,
                                                quizList = domainModel.quizList),
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        private suspend fun startTimer() {
            if (_timerCount.value <= 40) {
                delay(50)
                _timerCount.value = _timerCount.value + 1
                startTimer()
            }
        }

        private fun stopTimer() {
            _timerCount.value = 0
        }
    }




sealed class QuizUiState{
    object Loading
    data class Success(val message: String?, val data: QuizDomainModel): QuizUiState()

    data class Error(val errorMessage: String): QuizUiState()

}