package com.example.kuitandroidapiexample.detail.uistate

import com.example.kuitandroidapiexample.data.repository.AnimalRepository
import com.example.kuitandroidapiexample.model.AnimalType
import okhttp3.Address

data class AnimalDetailUiState(
    val url: String ="",
    val animalName: String="",
    val animalType: AnimalType= AnimalType.PROTECT,
    val address: String="",
    val reporterName: String="",
    val isDelete: Boolean=false
)
