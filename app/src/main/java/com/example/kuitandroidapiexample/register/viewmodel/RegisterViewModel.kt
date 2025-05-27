package com.example.kuitandroidapiexample.register.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.kuitandroidapiexample.data.dto.request.RequestAddAnimalDto
import com.example.kuitandroidapiexample.data.repository.AnimalRepository
import com.example.kuitandroidapiexample.model.AnimalType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

// UI 상태 표현을 위한 데이터 클래스
data class AnimalRegisterUiState(
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null
)

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val animalRepository: AnimalRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AnimalRegisterUiState())
    val uiState = _uiState.asStateFlow()

    fun registerAnimal(
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
            animalRepository.addAnimal(request).fold(
                onSuccess = { _: Unit ->
                    _uiState.update {
                        it.copy(
                            isSuccess = true,
                            isError = false,
                            errorMessage = null
                        )
                    }
                },
                onFailure = { error ->
                    Log.e("RegisterAnimal", error.message ?: "Unknown error")
                    _uiState.update {
                        it.copy(
                            isSuccess = false,
                            isError = true,
                            errorMessage = error.message ?: "Unknown error"
                        )
                    }
                }
            )
        }
    }
}

// ViewModelFactory
class RegisterViewModelFactory(
    private val animalRepository: AnimalRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegisterViewModel(animalRepository) as T
    }
}
