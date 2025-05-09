package com.example.kuitandroidapiexample.data.dto.response

import com.example.kuitandroidapiexample.model.AnimalType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseAnimalDetailDto(
    @SerialName("id")
    val id: Int = -1,
    @SerialName("name")
    val name: String = "",
    @SerialName("url")
    val url: String = "",
    @SerialName("state")
    val state: AnimalType = AnimalType.PROTECT,
    @SerialName("breed")
    val breed: String = "",
    @SerialName("address")
    val address: String = ""
)