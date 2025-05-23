package com.example.kuitandroidapiexample.ui.home.screen

import androidx.compose.runtime.rememberCoroutineScope
import com.example.kuitandroidapiexample.util.DataStoreManager
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.flow.first

@Composable
fun DataStoreScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var input by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }


    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = input,
            onValueChange = { input = it },
            label = { Text("input value") }
        )

        Button(
            onClick = {
                scope.launch {
                    DataStoreManager.saveValue(
                        context = context,
                        key = "sample_key",
                        value = input
                    )
                }
            }
        ) {
            Text("pref 저장")
        }
        Button(
            onClick = {
                scope.launch {
                    DataStoreManager.deleteValue(
                        context = context,
                        key = "sample_key"
                    )
                }
            }
        ) {
            Text("pref 삭제")
        }
        Button(
            onClick = {
                scope.launch {
                    result = DataStoreManager.getValue(context, "sample_key")
                        .first()
                        ?: "없음"
                }
            }
        ) {
            Text("pref 조회")
        }

        Text("결과 : $result")
    }
}

@Preview(showBackground = true)
@Composable
private fun PreferenceScreenPreview() {
    PreferenceScreen()
}