package com.example.kuitandroidapiexample

import com.example.kuitandroidapiexample.data.ServicePool.animalService

class AppContainer {
    fun provideApiService() = animalService
}