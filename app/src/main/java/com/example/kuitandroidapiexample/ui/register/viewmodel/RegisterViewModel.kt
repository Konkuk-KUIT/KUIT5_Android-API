package com.example.kuitandroidapiexample.ui.register.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.kuitandroidapiexample.data.repository.AnimalRepository
import com.example.kuitandroidapiexample.ui.mapper.toRequestAddAnimalDto
import com.example.kuitandroidapiexample.ui.model.AnimalType
import com.example.kuitandroidapiexample.ui.register.uistate.RegisterUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val animalRepository: AnimalRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState = _uiState.asStateFlow()

    fun registerAnimal() {
        viewModelScope.launch {
            animalRepository.registerAnimal(
                _uiState.value.toRequestAddAnimalDto()
            ).fold(
                onSuccess = {
                    _uiState.update { it.copy(isAdded = true) }
                },
                onFailure = { error ->
                    Log.e("okhttpError", error.message.toString())
                }
            )
        }
    }

    fun updateUrl(url: String) {
        _uiState.update {
            it.copy(url = url)
        }
    }

    fun updateName(name: String) {
        _uiState.update {
            it.copy(name = name)
        }
    }

    fun updateAddress(address: String) {
        _uiState.update {
            it.copy(address = address)
        }
    }

    fun updateBreed(breed: String) {
        _uiState.update {
            it.copy(breed = breed)
        }
    }

    fun updateType(animalType: AnimalType) {
        _uiState.update {
            it.copy(type = animalType)
        }
    }
}

class RegisterViewModelFactory(
    private val animalRepository: AnimalRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        RegisterViewModel(animalRepository) as T
}