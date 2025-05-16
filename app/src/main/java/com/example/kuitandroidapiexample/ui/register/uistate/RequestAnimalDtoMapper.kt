package com.example.kuitandroidapiexample.ui.register.uistate

import com.example.kuitandroidapiexample.data.dto.request.RequestAddAnimalDto

fun RequestAddAnimalDto.toUiState() = AnimalRegisterUiState(
    url = this.url,
    animalName = this.breed,
    animalType = this.state,
    address = this.address,
    reporterName = this.name,
)