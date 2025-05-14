package com.example.kuitandroidapiexample.data.repository

import com.example.kuitandroidapiexample.data.dto.request.RequestAddAnimalDto
import com.example.kuitandroidapiexample.data.dto.response.BaseResponse
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDetailDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseDeleteAnimalDto
import com.example.kuitandroidapiexample.data.service.AnimalService

class AnimalRepository(
    private val animalService: AnimalService
) {
    suspend fun getTotalAnimalList(): Result<BaseResponse<List<ResponseAnimalDto>>> {
        return runCatching {
            animalService.getTotalAnimalList()
        }
    }

    suspend fun getAnimalDetail(id: Int): Result<BaseResponse<ResponseAnimalDetailDto>> {
        return runCatching {
            animalService.getAnimalDetail(id)
        }
    }
    //suspend fun getAnimalDetail(id: Int) = runCatching { animalService.getAnimalDetail(id) }

    suspend fun postAddAnimal(request: RequestAddAnimalDto): Result<Unit> {
        return runCatching {
            animalService.postAddAnimal(request)
        }
    }

    suspend fun deleteAnimal(id: Int): Result<ResponseDeleteAnimalDto> {
        return runCatching {
            animalService.deleteAnimal(id)
        }
    }
}