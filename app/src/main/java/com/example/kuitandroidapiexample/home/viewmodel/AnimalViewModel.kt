package com.example.kuitandroidapiexample.home.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kuitandroidapiexample.data.dto.request.RequestAddAnimalDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDetailDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalListDto
import com.example.kuitandroidapiexample.data.repository.AnimalRepository
import com.example.kuitandroidapiexample.model.AnimalType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimalViewModel @Inject constructor(
    private val animalRepository: AnimalRepository
) : ViewModel() {

    private val _animalListState = mutableStateOf<ResponseAnimalListDto?>(null)
    val animalListState: State<ResponseAnimalListDto?> get() = _animalListState

    private val _animalDetailState = mutableStateOf<ResponseAnimalDetailDto?>(null)
    val animalDetailState: State<ResponseAnimalDetailDto?> get() = _animalDetailState

    private val _addAnimalState = mutableStateOf<Boolean?>(null)
    val addAnimalState: State<Boolean?> get() = _addAnimalState

    private val _deleteAnimalState = mutableStateOf<Boolean?>(null)
    val deleteAnimalState: State<Boolean?> get() = _deleteAnimalState

    fun getTotalAnimalList() {
        viewModelScope.launch {
            animalRepository.getTotalAnimalList()
                .onSuccess { _animalListState.value = it }
                .onFailure { Log.e("getTotalAnimalList", it.message ?: "Unknown error") }
        }
    }

    fun getAnimalDetail(id: Int) {
        viewModelScope.launch {
            animalRepository.getAnimal(id)
                .onSuccess { _animalDetailState.value = it }
                .onFailure { Log.e("getAnimalDetail", it.message ?: "Unknown error") }
        }
    }

    fun postAddAnimal(request: RequestAddAnimalDto) {
        viewModelScope.launch {
            animalRepository.addAnimal(request)
                .onSuccess { _addAnimalState.value = true }
                .onFailure {
                    Log.e("postAddAnimal", it.message ?: "Unknown error")
                    _addAnimalState.value = false
                }
        }
    }

    fun deleteAnimal(id: Int) {
        viewModelScope.launch {
            animalRepository.deleteAnimal(id)
                .onSuccess { _deleteAnimalState.value = true }
                .onFailure {
                    Log.e("deleteAnimal", it.message ?: "Unknown error")
                    _deleteAnimalState.value = false
                }
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
