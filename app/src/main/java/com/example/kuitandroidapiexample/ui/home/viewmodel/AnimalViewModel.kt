package com.example.kuitandroidapiexample.ui.home.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.kuitandroidapiexample.data.AnimalRepository

import com.example.kuitandroidapiexample.data.dto.response.*

import kotlinx.coroutines.launch

class AnimalViewModel(
    private val animalRepository: AnimalRepository
) : ViewModel() {

    private val _animalListState = mutableStateOf<BaseResponse<List<ResponseAnimalDto>>?>(null)
    val animalListState: State<BaseResponse<List<ResponseAnimalDto>>?> get() = _animalListState

    fun getTotalAnimalList() {
        viewModelScope.launch {
            runCatching {
                animalRepository.getTotalAnimalList().getOrThrow()
            }.onSuccess { data ->
                _animalListState.value = data
            }.onFailure { error ->
                Log.e("getTotalAnimalList", "실패 원인: ${error.localizedMessage}", error)
            }
        }
    }



}

class AnimalViewModelFactory(
    private val animalRepository: AnimalRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AnimalViewModel(animalRepository) as T
    }
}