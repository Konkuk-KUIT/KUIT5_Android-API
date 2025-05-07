package com.example.kuitandroidapiexample.home.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kuitandroidapiexample.data.ServicePool
import com.example.kuitandroidapiexample.data.ServicePool.animalService
import com.example.kuitandroidapiexample.data.dto.request.RequestAddAnimalDto
import com.example.kuitandroidapiexample.data.dto.response.BaseResponse
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDetailDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDto
import com.example.kuitandroidapiexample.data.service.AnimalService
import com.example.kuitandroidapiexample.model.AnimalType
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnimalViewModel : ViewModel() {
    private val animalService: AnimalService by lazy { ServicePool.animalService }

    private val _animalListState = mutableStateOf<BaseResponse<List<ResponseAnimalDto>>?>(null)
    val animalListState: State<BaseResponse<List<ResponseAnimalDto>>?> get() = _animalListState

    private val _animalDetailState = mutableStateOf<ResponseAnimalDetailDto?>(null)
    val animalDetailState: State<ResponseAnimalDetailDto?> get() = _animalDetailState

    private val _addAnimalState = mutableStateOf<Boolean?>(null)
    val addAnimalState: State<Boolean?> get() = _addAnimalState

    private val _deleteAnimalState = mutableStateOf<Boolean?>(null)
    val deleteAnimalState: State<Boolean?> get() = _deleteAnimalState

    fun getTotalAnimalList() {
        viewModelScope.launch {
           runCatching {
                animalService.getTotalAnimalList()
            }
                .onSuccess { data ->
                    _animalListState.value = data
                    //성공 핸들링
                }
                .onFailure { error ->
                    Log.e("getTotalAnimalList", error.message ?: "Unknown error" )
                    //실패 핸들링

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
    }

    fun getAnimalDetail(id: Int) {
        viewModelScope.launch {
            runCatching {
                animalService.getAnimalDetail(id)  // BaseResponse<ResponseAnimalDetailDto>
            }.onSuccess { response ->
                _animalDetailState.value = response.data
            }.onFailure { error ->
                Log.e("getAnimalDetail", error.message ?: "Unknown error")
            }
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
            val result = runCatching {
                animalService.postAddAnimal(request)
            }

            result.onSuccess {
                _addAnimalState.value = true
            }.onFailure {
                Log.e("postAddAnimal", it.message ?: "Unknown error")
                _addAnimalState.value = false
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
    }


    fun deleteAnimal(id: Int) {
        viewModelScope.launch {
           runCatching {
               animalService.deleteAnimal(id)
            }.fold (
                onSuccess = {data ->
                    _deleteAnimalState.value = true
                    Log.d("deleteAnimal", "삭제 성공")
                },
                onFailure = {error ->
                    _deleteAnimalState.value = false
                    Log.e("deleteAnimal", error.message ?: "Unknown error")
                }
            )
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