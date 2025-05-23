package com.example.kuitandroidapiexample.ui.home.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.preferences.preferencesDataStore
import com.example.kuitandroidapiexample.util.DataStoreManager
import com.example.kuitandroidapiexample.util.SharedPreferenceManager
import kotlinx.coroutines.launch

@Composable
fun PreferencesScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

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
//                SharedPreferenceManager.saveValue(
//                    context = context,
//                    key = "sample key",
//                    value = input
//                )
                coroutineScope.launch {
                    DataStoreManager.saveValue(
                        context = context,
                        key = "sample_data_key",
                        value = input
                    )
                }
            }
        ) {
            Text("pref 저장")
        }
        Button(
            onClick = {
//                SharedPreferenceManager.deleteValue(context = context, key = "sample key")
                coroutineScope.launch {
                    DataStoreManager.deleteValue(
                        context = context,
                        key = "sample_data_key"
                    )
                }
            }
        ) {
            Text("pref 삭제")
        }
        Button(
            onClick = {
//                result = SharedPreferenceManager.getValue(context = context, key = "sample key")?:"없음"
                coroutineScope.launch {
                    result = DataStoreManager.getValue(
                        context = context,
                        key = "sample_data_key"
                    )?:"없음"
                }
            }
        ) {
            Text("pref 조회")
        }

        Text("결과:$result")
    }
}

@Preview(showBackground = true)
@Composable
private fun PreferencesScreenPreview() {
    PreferencesScreen()
}