package com.example.myapplication.Database


import android.content.Context
import androidx.core.content.contentValuesOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.settingsDataStorage: DataStore<Preferences> by preferencesDataStore(name = "whatever_name")

object DataStoreManager {

    suspend fun saveValue(context: Context, key: String, value: String) {

        val wrappedKey = stringPreferencesKey(key)
        context.settingsDataStorage.edit {
            it[wrappedKey] = value
        }

    }

    suspend fun getStringValue(context: Context, key: String, default: String = ""): String {

        val wrappedKey = stringPreferencesKey(key)
        val valueFlow: Flow<String> = context.settingsDataStorage.data.map {
            it[wrappedKey] ?: default
        }

        return valueFlow.first()
    }


}