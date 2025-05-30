package com.example.kuitandroidapiexample.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesOf
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "my_prefs")

object DataStoreManager {
    //DataStore 인스턴스 정의

    //키 값 정의
    val SAMPLE_KEY = stringPreferencesKey("sample_key")

    suspend fun saveValue(context: Context, key: String, value: String) {
        val dataStoreKey = stringPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[dataStoreKey] = value
        }
    }

    suspend fun getValue(context: Context, key: String): String? {
        val dataStoreKey = stringPreferencesKey(key)
        return context.dataStore.data.map { preferences ->
            preferences[dataStoreKey]
        }.first()
    }

    suspend fun deleteValue(context: Context, key: String) {
        val dataStoreKey = stringPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences.remove(dataStoreKey)
        }
    }
}