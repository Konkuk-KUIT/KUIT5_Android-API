package com.example.kuitandroidapiexample.ui.home.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.kuitandroidapiexample.data.ServicePool.animalService
import com.example.kuitandroidapiexample.data.dto.request.RequestAddAnimalDto
import com.example.kuitandroidapiexample.data.dto.response.BaseResponse
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDetailDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDto
import com.example.kuitandroidapiexample.data.repository.AnimalRepository
import com.example.kuitandroidapiexample.data.service.AnimalService
import com.example.kuitandroidapiexample.ui.model.AnimalType
import kotlinx.coroutines.launch

class AnimalViewModel(
    private val animalRepository: AnimalRepository
) : ViewModel() {
//    private val animalService: AnimalService by lazy { ServicePool.animalService }

    private val _animalListState = mutableStateOf<BaseResponse<List<ResponseAnimalDto>>?>(null)
    val animalListState: State<BaseResponse<List<ResponseAnimalDto>>?> get() = _animalListState

    private val _animalDetailState = mutableStateOf<BaseResponse<ResponseAnimalDetailDto>?>(null)
    val animalDetailState: State<BaseResponse<ResponseAnimalDetailDto>?> get() = _animalDetailState

    private val _addAnimalState = mutableStateOf<Boolean?>(null)
    val addAnimalState: State<Boolean?> get() = _addAnimalState

    private val _deleteAnimalState = mutableStateOf<Boolean?>(null)
    val deleteAnimalState: State<Boolean?> get() = _deleteAnimalState

    fun getTotalAnimalList() {
        viewModelScope.launch {
            runCatching {
                animalRepository.getTotalAnimalList()
            }
                .onSuccess { data ->
                    _animalListState.value = data
                }
                .onFailure { error ->
                    Log.e("getTotalAnimalList", error.message ?: "Unknown error")
                }
        }
    }

    fun getAnimalDetail(id: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                animalRepository.getAnimalDetail(id)
            }
                .onSuccess { data ->
                    _animalDetailState.value = data
                }
                .onFailure { error ->
                    Log.e("getAnimalDetail",error.message ?: "Unknown error")
                }
        }
    }

    fun postAddAnimal(request: RequestAddAnimalDto) {
        viewModelScope.launch {
            runCatching {
                animalRepository.postAddAnimal(request)
            }.onSuccess {
                _addAnimalState.value = true
            }.onFailure { error ->
                Log.e("postAddAnimal", "등록 실패: ${error.message}")
                _addAnimalState.value = false
            }
        }
    }

    fun deleteAnimal(id: Int) {
        viewModelScope.launch {
            runCatching {
                animalRepository.deleteAnimal(id)
            }.fold(
                onSuccess = { data ->
                    Log.d("deleteAnimal", "삭제 성공")
                    _deleteAnimalState.value = true
                },
                onFailure = { error ->
                    Log.e("deleteAnimal", "삭제 실패: ${error.message}")
                    _deleteAnimalState.value = false
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

    fun resetAddAnimal(){
        _addAnimalState.value=null
    }
}

class AnimalViewModelFactory(
    private val animalRepository : AnimalRepository
): ViewModelProvider.Factory{
    override fun <T:ViewModel> create(modelClass:Class<T>) : T = AnimalViewModel(animalRepository) as T
}