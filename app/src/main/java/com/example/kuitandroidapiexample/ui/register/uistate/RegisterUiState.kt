package com.example.kuitandroidapiexample.ui.register.uistate

import com.example.kuitandroidapiexample.ui.model.AnimalType

data class RegisterUiState(
    val id: Int = 0,
    val url: String = "",
    val animalName: String = "",
    val reporterName: String = "",
    val address: String = "",
    val animalType: AnimalType = AnimalType.PROTECT,
    val isSubmitted: Boolean = false,
    val errorMessage: String? = null
)
