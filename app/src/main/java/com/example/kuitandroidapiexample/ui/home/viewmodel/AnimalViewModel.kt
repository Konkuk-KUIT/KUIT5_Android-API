package com.example.kuitandroidapiexample.ui.home.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kuitandroidapiexample.data.repository.AnimalRepository
import com.example.kuitandroidapiexample.data.ServicePool.animalService

import com.example.kuitandroidapiexample.data.dto.response.*
import com.example.kuitandroidapiexample.data.dto.request.*
import com.example.kuitandroidapiexample.ui.home.uistate.AnimalListUiState
import com.example.kuitandroidapiexample.ui.model.AnimalType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel

class AnimalViewModel @Inject constructor(
    private val animalRepository: AnimalRepository
) : ViewModel() {

    private val _uiState = mutableStateOf(AnimalListUiState())
    val uiState: State<AnimalListUiState> get() = _uiState

    private val _animalDetailState = mutableStateOf<BaseResponse<ResponseAnimalDetailDto>?>(null)
    val animalDetailState: State<BaseResponse<ResponseAnimalDetailDto>?> get() = _animalDetailState

    private val _addAnimalState = mutableStateOf<Boolean?>(null)
    val addAnimalState: State<Boolean?> get() = _addAnimalState

    private val _deleteAnimalState = mutableStateOf<Boolean?>(null)
    val deleteAnimalState: State<Boolean?> get() = _deleteAnimalState

    fun getTotalAnimalList() {
        viewModelScope.launch {
            runCatching {
                animalRepository.getTotalAnimalList().getOrThrow()
            }.onSuccess { response ->
                _uiState.value = AnimalListUiState(animalList = response.data)
            }
        }
    }

    fun getAnimalDetail(id: Int) {
        viewModelScope.launch {
            runCatching {
                animalService.getAnimalDetail(id)
            }.onSuccess { data ->
                _animalDetailState.value = data
            }.onFailure { error ->
                Log.e("getAnimalDetail", error.message ?: "Unknown error")
            }
        }
    }

    fun postAddAnimal(request: RequestAddAnimalDto) {
        viewModelScope.launch {
            runCatching {
                animalService.postAddAnimal(request)
            }.onSuccess {
                _addAnimalState.value = true
            }.onFailure { error ->
                _addAnimalState.value = false
                Log.e("postAddAnimal", error.message ?: "Unknown error")
            }
        }
    }

    fun deleteAnimal(id: Int) {
        viewModelScope.launch {
            runCatching {
                animalService.deleteAnimal(id)
                // TODO: 삭제함수호출
            }.fold(
                onSuccess = { data -> // TODO: Data 핸들링
                    _deleteAnimalState.value = true
                },
                onFailure = { error -> // TODO: Error 핸들링
                    _deleteAnimalState.value = false
                    Log.e("deleteAnimal", error.message ?: "Unknown error")
                }
            )
        }
    }

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
        postAddAnimal(request)
    }
}


//자동 주입을 했기 때문에 필요없음
/* class AnimalViewModelFactory(
    private val animalRepository: AnimalRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AnimalViewModel(animalRepository) as T
    }
}
*/