package com.example.tulip.learn.flows

import android.os.Bundle
import androidx.activity.ComponentActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking

class PostsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = PostsViewModel()

        runBlocking {
            viewModel.normalFlow.collect{
                println("Normal Flow --> ${it[0].title} + ${it[0].description} + ${it[0].author}")
            }

            viewModel.stateFlow.collectLatest {
                println("State Flow --> ${it[0].title} + ${it[0].description} + ${it[0].author}")
            }

            viewModel.sharedFlow.collectLatest {
                println("Shared Flow --> ${it[0].title} + ${it[0].description} + ${it[0].author}")
            }
        }
    }
}