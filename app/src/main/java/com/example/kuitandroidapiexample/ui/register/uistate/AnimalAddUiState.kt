package com.example.kuitandroidapiexample.ui.register.uistate

import com.example.kuitandroidapiexample.ui.model.AnimalType

data class AnimalAddUiState(
    val url: String = "",
    val reporter: String = "",
    val address: String = "",
    val animalName: String = "",
    val animalType: AnimalType = AnimalType.PROTECT,
    var isAdded: Boolean = false
)
