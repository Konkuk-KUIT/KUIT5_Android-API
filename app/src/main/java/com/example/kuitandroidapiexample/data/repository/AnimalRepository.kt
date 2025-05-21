package com.example.kuitandroidapiexample.data.repository

import com.example.kuitandroidapiexample.data.dto.request.RequestAddAnimalDto
import com.example.kuitandroidapiexample.data.service.AnimalService

class AnimalRepository (
    private val animalService: AnimalService
) {

    suspend fun getTotalAnimalList() = runCatching { animalService.getTotalAnimalList() }

    suspend fun getAnimal(id: Int) = runCatching { animalService.getAnimalDetail(id) }

    suspend fun deleteAnimal(id: Int) = runCatching { animalService.deleteAnimal(id) }

    suspend fun addAnimal(request: RequestAddAnimalDto): Result<Unit> = runCatching { animalService.postAddAnimal(request) }


}