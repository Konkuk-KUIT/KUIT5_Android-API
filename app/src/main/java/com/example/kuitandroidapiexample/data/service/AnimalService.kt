package com.example.kuitandroidapiexample.data.service

import com.example.kuitandroidapiexample.data.dto.request.RequestAddAnimalDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDetailDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalListDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AnimalService {
    @GET("animals")
    suspend fun getTotalAnimalList(): ResponseAnimalListDto

    @GET("animals/{id}")
    suspend fun getAnimalDetail(
        @Path("id") id: Int
    ): ResponseAnimalDetailDto

    @POST("animals")
    suspend fun postAddAnimal(
        @Body request: RequestAddAnimalDto
    )

    @DELETE("animals/{id}")
    suspend fun deleteAnimal(
        @Path("id") id: Int
    )
}