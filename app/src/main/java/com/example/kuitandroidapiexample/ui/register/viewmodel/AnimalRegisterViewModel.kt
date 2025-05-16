package com.example.kuitandroidapiexample.ui.register.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.kuitandroidapiexample.data.dto.request.RequestAddAnimalDto
import com.example.kuitandroidapiexample.data.repository.AnimalRepository
import com.example.kuitandroidapiexample.ui.model.AnimalType
import com.example.kuitandroidapiexample.ui.register.uistate.AnimalRegisterUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AnimalRegisterViewModel(
    private val animalRepository: AnimalRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AnimalRegisterUiState())
    val uiState = _uiState.asStateFlow()

    fun postAddAnimal(request: RequestAddAnimalDto) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    url = request.url,
                    animalName = request.breed,
                    animalType = request.state,
                    address = request.address,
                    isRegister = false
                )
            }

            runCatching {
                animalRepository.postAnimal(request)
            }
                .onSuccess {
                    _uiState.update {
                        it.copy(isRegister = true)
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(isRegister = false)
                    }
                    Log.e("okhttpError", error.message.toString())
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

class AnimalRegisterViewModelFactory(
    private val animalRepository: AnimalRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        AnimalRegisterViewModel(animalRepository) as T
}