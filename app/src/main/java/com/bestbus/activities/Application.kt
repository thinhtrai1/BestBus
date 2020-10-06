package com.bestbus.activities

import android.app.Application
import android.content.Context
import com.bestbus.utils.Util

class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        Util.sharedPreferences = getSharedPreferences("BESTBUS2020", Context.MODE_PRIVATE)
    }
}