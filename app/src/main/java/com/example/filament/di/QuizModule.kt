package com.example.filament.di

import com.example.filament.core.api.ApiInstance
import com.example.filament.data.repository.QuizRepository
import com.example.filament.data.service.QuizApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object QuizModule {

    @Provides
    fun provideQuizService(): QuizApi {
        return ApiInstance.createService(QuizApi::class.java)
    }


    @Provides
    fun provideQuizRepository(quizApi: QuizApi): QuizRepository {
        return QuizRepository(quizApi)
    }

}