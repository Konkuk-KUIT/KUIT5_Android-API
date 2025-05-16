import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDto
import com.example.kuitandroidapiexample.ui.model.AnimalType

data class AnimalListUiState(
    val animalList: List<ResponseAnimalDto> = emptyList()
)