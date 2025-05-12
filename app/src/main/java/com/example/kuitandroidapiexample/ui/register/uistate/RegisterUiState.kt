package com.example.kuitandroidapiexample.ui.register.uistate

import com.example.kuitandroidapiexample.ui.model.AnimalType

data class RegisterUiState(
    val url: String = "",
    val name: String = "",
    val address: String = "",
    val breed: String = "",
    val type: AnimalType = AnimalType.PROTECT,
    val isAdded: Boolean = false
)
