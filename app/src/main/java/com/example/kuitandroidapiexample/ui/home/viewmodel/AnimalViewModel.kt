package com.example.kuitandroidapiexample.ui.home.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kuitandroidapiexample.data.dto.request.RequestAddAnimalDto
import com.example.kuitandroidapiexample.data.repository.AnimalRepository
import com.example.kuitandroidapiexample.ui.home.uistate.AnimalListUiState
import com.example.kuitandroidapiexample.ui.home.uistate.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimalViewModel @Inject constructor(
    private val animalRepository: AnimalRepository
) : ViewModel() {
    //private val animalService: AnimalService by lazy { ServicePool.animalService }


    private val _animalAddState = mutableStateOf<RequestAddAnimalDto?>(null)
    val animalAddState: State<RequestAddAnimalDto?> get() = _animalAddState

    private val _registerSuccess = mutableStateOf(false)
    val registerSuccess: State<Boolean> get() = _registerSuccess

    private val _uiState = MutableStateFlow(AnimalListUiState())
    val uiState = _uiState.asStateFlow()

    fun getTotalAnimalList() {
        viewModelScope.launch {
            animalRepository.getTotalAnimal()
                .onSuccess { baseResponse ->
                    _uiState.value = AnimalListUiState(
                        animals = baseResponse.data.map { it.toUiState() }
                    )
                }
                .onFailure { error ->
                    Log.e("getTotalAnimalList", "API call failed", error)
                }
        }
    }
}
