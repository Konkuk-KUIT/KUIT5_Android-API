package com.example.kuitandroidapiexample.home.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kuitandroidapiexample.data.ServicePool
import com.example.kuitandroidapiexample.data.dto.request.RequestAddAnimalDto
import com.example.kuitandroidapiexample.data.dto.response.BaseResponse
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDetailDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDto
import com.example.kuitandroidapiexample.data.service.AnimalService
import com.example.kuitandroidapiexample.model.AnimalType
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnimalViewModel : ViewModel() {
    private val animalService: AnimalService by lazy { ServicePool.animalService }

    private val _animalListState = mutableStateOf<BaseResponse<List<ResponseAnimalDto>>?>(null)
    val animalListState: State<BaseResponse<List<ResponseAnimalDto>>?>get() = _animalListState

    private val _animalDetailState = mutableStateOf<ResponseAnimalDetailDto?>(null)
    val animalDetailState: State<ResponseAnimalDetailDto?> get() = _animalDetailState

    private val _addAnimalState = mutableStateOf<Boolean?>(null)
    val addAnimalState: State<Boolean?> get() = _addAnimalState

    private val _deleteAnimalState = mutableStateOf<Boolean?>(null)
    val deleteAnimalState: State<Boolean?> get() = _deleteAnimalState

    fun getTotalAnimalList() {
        viewModelScope.launch {
            runCatching {
                animalService.getTotalAnimalList()
            }
                .onSuccess { data ->
                    _animalListState.value = data

                }
                .onFailure { error ->
                    Log.e("getTotalAnimallist", error.message?:"Unkonwn error") // Nullable한 객체, Null값 처리 해줘야함

                }
        }
    }

    fun getAnimalDetail(id: Int) {
        viewModelScope.launch {
            runCatching {
                animalService.getAnimalDetail(id)
            }.fold(
                onSuccess = { data ->
                    _animalDetailState.value = data.data
                },
                onFailure = { error ->
                    Log.e("getAnimalDetail", error.message ?: "Unknown error")
                }
            )
        }
    }

    fun postAddAnimal(request: RequestAddAnimalDto) {
        viewModelScope.launch {
            runCatching {
                animalService.postAddAnimal(request)
            }.fold(
                onSuccess = {
                    _addAnimalState.value = true
                },
                onFailure = { error ->
                    _addAnimalState.value = false
                    Log.e("postAddAnimal", error.message ?: "Unknown error")
                }
            )
        }
    }

    fun deleteAnimal(id: Int) {
        viewModelScope.launch {
            runCatching {
                animalService.deleteAnimal(id)
            }.fold(
                onSuccess = {
                    _deleteAnimalState.value = true
                },
                onFailure = { error ->
                    _deleteAnimalState.value = false
                    Log.e("deleteAnimal", error.message ?: "Unknown error")
                }
            )
        }
    }

    fun addAnimal(
        url: String,
        name: String,
        state: AnimalType,
        breed: String,
        address: String
    ) {
        val request = RequestAddAnimalDto(
            id = 1,
            url = url,
            name = name,
            state = state,
            breed = breed,
            address = address
        )
        postAddAnimal(request)
    }
}