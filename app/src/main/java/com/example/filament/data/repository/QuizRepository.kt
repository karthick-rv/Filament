package com.example.filament.data.repository

import com.example.filament.core.util.Result
import com.example.filament.data.models.response.QuizResponse
import com.example.filament.data.service.QuizApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class QuizRepository @Inject constructor(val remoteQuizApi: QuizApi) {


    suspend fun getQuizCollection(): Flow<Result<QuizResponse>> = flow {
        try {
            val response = remoteQuizApi.getQuizCollection(10).execute()

            if (response.isSuccessful) {
                response.body()?.let { emit(Result.Success(it)) } ?: emit(Result.Error("Something went wrong"))
            } else {
                emit(Result.Error("Something went wrong"))
            }
        } catch (ex: Exception) {
            println("Exception - ${ex}")
            emit(Result.Error("Something went wrong"))
        }
    }

}