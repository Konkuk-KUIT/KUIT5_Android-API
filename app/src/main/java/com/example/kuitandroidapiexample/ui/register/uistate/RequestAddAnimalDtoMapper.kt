package com.example.kuitandroidapiexample.ui.register.uistate

import com.example.kuitandroidapiexample.data.dto.request.RequestAddAnimalDto

fun AnimalAddUiState.toDto() = RequestAddAnimalDto(
    url = this.url,
    name = this.reporter,
    state = this.animalType,
    breed = this.animalName,
    address = this.address
)

