package com.example.kuitandroidapiexample.ui.home.uistate

import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDto

data class AnimalListUiState(
    val animalList: List<ResponseAnimalDto> = emptyList()
)