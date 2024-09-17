package com.example.filament.presentation.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun QuizQuestion(question: String) {

    Box(
        modifier = Modifier
            .border(2.dp, Color.Black, RoundedCornerShape(20))
            .background(Color.Cyan, shape = RoundedCornerShape(20))
    ) {
        Text(
            text = question,
            modifier = Modifier
                .padding(30.dp)
        )
    }

}


@Preview
@Composable
fun PreviewQuizQuestion() {
    QuizQuestion("Foie gras is a French delicacy typically made from what part of a duck or goose?")
}