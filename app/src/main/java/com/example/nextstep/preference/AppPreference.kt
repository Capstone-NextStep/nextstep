package com.example.nextstep.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.nextstep.data.model.UserSession
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "AppPrefs")

class AppPreference private constructor(private val dataStore: DataStore<Preferences>) {


    // Save user data
    suspend fun saveUserData(user: UserSession) {
        dataStore.edit { preferences ->
            preferences[UID_KEY] = user.uid
            preferences[EMAIL_KEY] = user.email
            user.displayName?.let {
                preferences[DISPLAY_NAME_KEY] = it
            }
            user.token?.let {
                preferences[TOKEN_KEY] = it
            }
        }
    }

    // Read user data
    fun getUserId(): Flow<String> = dataStore.data
        .map { preferences ->
            preferences[UID_KEY] ?: ""
        }

    fun getUserName(): Flow<String> = dataStore.data
        .map { preferences ->
            preferences[DISPLAY_NAME_KEY] ?: ""
        }

    fun getUserEmail(): Flow<String> = dataStore.data
        .map { preferences ->
            preferences[EMAIL_KEY] ?: ""
        }

    fun getUserToken(): Flow<String?> = dataStore.data
        .map { preferences ->
            preferences[TOKEN_KEY] ?: ""
        }

    fun getUserData(): Flow<UserSession> = dataStore.data
        .map { preferences ->
            UserSession(
                displayName = preferences[DISPLAY_NAME_KEY] ?: "",
                uid = preferences[UID_KEY] ?: "",
                email = preferences[EMAIL_KEY] ?: "",
                token = preferences[TOKEN_KEY] ?: ""
            )
        }

    // Clear all user data
    suspend fun clearUserData() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        private val UID_KEY = stringPreferencesKey("uid")
        private val EMAIL_KEY = stringPreferencesKey("email")
        private val DISPLAY_NAME_KEY = stringPreferencesKey("display_name")
        private val TOKEN_KEY = stringPreferencesKey("token")

        @Volatile
        private var INSTANCE: AppPreference? = null

        fun getInstance(dataStore: DataStore<Preferences>): AppPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = AppPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}