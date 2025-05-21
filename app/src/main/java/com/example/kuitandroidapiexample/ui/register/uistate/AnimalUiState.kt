package com.example.kuitandroidapiexample.ui.register.uistate

import com.example.kuitandroidapiexample.ui.model.AnimalType

data class AnimalUiState(
    val url: String = "",
    val reporterName: String = "",
    val address: String = "",
    val animalType: AnimalType = AnimalType.PROTECT,
    val animalName: String = "",
    var isAdded: Boolean = false
)