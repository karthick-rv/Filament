package com.example.filament.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.filament.R


@Composable
fun BackButton(size: Dp, onClick: () -> Unit) {
    return IconButton(
        onClick = onClick, modifier = Modifier
            .size(size)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.chevron_left), // Replace with your icon resource
            contentDescription = "Sample Icon",
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0XFF69a2ff))
                .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(100)),
            tint = Color.Black,
        )
    }
}