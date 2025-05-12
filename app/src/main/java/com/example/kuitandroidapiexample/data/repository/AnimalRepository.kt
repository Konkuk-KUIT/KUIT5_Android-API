package com.example.kuitandroidapiexample.data.repository

import com.example.kuitandroidapiexample.data.dto.request.RequestAddAnimalDto
import com.example.kuitandroidapiexample.data.service.AnimalService

class AnimalRepository(
    private val animalService: AnimalService
) {
    suspend fun getAnimal(id: Int) = runCatching { animalService.getAnimalDetail(id) }

    suspend fun deleteAnimal(id: Int) = runCatching { animalService.deleteAnimal(id) }

    suspend fun getTotalAnimal() = runCatching { animalService.getTotalAnimalList() }

    suspend fun registerAnimal(
        request: RequestAddAnimalDto
    ) = runCatching { animalService.postAddAnimal(request) }
}