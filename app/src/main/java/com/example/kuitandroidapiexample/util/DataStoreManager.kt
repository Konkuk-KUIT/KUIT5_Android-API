package com.example.kuitandroidapiexample.util

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "my_prefs")

object DataStoreManager {

    fun keyFor(name: String) = stringPreferencesKey(name)

    suspend fun saveValue(context: Context, key: String, value: String) {
        val dataStoreKey = keyFor(key)
        context.dataStore.edit { preferences ->
            preferences[dataStoreKey] = value
        }
    }

    suspend fun getValue(context: Context, key: String): String? {
        val dataStoreKey = keyFor(key)
        return context.dataStore.data
            .map { preferences -> preferences[dataStoreKey] }
            .first()
    }

    suspend fun deleteValue(context: Context, key: String) {
        val dataStoreKey = keyFor(key)
        context.dataStore.edit { preferences ->
            preferences.remove(dataStoreKey)
        }
    }
}
