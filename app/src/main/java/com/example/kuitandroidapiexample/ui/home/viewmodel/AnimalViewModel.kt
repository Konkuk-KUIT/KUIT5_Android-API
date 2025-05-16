package com.example.kuitandroidapiexample.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.kuitandroidapiexample.data.repository.AnimalRepository
import com.example.kuitandroidapiexample.ui.home.uistate.AnimalListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AnimalViewModel(
    private val animalRepository: AnimalRepository
) : ViewModel() {
//    private val _animalListState = mutableStateOf<BaseResponse<List<ResponseAnimalDto>>?>(null)
//    val animalListState: State<BaseResponse<List<ResponseAnimalDto>>?> get() = _animalListState
//
//    private val _animalDetailState = mutableStateOf<BaseResponse<ResponseAnimalDetailDto>?>(null)
//    val animalDetailState: State<BaseResponse<ResponseAnimalDetailDto>?> get() = _animalDetailState
//
//    private val _addAnimalState = mutableStateOf<Boolean?>(null)
//    val addAnimalState: State<Boolean?> get() = _addAnimalState
//
//    private val _deleteAnimalState = mutableStateOf<Boolean?>(null)
//    val deleteAnimalState: State<Boolean?> get() = _deleteAnimalState

    private val _uiState = MutableStateFlow(AnimalListUiState())
    val uiState = _uiState.asStateFlow()

    fun getTotalAnimalList() {
        viewModelScope.launch {
            animalRepository.getTotalAnimalList().fold(
                onSuccess = { data ->
                    Log.d("AnimalViewModel", "getTotalAnimalList 성공: ${data.data}") // 성공 로그
                    _uiState.update {
                        it.copy(animalList = data.data)
                    }
                },
                onFailure = { error ->
                    Log.e("okhttpError", error.message.toString())
                }
            )
        }
    }

//    fun postAddAnimal(request: RequestAddAnimalDto) {
//        viewModelScope.launch {
//            runCatching {
//                animalRepository.postAddAnimal(request)
//            }.onSuccess {
//                _addAnimalState.value = true
//            }.onFailure { error ->
//                Log.e("postAddAnimal", "등록 실패: ${error.message}")
//                _addAnimalState.value = false
//            }
//        }
//    }

//    fun deleteAnimal(id: Int) {
//        viewModelScope.launch {
//            runCatching {
//                animalRepository.deleteAnimal(id)
//            }.fold(
//                onSuccess = { data ->
//                    Log.d("deleteAnimal", "삭제 성공")
//                    _deleteAnimalState.value = true
//                },
//                onFailure = { error ->
//                    Log.e("deleteAnimal", "삭제 실패: ${error.message}")
//                    _deleteAnimalState.value = false
//                }
//            )
//        }
//    }

//    fun addAnimal(
//        url: String,
//        name: String,
//        state: AnimalType,
//        breed: String,
//        address: String
//    ) {
//        val request = RequestAddAnimalDto(
//            id = 1,
//            url = url,
//            name = name,
//            state = state,
//            breed = breed,
//            address = address
//        )
//        postAddAnimal(request)
//    }
//
//    fun resetAddAnimal() {
//        _addAnimalState.value = null
//    }
}

class AnimalViewModelFactory(
    private val animalRepository: AnimalRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        AnimalViewModel(animalRepository) as T
}