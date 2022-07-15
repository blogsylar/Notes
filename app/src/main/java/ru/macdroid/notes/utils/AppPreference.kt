package ru.macdroid.notes.utils

import android.content.Context
import android.content.SharedPreferences

object AppPreference {

    private const val INIT_USER = "initUser"
    private const val TYPE_DB = "typeDB"
    private const val NAME_PREF = "preference"

    private lateinit var preferences: SharedPreferences

    fun getPreferences(context: Context) : SharedPreferences {
        preferences = context.getSharedPreferences(NAME_PREF, Context.MODE_PRIVATE)
        return preferences
    }

    fun setInitUser(init: Boolean) { // is user logged in
        preferences.edit()
            .putBoolean(INIT_USER, init)
            .apply()
    }

    fun setTypeDB(type: String) {
        preferences.edit()
            .putString(TYPE_DB, type)
            .apply()
    }

    fun getinitUser(): Boolean {
        return preferences.getBoolean(INIT_USER, false)
    }

    fun getTypeDB(): String {
        return preferences.getString(TYPE_DB, TYPE_ROOM).toString()
    }
}