package com.example.kuitandroidapiexample.data.service

import com.example.kuitandroidapiexample.data.dto.request.RequestAddAnimalDto
import com.example.kuitandroidapiexample.data.dto.response.BaseResponse
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDetailDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalListDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AnimalService {
    @GET("animals")
    fun getTotalAnimalList() : BaseResponse<List<ResponseAnimalDto>>

    @GET("animals/{id}")
    fun getAnimalDetail(
        @Path("id") id: Int
    ) : BaseResponse<ResponseAnimalDto>

    @POST("animals")
    fun postAddAnimal(
        @Body request: RequestAddAnimalDto
    )

    @DELETE("animals/{id}")
    fun deleteAnimal(
        @Path("id") id: Int
    )
}