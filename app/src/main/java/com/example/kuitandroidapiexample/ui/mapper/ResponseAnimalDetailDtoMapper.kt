package com.example.kuitandroidapiexample.ui.mapper

import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDetailDto
import com.example.kuitandroidapiexample.ui.detail.uistate.AnimalDetailUiState

fun ResponseAnimalDetailDto.toUiState() = AnimalDetailUiState(
    url = this.url,
    animalName = this.breed,
    animalType = this.state,
    address = this.address,
    reporterName = this.name
)