package com.example.kuitandroidapiexample.ui.register.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.kuitandroidapiexample.data.AnimalRepository
import com.example.kuitandroidapiexample.data.dto.request.RequestAddAnimalDto
import com.example.kuitandroidapiexample.ui.model.AnimalType
import com.example.kuitandroidapiexample.ui.register.uistate.AnimalRegisterUiState
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val animalRepository: AnimalRepository
) : ViewModel() {
    private val _uiState = mutableStateOf(AnimalRegisterUiState())
    val uiState: State<AnimalRegisterUiState> get() = _uiState

    private val _addAnimalState = mutableStateOf<Boolean?>(null)
    val addAnimalState: State<Boolean?> get() = _addAnimalState

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
        viewModelScope.launch {
            runCatching {
                animalRepository.postAddAnimal(request).getOrThrow()
            }.onSuccess {
                _addAnimalState.value = true
            }.onFailure {
                _addAnimalState.value = false
            }
        }
    }
}

class RegisterViewModelFactory(
    private val animalRepository: AnimalRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegisterViewModel(animalRepository) as T
    }
}
