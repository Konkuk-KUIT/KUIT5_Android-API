package com.example.kuitandroidapiexample.register.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kuitandroidapiexample.model.AnimalType
import com.example.kuitandroidapiexample.register.componet.FindUTextField
import com.example.kuitandroidapiexample.register.componet.TypeSelectContent
import com.example.kuitandroidapiexample.register.viewmodel.RegisterViewModel
import com.example.kuitandroidapiexample.register.viewmodel.RegisterViewModelFactory
import com.example.kuitandroidapiexample.ui.theme.FindUTheme.colors
import com.example.kuitandroidapiexample.ui.theme.FindUTheme.typography

@Composable
fun RegisterScreen(
    padding: PaddingValues,
    navigateToBack: () -> Unit = {},
    viewModel: RegisterViewModel = hiltViewModel()
) {
    var url by remember { mutableStateOf("") }
    var animalName by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var reporterName by remember { mutableStateOf("") }
    var animalType by remember { mutableStateOf(AnimalType.PROTECT) }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    // 등록 성공 시 Snackbar → 뒤로 가기
    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) {
            val result = snackbarHostState.showSnackbar(
                message = "등록이 완료되었습니다.",
                duration = SnackbarDuration.Short
            )
            if (result == SnackbarResult.Dismissed) {
                navigateToBack()
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(innerPadding)
        ) {
            Column(modifier = Modifier.matchParentSize()) {
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
                colors = ButtonDefaults.buttonColors(containerColor = colors.orange),
                shape = RoundedCornerShape(8.dp),
                onClick = {
                    viewModel.registerAnimal(
                        url = url,
                        name = reporterName,
                        state = animalType,
                        breed = "", // 현재는 고정
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