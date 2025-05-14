import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kuitandroidapiexample.home.viewmodel.AnimalViewModel
import com.example.kuitandroidapiexample.model.AnimalType
import com.example.kuitandroidapiexample.register.componet.FindUTextField
import com.example.kuitandroidapiexample.register.componet.TypeSelectContent
import com.example.kuitandroidapiexample.ui.theme.FindUTheme.colors
import com.example.kuitandroidapiexample.ui.theme.FindUTheme.typography
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    padding: PaddingValues,
    navigateToBack: () -> Unit = {},
    viewModel: AnimalViewModel = viewModel()
) {
    var url by remember { mutableStateOf("") }
    var animalName by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var reporterName by remember { mutableStateOf("") }
    var animalType by remember { mutableStateOf(AnimalType.PROTECT) }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val addAnimalState by viewModel.addAnimalState

    // 스낵바 + 뒤로가기
    LaunchedEffect(addAnimalState) {
        if (addAnimalState == true) {
            scope.launch {
                snackbarHostState.showSnackbar(
                    message = "등록이 완료되었습니다.",
                    duration = SnackbarDuration.Short
                )
                delay(2000)
                navigateToBack()
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier.matchParentSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Text(
                        text = "등록하기",
                        modifier = Modifier.align(Alignment.Center),
                        style = typography.semiBold.copy(fontSize = 18.sp)
                    )
                }

                FindUTextField(
                    modifier = Modifier.padding(top = 21.dp),
                    title = "사진 url 입력",
                    value = url
                ) { url = it }

                FindUTextField(
                    modifier = Modifier.padding(top = 15.dp),
                    title = "이름 입력",
                    value = reporterName
                ) { reporterName = it }

                FindUTextField(
                    modifier = Modifier.padding(top = 15.dp),
                    title = "주소 입력",
                    value = address
                ) { address = it }

                FindUTextField(
                    modifier = Modifier.padding(top = 15.dp),
                    title = "동물 이름",
                    value = animalName
                ) { animalName = it }

                TypeSelectContent(
                    modifier = Modifier.padding(start = 16.dp, top = 30.dp),
                    animalType = animalType
                ) { animalType = it }
            }

            Button(
                modifier = Modifier
                    .padding(bottom = 50.dp)
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
                    .height(50.dp)
                    .align(Alignment.BottomCenter),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.orange
                ),
                shape = RoundedCornerShape(8.dp),
                onClick = {
                    viewModel.addAnimal(
                        url = url,
                        name = reporterName,
                        state = animalType,
                        breed = "",
                        address = address
                    )
                }
            ) {
                Text(
                    text = "등록하기",
                    style = typography.semiBold.copy(fontSize = 18.sp)
                )
            }
        }
    }
}
