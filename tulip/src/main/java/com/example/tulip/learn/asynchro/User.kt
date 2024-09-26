package com.example.tulip.learn.asynchro

data class User(
    val name: String,
    val phone: String,
    val email: String,
    val address: Address
)


data class Address(
    val street: String,
    val city: String,
    val state: String,
    val country: String,
    val pinCode: String
)