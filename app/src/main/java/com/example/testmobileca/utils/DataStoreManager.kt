package com.example.testmobileca.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

private const val FDJ_PREFERENCES = "fdj_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = FDJ_PREFERENCES)

class DataStoreManager constructor(val context: Context) : DataStoreOperations {

    companion object {

    }


    override suspend fun clear() {
        context.dataStore.edit {
            it.clear()
        }
    }

    /**
     * Generic function to fetch primitive type sync
     * we can add [Long] and [Float]
     */
    @Suppress("UNCHECKED_CAST")
    fun <D> getSyncData(key: String, default: D): D {
        val response = when (default) {
            is Int -> readIntData(key = key, default)
            is Double -> readDoubleData(key = key, default)
            is Boolean -> readBooleanData(key = key, default)
            is String -> readStringData(key = key, default)
            else -> throw  IllegalAccessException("IllegalAccessException")
        }
        return response as D
    }

    private fun readIntData(key: String, default: Int = 0): Int {
        var value = 0
        runBlocking {
            context.dataStore.data.first { preferences ->
                value = preferences[intPreferencesKey(key)] ?: default
                true
            }
        }
        return value
    }

    private fun readStringData(key: String, default: String = ""): String {
        var value = ""
        runBlocking {
            context.dataStore.data.first { preferences ->
                value = preferences[stringPreferencesKey(key)] ?: default
                true
            }
        }
        return value
    }

    private fun readBooleanData(key: String, default: Boolean = true): Boolean {
        var value = true
        runBlocking {
            context.dataStore.data.first { preferences ->
                value = preferences[booleanPreferencesKey(key)] ?: default
                true
            }
        }
        return value
    }

    private fun readDoubleData(key: String, default: Double = 0.0): Double {
        var value = 0.0
        runBlocking {
            context.dataStore.data.first { preferences ->
                value = preferences[doublePreferencesKey(key)] ?: default
                true
            }
        }
        return value
    }
}