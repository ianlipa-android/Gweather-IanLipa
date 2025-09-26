package com.example.gweather.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class SecurePreference(context: Context) {

    val GENERAL_PREF_KEY = "user_credentials_prefs"
    val USER_PREF_KEY = "user_specific_key"

    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
    private val sharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
        GENERAL_PREF_KEY,
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveUserPasswordHash(userName: String, hash: String) {
        sharedPreferences.edit {
            putString(USER_PREF_KEY+"_$userName", hash)
        }
    }

    fun getUserPasswordHash(userName: String): String? {
        return sharedPreferences.getString(USER_PREF_KEY+"_$userName", null)
    }

    fun setIsLoggedIn(userName: String, isLoggedIn: Boolean) {
        sharedPreferences.edit {
            putBoolean(USER_PREF_KEY+"_isLoggedIn_$userName", isLoggedIn)
        }
    }

    fun isLoggedIn(userName: String): Boolean {
        return sharedPreferences.getBoolean(USER_PREF_KEY+"_isLoggedIn_$userName", false)
    }

    fun setCurrentUserName(userName: String) {
        sharedPreferences.edit {
            putString(USER_PREF_KEY+"_un", userName)
        }
    }

    fun getCurrentUserName(): String? {
        return sharedPreferences.getString(USER_PREF_KEY+"_un", "")
    }

    fun clearSessionPrefs(userName: String) {
        sharedPreferences.edit {
            remove(USER_PREF_KEY+"_un")
            putBoolean(USER_PREF_KEY+"_isLoggedIn_$userName", false)
        }
    }

    fun clearAllPrefs() {
        sharedPreferences.edit {
            clear()
        }
    }

}