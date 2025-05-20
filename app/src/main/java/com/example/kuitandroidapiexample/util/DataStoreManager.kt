package com.example.kuitandroidapiexample.util

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


private val Context.dataStore by preferencesDataStore(name = "my_datastore")

object DataStoreManager {

    suspend fun saveValue(context: Context, key: String, value: String) {
        val dataStoreKey = stringPreferencesKey(key)
        context.dataStore.edit { prefs ->
            prefs[dataStoreKey] = value
        }
    }

    suspend fun getValue(context: Context, key: String): String? {
        val dataStoreKey = stringPreferencesKey(key)
        return context.dataStore.data
            .map { prefs -> prefs[dataStoreKey] }
            .first()
    }

    suspend fun deleteValue(context: Context, key: String) {
        val dataStoreKey = stringPreferencesKey(key)
        context.dataStore.edit { prefs ->
            prefs.remove(dataStoreKey)
        }
    }
}
