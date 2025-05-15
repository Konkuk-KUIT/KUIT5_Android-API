package com.example.kuitandroidapiexample.ui.home.uistate

import com.example.kuitandroidapiexample.ui.model.AnimalType

data class AnimalUiState(
    val id: Int = 0,
    val url: String = "",
    val animalName: String = "",
    val animalType: AnimalType = AnimalType.PROTECT,
    val address: String = "",
    val reporter: String = "",
)
