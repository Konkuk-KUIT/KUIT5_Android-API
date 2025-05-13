package com.example.kuitandroidapiexample.ui.home.uistate

import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDto

fun ResponseAnimalDto.toUiState() = AnimalUiState(
    url = this.url,
    animalName = this.breed,
    animalType = this.state,
    address = this.address,
    reporter = this.name
)