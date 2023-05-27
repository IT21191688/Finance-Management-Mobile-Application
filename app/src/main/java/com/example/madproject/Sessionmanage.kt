package com.example.madproject

import android.content.Context

class Sessionmanage(private val context: Context) {
    private val sharedPref = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    private val editor = sharedPref.edit()

    fun saveSession(userId: Int) {
        editor.putInt("userId", userId)
        editor.apply()
    }

    fun removeSession() {
        editor.remove("userId")
        editor.apply()
    }

    fun getUserId(): Int {
        return sharedPref.getInt("userId", -1)
    }

    fun isLoggedIn(): Boolean {
        return getUserId() != -1
    }
}