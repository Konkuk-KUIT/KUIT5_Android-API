package com.example.kuitandroidapiexample.data.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kuitandroidapiexample.data.dto.request.RequestAddAnimalDto
import com.example.kuitandroidapiexample.data.service.AnimalService
import com.example.kuitandroidapiexample.ui.register.viewmodel.RegisterViewModel

class AnimalRepository(
    private val animalService: AnimalService
) {
    suspend fun getAnimal(id: Int) = runCatching { animalService.getAnimalDetail(id) }

    suspend fun getTotalAnimalList() = runCatching { animalService.getTotalAnimalList() }

    suspend fun postAddAnimal(request: RequestAddAnimalDto) =
        runCatching { animalService.postAddAnimal(request) }

    suspend fun deleteAnimal(id: Int) = runCatching { animalService.deleteAnimal(id) }
}

