package com.example.kuitandroidapiexample.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.kuitandroidapiexample.ui.navigation.Route
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "my_datastore")

object DataStoreManager {
    private val SAMPLE_KEY = stringPreferencesKey("sample_key")

    suspend fun saveValue(context: Context, value: String) {
        context.dataStore.edit { prefs ->
            prefs[SAMPLE_KEY] = value
        }
    }

    fun getValue(context: Context): Flow<String?> {
        return context.dataStore.data.map { prefs ->
            prefs[SAMPLE_KEY]
        }
    }

    suspend fun deleteValue(context: Context) {
        context.dataStore.edit { prefs ->
            prefs.remove(SAMPLE_KEY)
        }
    }
}