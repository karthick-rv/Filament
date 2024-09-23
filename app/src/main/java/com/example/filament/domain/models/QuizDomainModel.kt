package com.example.filament.domain.models

import com.example.filament.data.models.response.QuizItem
import com.example.filament.data.models.response.QuizResponse

data class QuizDomainModel(
    val questionNumber: Int,
    val totalQuestions: Int,
    val quizList: List<QuizQuestionModel>
)


data class QuizQuestionModel(
    val question: String,
    val options: List<Option>,
    val answer: String
)


data class Option(
    val option: String,
    val isCorrectAnswer: Boolean?
)


fun toQuizDomainModel(model: QuizResponse): QuizDomainModel {
    return QuizDomainModel(
        questionNumber = 0,
        totalQuestions = model.results.size,
        quizList = model.results.map {
            QuizQuestionModel(
                it.question,
                toOptions(it),
                it.correctAnswer
            )
        }
    )
}


private fun toOptions(quizItem: QuizItem): List<Option> {
    val list = mutableListOf<Option>()
    quizItem.incorrectAnswers.map {
        list.add(Option(it, null))
    }
    list.add(Option(quizItem.correctAnswer, null))
    list.shuffle()

    return list
}