package com.example.kuitandroidapiexample.data.repository

import com.example.kuitandroidapiexample.data.service.AnimalService

class AnimalRepository(
    private val animalService: AnimalService
) {
    suspend fun getAnimal(id: Int) = runCatching { animalService.getAnimalDetail(id) }

    suspend fun deleteAnimal(id: Int) = runCatching { animalService.deleteAnimal(id) }
    //TODO: POST, GET, DELETE 등 구현...
}