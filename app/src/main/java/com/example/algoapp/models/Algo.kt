package com.example.algoapp.models

import java.io.Serializable


data class Algo(
    val id: String,
    val name: String,
    val description: String,
    val descriptionUrl: String,
    val algoImageUrl: String
): Serializable {
}