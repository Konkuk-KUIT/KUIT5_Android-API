package com.example.kuitandroidapiexample.ui.register.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.kuitandroidapiexample.data.repository.AnimalRepository
import com.example.kuitandroidapiexample.ui.model.AnimalType
import com.example.kuitandroidapiexample.ui.register.uistate.RegisterUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val repository: AnimalRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState = _uiState.asStateFlow()

    fun updateUrl(url: String) = _uiState.update { it.copy(url = url) }
    fun updateAnimalName(name: String) = _uiState.update { it.copy(animalName = name) }
    fun updateAddress(address: String) = _uiState.update { it.copy(address = address) }
    fun updateReporterName(name: String) = _uiState.update { it.copy(reporterName = name) }
    fun updateAnimalType(type: AnimalType) = _uiState.update { it.copy(animalType = type) }

    fun postAnimal() {
        val state = _uiState.value
        viewModelScope.launch {
            repository.addAnimal(
                id = state.id,
                url = state.url,
                name = state.reporterName,
                state = state.animalType,
                breed = state.animalName,
                address = state.address
            ).fold(
                onSuccess = {
                    _uiState.update { it.copy(isSubmitted = true) }
                },
                onFailure = { throwable ->
                    _uiState.update {
                        it.copy(errorMessage = throwable.message ?: "등록 실패")
                    }
                }
            )
        }
    }
}

class RegisterViewModelFactory(
    private val repository: AnimalRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegisterViewModel(repository) as T
    }
}
