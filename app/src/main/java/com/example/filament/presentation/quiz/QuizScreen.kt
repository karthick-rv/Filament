package com.example.filament.presentation.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.filament.core.theme.gradientCyanWhite
import com.example.filament.presentation.components.BackButton


@Composable
fun QuizScreen(
    navController: NavHostController,
    viewModel: QuizViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    val quizState by viewModel.quizUiState.collectAsState()
    val timerCount by viewModel.timerCount.collectAsState()


    when (val screenState = quizState) {
        is QuizUiState.Error -> TODO()
        is QuizUiState.Success -> {
            if (screenState.data.quizList.isEmpty()) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .background(gradientCyanWhite),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Hold tight.. Loading Quiz for you",
                        fontSize = TextUnit(20F, TextUnitType.Sp),
                        color = Color.Blue
                    )
                }

            } else if (screenState.data.questionNumber >= screenState.data.quizList.size) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .background(gradientCyanWhite),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "You are done with the quiz. Thank you",
                        fontSize = TextUnit(20F, TextUnitType.Sp),
                        color = Color.Blue
                    )
                }
            } else {
                val quiz = screenState.data.quizList[screenState.data.questionNumber]
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            gradientCyanWhite
                        )
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        BackButton(size = 40.dp) {
                            navController.navigateUp()
                        }

                        if (timerCount > 0) {
                            CircularProgressIndicator(
                                progress = timerCount * 0.025F,
                                color = Color.Green,
                                trackColor = Color.Transparent,
                            )
                        }


                        Text(
                            text = "${screenState.data.questionNumber + 1}/10",
                            modifier = Modifier
                                .border(2.dp, Color.Black, RoundedCornerShape(50))
                                .padding(vertical = 10.dp, horizontal = 15.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(40.dp))

                    LinearProgressIndicator(
                        progress = (screenState.data.questionNumber) / 10F,
                        color = Color.Green,
                        trackColor = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(18.dp)
                            .border(2.dp, Color.Black, shape = RoundedCornerShape(10))
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    QuizQuestion(quiz.question)

                    Spacer(modifier = Modifier.height(40.dp))


                    quiz.options.map {
                        QuizAnswer(it) {
                            viewModel.updateAnswer(quiz.answer)
                            viewModel.nextQuiz()
                        }

                        Spacer(modifier = Modifier.height(30.dp))
                    }
                }
            }
        }
    }


}


@Preview
@Composable
fun PreviewQuizScreen() {
    val navController = rememberNavController()
    QuizScreen(navController = navController)
}


/*
* Todo
*  Quiz interval timer
*  Events and Data wrapper
*  Dagger Hilt
*  Room Database - offline storage
*  Testing
* */