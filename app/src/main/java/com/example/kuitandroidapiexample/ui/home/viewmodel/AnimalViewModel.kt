package com.example.kuitandroidapiexample.ui.home.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.kuitandroidapiexample.data.ServicePool.animalService
import com.example.kuitandroidapiexample.data.dto.request.RequestAddAnimalDto
import com.example.kuitandroidapiexample.data.dto.response.BaseResponse
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDto
import com.example.kuitandroidapiexample.data.repository.AnimalRepository
import com.example.kuitandroidapiexample.ui.home.uistate.AnimalUiState
import com.example.kuitandroidapiexample.ui.home.uistate.toUiState
import com.example.kuitandroidapiexample.ui.model.AnimalType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimalViewModel(
    private val animalRepository: AnimalRepository
) : ViewModel() {

    private val _addAnimalState = mutableStateOf<Boolean?>(null)
    val addAnimalState: State<Boolean?> get() = _addAnimalState


    private val _uiState = MutableStateFlow<List<AnimalUiState>>(emptyList())
    val uiState = _uiState.asStateFlow()

    fun getTotalAnimalList() {
        viewModelScope.launch {
            animalRepository.getAnimals().fold(
                onSuccess = { data ->
                    _uiState.value = data.data.map { it.toUiState() }
                },
                onFailure = { error ->
                    Log.e("okhttpError", error.message.toString())
                }
            )
        }
    }

    fun postAddAnimal(request: RequestAddAnimalDto) {
        viewModelScope.launch {
            runCatching {
                animalService.postAddAnimal(request)
            }
                .onSuccess {
                    _addAnimalState.value = true
                }
                .onFailure { error ->
                    _addAnimalState.value = false
                    Log.e("postAddAnimal", error.message ?: "Unknown error")
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

class AnimalViewModelFactory(
    private val animalRepository: AnimalRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        AnimalViewModel(animalRepository) as T
}