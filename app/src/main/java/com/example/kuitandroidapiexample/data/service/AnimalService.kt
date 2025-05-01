package com.example.kuitandroidapiexample.data.service

import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDetailDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalListDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimalService {
    @GET("animals")
    fun getTotalAnimalList() : Call<ResponseAnimalListDto>

    @GET("animals/{id}")
    fun getAnimalDetail(
        @Path("id") id: Int
    ) : Call<ResponseAnimalDetailDto>
}