package com.example.kuitandroidapiexample.util

import android.content.Context
import android.util.Log
import androidx.core.content.edit
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

object DataStoreManager {
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "my_datastore")


    suspend fun saveValue(context: Context, key: String, value: String) {
        val sampleCounter = stringPreferencesKey(key)
        Log.d("sv", value)
        context.dataStore.edit { settings ->
            settings[sampleCounter] = value
        }
    }

    suspend fun getValue(context: Context, key: String): String? {
        val sampleCounter = stringPreferencesKey(key)

        val sampleCounterValue = context.dataStore.data.first()[sampleCounter]
        return sampleCounterValue
    }

    suspend fun deleteValue(context: Context, key: String) {
        val sampleCounter = stringPreferencesKey(key)
        context.dataStore.edit { settings ->
            settings.remove(sampleCounter)
        }
    }
}
