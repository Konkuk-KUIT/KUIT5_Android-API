package com.example.kuitandroidapiexample.ui.register.uistate

import com.example.kuitandroidapiexample.data.dto.request.RequestAddAnimalDto

fun RequestAddAnimalDto.toUiState() = AnimalUiState(
    url = this.url,
    reporterName = this.name,
    address = this.address,
    animalType = this.state,
    animalName = this.breed
)