package com.example.s3vior.cache

import android.content.Context
import android.content.SharedPreferences
import com.example.s3vior.domain.model.Profile
import com.example.s3vior.domain.model.User
import com.example.s3vior.utils.Constants.MOBILE_KEY
import com.example.s3vior.utils.Constants.NAME_KEY
import com.example.s3vior.utils.Constants.PREF_NAME
import com.example.s3vior.utils.Constants.TOKEN_KEY


object UserInfo {
    fun isUserLoggedIn(context: Context): Boolean {
        val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val name = pref.getString(NAME_KEY, null)
        return name != null
    }

    fun getUserData(context: Context): User {
        val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val name = pref.getString(NAME_KEY, "Default Name").toString()
        val mobile = pref.getString(MOBILE_KEY, "Default Mobile").toString()
        val token = pref.getString(TOKEN_KEY, "").toString()
        return User(null, name, mobile, "", token)
    }

    fun saveUserData(context: Context, user: User) {
        val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = pref.edit()
        editor.putString(NAME_KEY, user.user_name)
        editor.putString(MOBILE_KEY, user.phone_number)
        editor.putString(TOKEN_KEY, user.token)
        editor.apply()
    }

    fun getUserID(context: Context): Profile {
        val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val id = pref.getInt("id", 0)
        return Profile(id)
    }

    fun saveUserID(context: Context, id: Int) {
        val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = pref.edit()
        editor.putInt("id", id)
        editor.apply()
    }
}