package com.example.kuitandroidapiexample.home.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import com.example.kuitandroidapiexample.util.SharedPreferenceManager

@Composable
fun PreferencesScreen(modifier: Modifier = Modifier){
    var input by remember {mutableStateOf("")}
    var result by remember {mutableStateOf("")}
    val context = LocalContext.current

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        TextField(
            value = input,
            onValueChange = {input = it},
            label = {Text("input value")}
        )

        Button(
            onClick = {
                SharedPreferenceManager.saveValue(context = context, key = "sample_key", value = input)
            }
        ){
            Text("pref 저장")
        }

        Button(
            onClick = {
                SharedPreferenceManager.deleteValue(context = context, key = "sample_key")
            }
        ){
            Text("pref 삭제")
        }

        Button(
            onClick = {
                result =
                    SharedPreferenceManager.getValue(context = context, key = "sample_key") ?: "없음"
            }
        ){
            Text("pref 조회")
        }

        Text("결과 : $result")
    }
}

@Preview(showBackground = true)
@Composable
private fun PreferencesScreenPreview() {
    PreferencesScreen()
}

