package com.example.kuitandroidapiexample.ui.home.uistate

import com.example.kuitandroidapiexample.ui.model.AnimalType

data class AnimalUiState(
    val animalList: List<Animal> = emptyList(),
)

data class Animal(
    val id: Int = 0,
    val name: String = "",
    val type: AnimalType,
    val address: String = "",
    val url: String = "",
)
