package com.example.kuitandroidapiexample.ui.register.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.kuitandroidapiexample.data.dto.request.RequestAddAnimalDto
import com.example.kuitandroidapiexample.data.repository.AnimalRepository
import com.example.kuitandroidapiexample.ui.detail.viewmodel.AnimalDetailViewModel
import com.example.kuitandroidapiexample.ui.model.AnimalType
import com.example.kuitandroidapiexample.ui.register.addstate.AnimalRegisterAddState
import com.example.kuitandroidapiexample.ui.register.model.AddAnimalStateType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimalRegisterViewModel @Inject constructor(
    private val animalRepository: AnimalRepository
) : ViewModel() {

    private val _addState = MutableStateFlow(AnimalRegisterAddState())
    val addState = _addState.asStateFlow()

    fun postAddAnimal(request: RequestAddAnimalDto) {
        viewModelScope.launch {
            animalRepository.postAddAnimal(request).fold(
                onSuccess = {
                    Log.d("postAddAnimal", "등록 성공")
                    _addState.update { it.copy(addState = AddAnimalStateType.ADDSUCCESS) }
                },
                onFailure = { error ->
                    Log.e("postAddAnimal", error.message ?: "Unknown error")
                    _addState.update { it.copy(addState = AddAnimalStateType.ADDFAILURE) }
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

    fun resetAddState() {
        _addState.update { it.copy(addState = AddAnimalStateType.PENDING) }
    }

}

class AnimalRegisterViewModelFactory(
    private val animalRepository: AnimalRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        AnimalRegisterViewModel(animalRepository) as T
}