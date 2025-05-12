package com.example.kuitandroidapiexample.ui.home.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.kuitandroidapiexample.data.ServicePool
import com.example.kuitandroidapiexample.data.ServicePool.animalService
import com.example.kuitandroidapiexample.data.dto.request.RequestAddAnimalDto
import com.example.kuitandroidapiexample.data.dto.response.BaseResponse
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDetailDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDto
import com.example.kuitandroidapiexample.data.repository.AnimalRepository
import com.example.kuitandroidapiexample.data.service.AnimalService
import com.example.kuitandroidapiexample.ui.home.uistate.AnimalUiState
import com.example.kuitandroidapiexample.ui.mapper.toUiState
import com.example.kuitandroidapiexample.ui.model.AnimalType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimalViewModel(
    private val animalRepository: AnimalRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<AnimalUiState>(AnimalUiState())
    val uiState = _uiState.asStateFlow()

//    init {
//        getTotalAnimalList()
//    }

    fun getTotalAnimalList() {
        viewModelScope.launch {
            animalRepository.getTotalAnimal().fold(
                onSuccess = {
                    _uiState.value = it.data.toUiState()
                },
                onFailure = { error ->
                    Log.e("okhttpError", error.message.toString())
                }
            )
        }
    }
}

class AnimalViewModelFactory(
    private val animalRepository: AnimalRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        AnimalViewModel(animalRepository) as T
}