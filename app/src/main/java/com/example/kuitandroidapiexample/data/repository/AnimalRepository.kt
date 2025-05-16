package com.example.kuitandroidapiexample.data.repository

import com.example.kuitandroidapiexample.data.dto.request.RequestAddAnimalDto
import com.example.kuitandroidapiexample.data.service.AnimalService

class AnimalRepository(
    private val animalService: AnimalService
) {
    suspend fun getAnimal(id: Int) = runCatching { animalService.getAnimalDetail(id) }

    suspend fun deleteAnimal(id: Int) = runCatching { animalService.deleteAnimal(id) }

    suspend fun getAnimals() = runCatching { animalService.getTotalAnimalList() }

    suspend fun postAnimal(request: RequestAddAnimalDto) =
        runCatching { animalService.postAddAnimal(request) }
}