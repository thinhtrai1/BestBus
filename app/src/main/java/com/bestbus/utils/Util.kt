package com.bestbus.utils

import android.content.SharedPreferences
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.ParseException
import java.util.*

object Util {
    lateinit var sharedPreferences: SharedPreferences

    private var retrofit: ApiInterface? = null
    val apiClient: ApiInterface
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl("http://ducthinh-bestbus.000webhostapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiInterface::class.java)
            }
            return retrofit!!
        }

    fun formatFloat(input: Float): String {
        return if (input == input.toLong().toFloat())
            String.format("%d", input.toLong())
        else
            String.format("%s", input)
    }

    fun getEndTime(startTime: String?, duration: Float): String? {
        return try {
            Constant.timeFormat.format(Calendar.getInstance().apply {
                time = Constant.timeFormat.parse(startTime ?: "")!!
                add(Calendar.MINUTE, (duration * 60).toInt())
            }.time)
        } catch (e: ParseException) {
            startTime
        }
    }

    fun getEndDate(startDate: String?, duration: Float): String? {
        return try {
            Constant.dateFormat.format(Calendar.getInstance().apply {
                time = Constant.dateFormat.parse(startDate ?: "")!!
                add(Calendar.MINUTE, (duration * 60).toInt())
            }.time)
        } catch (e: ParseException) {
            startDate
        }
    }
}