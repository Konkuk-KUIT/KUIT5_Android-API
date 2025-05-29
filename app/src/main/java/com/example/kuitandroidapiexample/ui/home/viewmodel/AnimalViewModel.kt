package com.example.kuitandroidapiexample.ui.home.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kuitandroidapiexample.data.dto.response.BaseResponse
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDto
import com.example.kuitandroidapiexample.data.repository.AnimalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimalViewModel @Inject constructor(
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
//
//class AnimalViewModelFactory(
//    private val animalRepository: AnimalRepository
//) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T =
//        AnimalViewModel(animalRepository) as T
//}