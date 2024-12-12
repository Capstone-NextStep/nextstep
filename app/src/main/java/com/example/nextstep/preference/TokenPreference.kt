package com.example.nextstep.preference

import android.content.Context

class TokenPreference(context: Context) {
    companion object{
        private const val PREFS_NAME = "user_prefs"
        private const val TOKEN = "token"
        private const val USER_ID = "user_id"

    }

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveToken(token: String){
        val editor = preferences.edit()
        editor.putString(TOKEN, token)
        editor.apply()
    }

    fun saveUserId(userId: String){
        val editor = preferences.edit()
        editor.putString(USER_ID, userId)
        editor.apply()
    }

    fun getUserId(): String? {
        val userId = preferences.getString(USER_ID, "")
        return userId
    }

    fun getToken(): String? {
        val token = preferences.getString(TOKEN, "")
        return token
    }

    fun removePref(){
        val editor = preferences.edit()
        editor.remove(TOKEN)
        editor.remove(USER_ID)
        editor.apply()
    }


}