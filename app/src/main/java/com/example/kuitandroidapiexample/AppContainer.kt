package com.example.kuitandroidapiexample

import com.example.kuitandroidapiexample.data.repository.AnimalRepository
import com.example.kuitandroidapiexample.data.ServicePool.animalService

class AppContainer{
    private fun provideApiService() = animalService
    fun provideRepository() = AnimalRepository(provideApiService())

}
