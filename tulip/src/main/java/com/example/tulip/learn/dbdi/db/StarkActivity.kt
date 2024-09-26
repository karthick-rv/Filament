package com.example.tulip.learn.dbdi.db

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tulip.ui.theme.FilamentTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class StarkActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FilamentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    StarkScreen()
                }
            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun StarkScreen(){
    val viewModel: StarkViewModel = hiltViewModel()
    val coroutineScope = rememberCoroutineScope()

    viewModel.addProducts()


    coroutineScope.launch {
        viewModel.uiState.collectLatest {
            when(it){
                is StarkUiState.Failure -> {
                    println("Tulip -- Failed")
                }
                StarkUiState.Initial -> {
                    println("Tulip -- Initial")
                }
                StarkUiState.Loading -> {
                    println("Tulip -- Loading...")
                }
                is StarkUiState.Success -> {
                    println("Tulip -- Product -> " + it.products[0].name)
                }
            }
        }
    }




}