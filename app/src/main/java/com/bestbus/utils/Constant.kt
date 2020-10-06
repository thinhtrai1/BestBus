package com.bestbus.utils

import java.text.SimpleDateFormat
import java.util.*

object Constant {
    const val PREF_EMAIL = "PREF_EMAIL"
    const val PREF_PASSWORD = "PREF_PASSWORD"
    const val PREF_USER = "PREF_USER"

    val timeFormat = SimpleDateFormat("HH:mm", Locale.US)
    val dateFormat = SimpleDateFormat("EEE, dd MMM yyyy", Locale.US)
}