package com.bestbus.utils

import android.content.SharedPreferences

class SharedPreferenceHelper(private val mSharedPreferences: SharedPreferences) {

    companion object {
        lateinit var instance: SharedPreferenceHelper
    }

    fun putString(key: String, value: String?): SharedPreferenceHelper {
        mSharedPreferences.edit().putString(key, value).apply()
        return this
    }

    fun getString(key: String): String? {
        return mSharedPreferences.getString(key, null)
    }

    fun clearAll() {
        mSharedPreferences.edit().clear().apply()
    }
}