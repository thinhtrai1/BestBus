package com.bestbus.activities

import android.app.Application
import android.content.Context
import com.bestbus.utils.SharedPreferenceHelper

class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        SharedPreferenceHelper.instance = SharedPreferenceHelper(getSharedPreferences("BESTBUS2020", Context.MODE_PRIVATE))
    }
}