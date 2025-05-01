package com.example.kuitandroidapiexample.home.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kuitandroidapiexample.data.ServicePool
import com.example.kuitandroidapiexample.data.dto.request.RequestAddAnimalDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDetailDto
import com.example.kuitandroidapiexample.data.service.AnimalService
import com.example.kuitandroidapiexample.model.AnimalType
import kotlinx.coroutines.launch

class AnimalViewModel : ViewModel() {
    private val animalService: AnimalService by lazy { ServicePool.animalService }

    private val _animalDetailState = mutableStateOf<ResponseAnimalDetailDto?>(null)
    val animalDetailState: State<ResponseAnimalDetailDto?> get() = _animalDetailState

    private val _addAnimalState = mutableStateOf<Boolean?>(null)
    val addAnimalState: State<Boolean?> get() = _addAnimalState

    private val _deleteAnimalState = mutableStateOf<Boolean?>(null)
    val deleteAnimalState: State<Boolean?> get() = _deleteAnimalState

    fun getTotalAnimalList() {
        viewModelScope.launch {
            animalService.getTotalAnimalList()
        }
//            .enqueue(object : Callback<ResponseAnimalListDto> {
//                override fun onResponse(
//                    call: Call<ResponseAnimalListDto>,
//                    response: Response<ResponseAnimalListDto>
//                ) {
//                    if (response.isSuccessful) {
//                        _animalListState.value = response.body()
//                    } else {
//                        Log.e("getTotalAnimalList", "${response.code()} ${response.message()}")
//                    }
//                }
//
//                override fun onFailure(call: Call<ResponseAnimalListDto>, t: Throwable) {
//                    Log.e("getTotalAnimalList", "서버 통신 오류: ${t.message}")
//                }
//            })
    }

    fun getAnimalDetail(id: Int) {
        viewModelScope.launch {
            animalService.getAnimalDetail(id)
        }
//            .enqueue(object : Callback<ResponseAnimalDetailDto> {
//                override fun onResponse(
//                    call: Call<ResponseAnimalDetailDto>,
//                    response: Response<ResponseAnimalDetailDto>
//                ) {
//                    if (response.isSuccessful) {
//                        _animalDetailState.value = response.body()
//                    } else {
//                        Log.e("getAnimalDetail", "${response.code()} ${response.message()}")
//                    }
//                }
//
//                override fun onFailure(call: Call<ResponseAnimalDetailDto>, t: Throwable) {
//                    Log.e("getAnimalDetail", "서버 통신 오류: ${t.message}")
//                }
//            })
    }

    fun postAddAnimal(request: RequestAddAnimalDto) {
        viewModelScope.launch {
            animalService.postAddAnimal(request)
        }
//            .enqueue(object: Callback<Unit> {
//                override fun onResponse(
//                    call: Call<Unit>,
//                    response: Response<Unit>
//                ) {
//                    if (response.isSuccessful) {
//                        _addAnimalState.value = true
//                    } else {
//                        Log.e("postAddAnimal", "${response.code()} ${response.message()}")
//                        _addAnimalState.value = false
//                    }
//                }
//
//                override fun onFailure(call: Call<Unit>, t: Throwable) {
//                    Log.e("postAddAnimal", "서버 통신 오류: ${t.message}")
//                }
//            })
    }

    fun deleteAnimal(id: Int) {
        viewModelScope.launch {
            animalService.deleteAnimal(id)
        }
//            .enqueue(object : Callback<Unit> {
//                override fun onResponse(
//                    call: Call<Unit>,
//                    response: Response<Unit>
//                ) {
//                    if (response.isSuccessful) {
//                        _deleteAnimalState.value = true
//                    } else {
//                        Log.e("deleteAnimal", "${response.code()} ${response.message()}")
//                        _deleteAnimalState.value = false
//                    }
//                }
//
//                override fun onFailure(call: Call<Unit>, t: Throwable) {
//                    Log.e("deleteAnimal", "서버 통신 오류: ${t.message}")
//                }
//            })
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