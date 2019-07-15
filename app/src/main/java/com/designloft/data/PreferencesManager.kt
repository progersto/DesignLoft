package com.designloft.data

import android.content.SharedPreferences

class PreferencesManager(private val pref: SharedPreferences){
    private  val NAME_KEY = "name"

    fun saveName(token: String?){
        pref.edit().putString(NAME_KEY, token).apply()
    }

    fun getName() = pref.getString(NAME_KEY, "")
}