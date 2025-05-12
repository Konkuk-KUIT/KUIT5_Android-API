package com.example.kuitandroidapiexample.ui.mapper

import com.example.kuitandroidapiexample.data.dto.request.RequestAddAnimalDto
import com.example.kuitandroidapiexample.ui.register.uistate.RegisterUiState

fun RegisterUiState.toRequestAddAnimalDto(): RequestAddAnimalDto {
    return RequestAddAnimalDto(
        name = this.name,
        url = this.url,
        state = this.type,
        breed = this.breed,
        address = this.address,
    )
}