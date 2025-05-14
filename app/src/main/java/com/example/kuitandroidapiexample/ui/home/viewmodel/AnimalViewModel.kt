package com.example.kuitandroidapiexample.ui.home.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kuitandroidapiexample.data.ServicePool
import com.example.kuitandroidapiexample.data.dto.request.RequestAddAnimalDto
import com.example.kuitandroidapiexample.data.dto.request.RequestDto
import com.example.kuitandroidapiexample.data.dto.response.BaseResponse
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDetailDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDto

import com.example.kuitandroidapiexample.data.service.AnimalService
import com.example.kuitandroidapiexample.ui.model.AnimalType
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnimalViewModel : ViewModel() {
    private val animalService: AnimalService by lazy { ServicePool.animalService }

    private val _animalListState = mutableStateOf<BaseResponse<List<ResponseAnimalDto>>?>(null)
    val animalListState: State<BaseResponse<List<ResponseAnimalDto>>?> get() = _animalListState

    private val _animalDetailState = mutableStateOf<BaseResponse<ResponseAnimalDetailDto>?>(null)
    val animalDetailState: State<BaseResponse<ResponseAnimalDetailDto>?> get() = _animalDetailState

    private val _animalAddState = mutableStateOf<RequestAddAnimalDto?>(null)
    val animalAddState: State<RequestAddAnimalDto?> get() = _animalAddState

    private val _registerSuccess = mutableStateOf(false)
    val registerSuccess: State<Boolean> get() = _registerSuccess

    private val _animalDeleteState = mutableStateOf<ResponseAnimalDetailDto?>(null)
    val animalDeleteState: State<ResponseAnimalDetailDto?> get() = _animalDeleteState

    fun getTotalAnimalList() {
        viewModelScope.launch {
            runCatching {
                animalService.getTotalAnimalList()
            }
                .onSuccess { data ->
                    _animalListState.value = data
                }
                .onFailure { error ->
                    //Log.e("getTotalAnimalList", error.message ?: "Unknown error")
                    Log.e("getTotalAnimalList", "API call failed", error)
                }
        }

    }

    fun getAnimal(id: Int) {
        viewModelScope.launch {
            runCatching {
                animalService.getAnimal(id)
            }
                .onSuccess { data ->
                    _animalDetailState.value = data
                }
                .onFailure { error ->
                    Log.e("getAnimal", error.message ?: "Unknown error")
                }
        }
    }

    fun deleteAnimal(id: Int) {
        viewModelScope.launch {
            runCatching {
                animalService.deleteAnimal(id)
            }
                .onSuccess { data ->
                    _animalDeleteState.value = data
                }
                .onFailure { error ->
                    Log.e("deleteAnimal", error.message ?: "Unknown error")
                }
        }
    }



    fun postAddAnimal(
        name: String,
        url: String,
        state: AnimalType,
        address: String,
        breed: String,
        id: Int

    ) {
        val register = RequestDto(
            name = name,
            url = url,
            address = address,
            state = state,
            breed = breed,
            id = id

        )
        val requestAddAnimalDto = RequestAddAnimalDto(data = register)
        viewModelScope.launch{
            runCatching {
                animalService.registerAnimal(requestAddAnimalDto)
            }
                .onSuccess { data ->
                    _animalAddState.value = requestAddAnimalDto
                }
                .onFailure { error ->
                    Log.e("registerAnimal", error.message ?: "Unknown error")
                }
        }
    }
}