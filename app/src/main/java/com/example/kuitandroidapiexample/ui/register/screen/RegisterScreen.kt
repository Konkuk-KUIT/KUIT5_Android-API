package com.example.kuitandroidapiexample.ui.register.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kuitandroidapiexample.ui.model.AnimalType
import com.example.kuitandroidapiexample.ui.register.componet.FindUTextField
import com.example.kuitandroidapiexample.ui.register.componet.TypeSelectContent
import com.example.kuitandroidapiexample.ui.register.model.AddAnimalStateType
import com.example.kuitandroidapiexample.ui.register.viewmodel.AnimalRegisterViewModel
import com.example.kuitandroidapiexample.ui.theme.FindUTheme.colors
import com.example.kuitandroidapiexample.ui.theme.FindUTheme.typography
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    padding: PaddingValues,
    navigateToBack: () -> Unit = {},
    viewModel: AnimalRegisterViewModel = viewModel()
) {
    var url by remember { mutableStateOf("") }
    var animalName by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var reporterName by remember { mutableStateOf("") }
    var animalType by remember { mutableStateOf(AnimalType.PROTECT) }

    val addAnimal by viewModel.addState.collectAsStateWithLifecycle()

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(addAnimal.addState) {
        if (addAnimal.addState == AddAnimalStateType.ADDSUCCESS) {
            scope.launch {
                val result = snackbarHostState.showSnackbar(
                    message = "등록 완료",
                    duration = SnackbarDuration.Short,
                    withDismissAction = true
                )
                if (result == SnackbarResult.Dismissed) {
                    viewModel.resetAddState()
                    navigateToBack()
                }
            }
        }else if(addAnimal.addState == AddAnimalStateType.ADDFAILURE){
            scope.launch {
                val result = snackbarHostState.showSnackbar(
                    message = "등록 실패",
                    duration = SnackbarDuration.Short,
                    withDismissAction = true
                )
                if (result == SnackbarResult.Dismissed) {
                    viewModel.resetAddState()
                    navigateToBack()
                }
            }
        }
    }

    val scrollState = rememberScrollState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(padding),
        ) {
            Column(
                modifier = Modifier
                    .matchParentSize()
                    .verticalScroll(scrollState)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Text(
                        text = "등록하기",
                        modifier = Modifier.align(Alignment.Center),
                        style = typography.semiBold.copy(
                            fontSize = 18.sp
                        )
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

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(padding = PaddingValues())
}