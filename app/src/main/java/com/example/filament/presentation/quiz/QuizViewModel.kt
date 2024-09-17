package com.example.filament.presentation.quiz

import androidx.lifecycle.ViewModel
import com.example.filament.domain.models.Option
import com.example.filament.domain.models.QuizDomainModel
import com.example.filament.domain.models.QuizQuestionModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Random

class QuizViewModel : ViewModel() {

    private var _quizDomainModel: MutableStateFlow<QuizDomainModel> = MutableStateFlow(createData())
    val quizDomainModel: StateFlow<QuizDomainModel> = _quizDomainModel


    private fun createData(): QuizDomainModel {

        val options = mutableListOf(
            Option("Chris Benoit", null),
            Option("Lex Luger", null),
            Option("Al Snow", null)
        )
        options.add(Option("Owen Hart", null))
        val quiz =
            QuizQuestionModel(
                question = "Which professional wrestler fell from the rafters to his death during a live Pay-Per-View event in 1999?",
                answer = "Owen Hart",
                options = options.shuffled()
            )
        return QuizDomainModel(questionNumber = 0, totalQuestions = 10, quizList = listOf(quiz));
    }

    public fun updateAnswer(answer: String) {
        _quizDomainModel.value =
            quizDomainModel.value.copy(quizList = quizDomainModel.value.quizList.map {
                it.copy(options = it.options.map { it.copy(isCorrectAnswer = answer == it.option) })
            })
    }

}