package com.example.kuitandroidapiexample.ui.home.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.kuitandroidapiexample.data.dto.request.RequestAddAnimalDto
import com.example.kuitandroidapiexample.data.dto.response.BaseResponse
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDto
import com.example.kuitandroidapiexample.data.repository.AnimalRepository
import com.example.kuitandroidapiexample.ui.model.AnimalType
import kotlinx.coroutines.launch

class AnimalViewModel(
    private val repository: AnimalRepository
) : ViewModel() {

    private val _animalListState = mutableStateOf<BaseResponse<List<ResponseAnimalDto>>?>(null)
    val animalListState: State<BaseResponse<List<ResponseAnimalDto>>?> get() = _animalListState

    fun getTotalAnimalList() {
        viewModelScope.launch {
            runCatching {
                repository.getTotalAnimalList()
            }.onSuccess { data ->
                _animalListState.value = data.getOrNull()
            }.onFailure { error ->
                Log.e("getTotalAnimalList", error.message ?: "Unknown error")
            }
        }
    }

}

class AnimalViewModelFactory(
    private val animalRepository: AnimalRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        AnimalViewModel(animalRepository) as T
}