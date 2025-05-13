package com.example.kuitandroidapiexample.ui.register.viewmodel

import android.R.attr.name
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.kuitandroidapiexample.data.repository.AnimalRepository
import com.example.kuitandroidapiexample.ui.home.uistate.AnimalUiState
import com.example.kuitandroidapiexample.ui.home.uistate.toUiState
import com.example.kuitandroidapiexample.ui.home.viewmodel.AnimalViewModel
import com.example.kuitandroidapiexample.ui.model.AnimalType
import com.example.kuitandroidapiexample.ui.register.uistate.AnimalAddUiState
import com.example.kuitandroidapiexample.ui.register.uistate.toDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AnimalRegisterViewModel(
    private val animalRepository: AnimalRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(AnimalAddUiState())
    val uiState = _uiState.asStateFlow()

    fun onDismissSnackBar() {
        _uiState.update { it.copy(
            isAdded = false
        ) }
    }

    fun postAnimal(animalAddUiState: AnimalAddUiState) {
        viewModelScope.launch {
            _uiState.update { it.copy(
                url = animalAddUiState.url,
                reporter = animalAddUiState.reporter,
                animalName = animalAddUiState.animalName,
                animalType = animalAddUiState.animalType,
                address = animalAddUiState.address
            ) }
            animalRepository.postAnimal(_uiState.value.toDto()).fold(
                onSuccess = { data ->
                    _uiState.update { it.copy(isAdded = true) }
                },
                onFailure = { error ->
                    Log.e("okHttpError", error.message.toString())
                }
            )
        }
    }
}


class AnimalRegisterViewModelFactory(
    private val animalRepository: AnimalRepository

) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        AnimalRegisterViewModel(animalRepository) as T
}
