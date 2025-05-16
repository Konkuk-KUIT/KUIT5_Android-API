package com.example.kuitandroidapiexample.ui.register.uistate

import com.example.kuitandroidapiexample.ui.model.AnimalType

/**
 * 등록 화면(View)에서 사용할 입력 폼 및 결과 상태를 정의합니다.
 */
data class RegisterUiState(
    val id: Int = 0,
    val url: String = "",
    val name: String = "",
    val state: AnimalType = AnimalType.PROTECT,
    val breed: String = "",
    val address: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null
)