package com.bestbus.utils

import android.content.SharedPreferences
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.ParseException
import java.util.*

object Util {
    lateinit var sharedPreferences: SharedPreferences

    const val BASE_URL = "http://ducthinh-bestbus.000webhostapp.com/"
    private var retrofit: ApiInterface? = null
    val apiClient: ApiInterface
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
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

    fun getEndDate(startDate: String?, startTime: String?, duration: Float): String? {
        return try {
            Constant.dateFormat.format(Calendar.getInstance().apply {
                time = Constant.dateFormat.parse(startDate ?: "")!!
                Calendar.getInstance().apply {
                    time = Constant.timeFormat.parse(startTime ?: "")!!
                }.let {
                    set(Calendar.HOUR, it.get(Calendar.HOUR_OF_DAY))
                    set(Calendar.MINUTE, it.get(Calendar.MINUTE))
                }
                add(Calendar.MINUTE, (duration * 60).toInt())
            }.time)
        } catch (e: ParseException) {
            startDate
        }
    }

    fun getTicketFileName(startDate: String?, startTime: String?, id: Int): String {
        return try {
            Calendar.getInstance().apply {
                time = Constant.dateFormat.parse(startDate ?: "")!!
                Calendar.getInstance().apply {
                    time = Constant.timeFormat.parse(startTime ?: "")!!
                }.let {
                    set(Calendar.HOUR, it.get(Calendar.HOUR_OF_DAY))
                    set(Calendar.MINUTE, it.get(Calendar.MINUTE))
                }
            }.timeInMillis.toString() + id + ".png"
        } catch (e: ParseException) {
            Calendar.getInstance().timeInMillis.toString() + id + ".png"
        }
    }
}