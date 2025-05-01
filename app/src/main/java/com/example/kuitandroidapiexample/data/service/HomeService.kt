package com.example.kuitandroidapiexample.data.service

import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalListDto
import retrofit2.Call
import retrofit2.http.GET

interface HomeService {
    @GET("animals")
    fun getTotalAnimalList() : Call<ResponseAnimalListDto>
}