package com.example.filament.core.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

 val gradientCyanWhite = Brush.linearGradient(
    colors = listOf(Color(0XFF69a2ff), Color.White),
    start = Offset(0f, 0f),
    end = Offset(1500f, 1500f) // adjust as needed
)