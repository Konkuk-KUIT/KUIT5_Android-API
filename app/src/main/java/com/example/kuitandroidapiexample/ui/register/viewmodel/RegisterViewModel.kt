package com.example.kuitandroidapiexample.ui.register.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.kuitandroidapiexample.data.dto.request.RequestAddAnimalDto
import com.example.kuitandroidapiexample.data.repository.AnimalRepository
import com.example.kuitandroidapiexample.ui.model.AnimalType
import com.example.kuitandroidapiexample.ui.register.uistate.AnimalUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val animalRepository: AnimalRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AnimalUiState())
    val uiState = _uiState.asStateFlow()

    fun updateUrl(url: String) {
        _uiState.update { it.copy(url = url) }
    }
    fun updateReporterName(name: String) {
        _uiState.update { it.copy(reporterName = name) }
    }
    fun updateAddress(address: String) {
        _uiState.update { it.copy(address = address) }
    }
    fun updateAnimalName(name: String) {
        _uiState.update { it.copy(animalName = name) }
    }
    fun updateAnimalType(type: AnimalType) {
        _uiState.update { it.copy(animalType = type) }
    }

    fun resetUiState() {
        _uiState.value = AnimalUiState()
    }
    fun postAddAnimal(request: RequestAddAnimalDto) {
        viewModelScope.launch {
            animalRepository.postAddAnimal(request).fold(
                onSuccess = {
                    Log.d("RegisterViewModel", "등록 성공: $it")
                    _uiState.update {
                        it.copy(isAdded = true)
                    }
                },
                onFailure = { error ->
                    Log.e("okhttoError",error.message.toString())
                }
            )
        }
    }
}

class RegisterViewModelFactory(
    private val animalRepository: AnimalRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        RegisterViewModel(animalRepository) as T
}