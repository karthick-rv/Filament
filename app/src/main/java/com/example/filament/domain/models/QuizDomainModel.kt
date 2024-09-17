package com.example.filament.domain.models

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