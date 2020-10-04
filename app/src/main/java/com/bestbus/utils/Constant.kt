package com.bestbus.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Constant {
    private var retrofit: ApiInterface? = null
    val client: ApiInterface
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

    const val PREF_EMAIL = "PREF_EMAIL"
    const val PREF_PASSWORD = "PREF_PASSWORD"
    const val PREF_USER = "PREF_USER"
}