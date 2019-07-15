package com.designloft.data

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(private val pref: SharedPreferences){
    private  val NAME_KEY = "name"
    private var FIRST_INIT = "first_init"

    fun saveName(token: String?){
        pref.edit().putString(NAME_KEY, token).apply()
    }

    fun getName() = pref.getString(NAME_KEY, "")


    fun fistInit(value: Boolean) {
        pref.edit().putBoolean(FIRST_INIT, value).apply()
    }

    fun isInit(): Boolean {
        return pref.getBoolean(FIRST_INIT, false)
    }
}