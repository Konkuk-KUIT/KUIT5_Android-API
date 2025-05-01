package com.example.kuitandroidapiexample.home.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.kuitandroidapiexample.data.ServicePool
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDetailDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalListDto
import com.example.kuitandroidapiexample.data.service.AnimalService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnimalViewModel : ViewModel() {
    private val animalService: AnimalService by lazy { ServicePool.animalService }

    private val _animalListState = mutableStateOf<ResponseAnimalListDto?>(null)
    val animalListState: State<ResponseAnimalListDto?> get() = _animalListState

    private val _animalDetailState = mutableStateOf<ResponseAnimalDetailDto?>(null)
    val animalDetailState: State<ResponseAnimalDetailDto?> get() = _animalDetailState

    fun getTotalAnimalList() {
        animalService.getTotalAnimalList()
            .enqueue(object : Callback<ResponseAnimalListDto> {
                override fun onResponse(
                    call: Call<ResponseAnimalListDto>,
                    response: Response<ResponseAnimalListDto>
                ) {
                    if (response.isSuccessful) {
                        _animalListState.value = response.body()
                    } else {
                        Log.e("getTotalAnimalList", "${response.code()} ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<ResponseAnimalListDto>, t: Throwable) {
                    Log.e("getTotalAnimalList", "서버 통신 오류: ${t.message}")
                }
            })
    }

    fun getAnimalDetail(id: Int) {
        animalService.getAnimalDetail(id)
            .enqueue(object : Callback<ResponseAnimalDetailDto> {
                override fun onResponse(
                    call: Call<ResponseAnimalDetailDto>,
                    response: Response<ResponseAnimalDetailDto>
                ) {
                    if (response.isSuccessful) {
                        _animalDetailState.value = response.body()
                    } else {
                        Log.e("getAnimalDetail", "${response.code()} ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<ResponseAnimalDetailDto>, t: Throwable) {
                    Log.e("getAnimalDetail", "서버 통신 오류: ${t.message}")
                }
            })
    }
}