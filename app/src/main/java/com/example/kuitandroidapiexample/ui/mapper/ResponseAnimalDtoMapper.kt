package com.example.kuitandroidapiexample.ui.mapper

import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDto
import com.example.kuitandroidapiexample.ui.home.uistate.Animal
import com.example.kuitandroidapiexample.ui.home.uistate.AnimalUiState

fun List<ResponseAnimalDto>.toUiState() = AnimalUiState(
    animalList = this.map {
        it.toUiState()
    }
)

fun ResponseAnimalDto.toUiState() = Animal(
    id = this.id,
    url = this.url,
    name = this.breed,
    type = this.state,
    address = this.address,
)