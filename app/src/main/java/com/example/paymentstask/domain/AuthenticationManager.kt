package com.example.paymentstask.domain

import android.content.Context
import android.content.SharedPreferences

class AuthenticationManager(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE)

    var authToken: String?
        get() = sharedPreferences.getString(TOKEN_KEY, null)
        set(value) {
            sharedPreferences.edit().putString(TOKEN_KEY, value).apply()
        }

    fun clearAuthToken() {
        sharedPreferences.edit().remove(TOKEN_KEY).apply()
    }

    companion object {
        const val TOKEN_KEY = "token"
    }
}

