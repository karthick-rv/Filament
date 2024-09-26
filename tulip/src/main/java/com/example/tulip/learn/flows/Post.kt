package com.example.tulip.learn.flows

import java.sql.Timestamp

data class Post(
    val title: String,
    val description: String,
    val imageUrl: String,
    val author: String,
    val timestamp: Timestamp
)