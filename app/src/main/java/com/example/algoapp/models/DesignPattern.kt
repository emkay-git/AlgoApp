package com.example.algoapp.models

import com.example.algoapp.utils.Constants

data class DesignPattern (
     val id: Int,
     val name: String,
     val type: String,
     val classDiagramUrl: String,
     val intentDesc: String
) {
    val imageUrl
        get() = "${Constants.DESING_PATTERN_IMAGE_URL}$classDiagramUrl"
}