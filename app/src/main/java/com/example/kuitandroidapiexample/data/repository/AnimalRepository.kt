// data/AnimalRepository.kt
package com.example.kuitandroidapiexample.data

import com.example.kuitandroidapiexample.data.dto.request.RequestAddAnimalDto
import com.example.kuitandroidapiexample.data.service.AnimalService

class AnimalRepository(
    private val animalService: AnimalService
) {
    suspend fun getAnimal(id: Int) = runCatching { animalService.getAnimalDetail(id) }
    suspend fun getTotalAnimalList() = runCatching { animalService.getTotalAnimalList() }
    suspend fun addAnimal(request: RequestAddAnimalDto) = runCatching { animalService.postAddAnimal(request) }
    suspend fun deleteAnimal(id: Int) = runCatching { animalService.deleteAnimal(id) }

}