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
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
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

    val quizDomainModel by viewModel.quizDomainModel.collectAsState()
    val quiz = quizDomainModel.quizList[quizDomainModel.questionNumber]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                gradientCyanWhite
            )
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            BackButton(size = 40.dp) {
                navController.navigateUp()
            }

            Text(
                text = "0/10",
                modifier = Modifier
                    .border(2.dp, Color.Black, RoundedCornerShape(50))
                    .padding(vertical = 10.dp, horizontal = 15.dp)
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        LinearProgressIndicator(
            progress = 0.3F,
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
            QuizAnswer(it){
                viewModel.updateAnswer(quiz.answer)
            }

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}


@Preview
@Composable
fun PreviewQuizScreen() {
    val navController = rememberNavController()
    QuizScreen(navController = navController)
}
