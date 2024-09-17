package com.example.filament.presentation.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.filament.domain.models.Option

@Composable
fun QuizAnswer(option: Option, onClick: ()->Unit) {

    var color = Color.Transparent

    option.isCorrectAnswer?.let {
        color = if(it){
            Color.Green
        }else{
            Color.Red
        }
    }

    Box(
        modifier = Modifier
            .border(2.dp, Color.Black, RoundedCornerShape(20))
            .fillMaxWidth()
            .background(color, shape = RoundedCornerShape(20))
            .clickable {
                onClick()
            }
    ) {
        Text(
            text = option.option, modifier = Modifier
                .padding(20.dp)
        )
    }

}


@Preview
@Composable
fun PreviewQuizAnswer() {
    QuizAnswer(Option("", null)){}
}