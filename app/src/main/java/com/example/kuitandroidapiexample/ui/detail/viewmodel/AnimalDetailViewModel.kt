package com.example.kuitandroidapiexample.ui.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kuitandroidapiexample.data.repository.AnimalRepository
import com.example.kuitandroidapiexample.ui.home.viewmodel.AnimalViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class AnimalDetailViewModel(
    private val animalRepository: AnimalRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AnimalDetailUiState())
}

class AnimalDetailViewModelFactory(
    private val animalRepository: AnimalRepository

) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        AnimalDetailViewModel(animalRepository) as T
}