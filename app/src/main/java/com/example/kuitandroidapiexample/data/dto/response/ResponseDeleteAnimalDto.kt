package com.example.kuitandroidapiexample.data.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseDeleteAnimalDto(
    val message: String,
    val data: DeleteAnimalData
)

@Serializable
data class DeleteAnimalData(
    val id: Int
)