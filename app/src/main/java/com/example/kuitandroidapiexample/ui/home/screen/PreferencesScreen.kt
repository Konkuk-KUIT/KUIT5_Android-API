package com.example.kuitandroidapiexample.ui.home.screen

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
import com.example.kuitandroidapiexample.util.DataStoreManager
import com.example.kuitandroidapiexample.util.SharedPreferenceManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext



@Composable
fun PreferencesScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
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
            label = { Text("Input value") }
        )

        // SharedPreferences 저장
        /*
        Button(onClick = {
            SharedPreferenceManager.saveValue(context, "sample_key", input)
        }) {
            Text("pref 저장")
        }
        */

        // DataStore 저장
        Button(onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                DataStoreManager.saveValue(context, "sample_key", input)
            }
        }) {
            Text("datastore 저장")
        }

        // SharedPreferences 삭제
        /*
        Button(onClick = {
            SharedPreferenceManager.deleteValue(context, "sample_key")
        }) {
            Text("pref 삭제")
        }
        */

        // DataStore 삭제
        Button(onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                DataStoreManager.deleteValue(context, "sample_key")
            }
        }) {
            Text("datastore 삭제")
        }

        // SharedPreferences 조회
        /*
        Button(onClick = {
            result = SharedPreferenceManager.getValue(context, "sample_key") ?: "값이 없습니다."
        }) {
            Text("pref 조회")
        }
        */

        // DataStore 조회
        Button(onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                val value = DataStoreManager.getValue(context, "sample_key") ?: "값이 없습니다."
                withContext(Dispatchers.Main) {
                    result = value
                }
            }
        }) {
            Text("datastore 조회")
        }

        Text("결과 : $result")
    }
}

@Preview(showBackground = true)
@Composable
private fun PreferencesScreenPreview() {
    PreferencesScreen()

}