package com.example.kuitandroidapiexample.util

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private const val PREFS_NAME = "my_datastore"

private val Context.dataStore by preferencesDataStore(name = PREFS_NAME)

object DataStoreManager {
    private val SAMPLE_KEY = stringPreferencesKey("sample_key")

    suspend fun saveValue(context: Context, value: String) {
        context.dataStore.edit { prefs ->
            prefs[SAMPLE_KEY] = value
        }
    }

    suspend fun deleteValue(context: Context) {
        context.dataStore.edit { prefs ->
            prefs.remove(SAMPLE_KEY)
        }
    }

    suspend fun getValue(context: Context): String? {
        return context.dataStore.data.map { prefs ->
            prefs[SAMPLE_KEY]
        }.first()
    }
}