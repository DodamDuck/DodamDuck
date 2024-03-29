package org.chosun.dodamduck.network.auth

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TokenManager @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val tokenRefresher: TokenRefresher
) {
    companion object {
        private val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
        private val REFRESH_TOKEN_KEY = stringPreferencesKey("refresh_token")
    }

    val accessToken: Flow<String?> = dataStore.data.map { preferences ->
        preferences[ACCESS_TOKEN_KEY]
    }

    val refreshToken: Flow<String?> = dataStore.data.map { preferences ->
        preferences[REFRESH_TOKEN_KEY]
    }

    suspend fun saveAccessToken(token: String) =
        dataStore.edit { prefs -> prefs[ACCESS_TOKEN_KEY] = token }

    suspend fun saveRefreshToken(token: String) =
        dataStore.edit { prefs -> prefs[REFRESH_TOKEN_KEY] = token }

    suspend fun refreshAccessToken(refreshToken: String): String {
        val newAccessToken = tokenRefresher.refreshAccessToken(refreshToken)
        saveAccessToken(newAccessToken)
        return newAccessToken
    }

    suspend fun deleteAccessToken() = dataStore.edit { prefs -> prefs.remove(ACCESS_TOKEN_KEY) }
}