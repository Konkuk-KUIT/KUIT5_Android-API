package com.example.kuitandroidapiexample

import com.example.kuitandroidapiexample.data.AnimalRepository
import com.example.kuitandroidapiexample.data.repository.AnimalRepositoryImpl
import com.example.kuitandroidapiexample.data.ServicePool.animalService

class AppContainer {

    fun provideRepository(): AnimalRepository = AnimalRepositoryImpl(animalService)
}
