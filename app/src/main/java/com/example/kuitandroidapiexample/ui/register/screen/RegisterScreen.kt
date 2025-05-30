package com.example.kuitandroidapiexample.ui.register.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.kuitandroidapiexample.data.dto.request.RequestAddAnimalDto
import com.example.kuitandroidapiexample.ui.register.component.FindUTextField
import com.example.kuitandroidapiexample.ui.register.component.TypeSelectContent
import com.example.kuitandroidapiexample.ui.register.viewmodel.RegisterViewModel
import com.example.kuitandroidapiexample.ui.theme.FindUTheme.colors
import com.example.kuitandroidapiexample.ui.theme.FindUTheme.typography
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    padding: PaddingValues,
    navigateToBack: () -> Unit = {},
    viewModel: RegisterViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.resetUiState()
    }

    LaunchedEffect(uiState.isAdded) {
        if (uiState.isAdded == true) {

            launch {
                snackbarHostState.showSnackbar(
                    message = "등록이 완료되었습니다.",
                    actionLabel = "x"
                )
            }
//            if (result == SnackbarResult.ActionPerformed) {
//                navigateToBack()
//            }

            delay(3000L)
            navigateToBack()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) { innerPadding ->
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
                        style = typography.semiBold.copy(
                            fontSize = 18.sp
                        )
                    )
                }

                FindUTextField(
                    modifier = Modifier.padding(top = 21.dp),
                    title = "사진 url 입력",
                    value = uiState.url,
                    ) {
                    viewModel.updateUrl(it)
                    }

                FindUTextField(
                    modifier = Modifier.padding(top = 15.dp),
                    title = "이름 입력",
                    value = uiState.reporterName,
                    onValueChanged = {
                        viewModel.updateReporterName(it)
                    })

                FindUTextField(
                    modifier = Modifier.padding(top = 15.dp),
                    title = "주소 입력",
                    value = uiState.address,
                    onValueChanged = {
                        viewModel.updateAddress(it)
                    })

                FindUTextField(
                    modifier = Modifier.padding(top = 15.dp),
                    title = "동물 이름",
                    value = uiState.animalName,
                    onValueChanged = {
                        viewModel.updateAnimalName(it)
                    })

                TypeSelectContent(
                    modifier = Modifier.padding(start = 16.dp, top = 30.dp),
                    animalType = uiState.animalType,
                    onSelected = {
                        viewModel.updateAnimalType(it)
                    })
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
                    val request = RequestAddAnimalDto(
                        id = 0,
                        url = uiState.url,
                        name = uiState.reporterName,
                        state = uiState.animalType,
                        breed = uiState.animalName,
                        address = uiState.address
                    )
                    viewModel.postAddAnimal(request)
                }) {
                Text(
                    text = "등록하기", style = typography.semiBold.copy(fontSize = 18.sp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(
        padding = PaddingValues()
    )
}