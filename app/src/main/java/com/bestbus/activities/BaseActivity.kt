package com.bestbus.activities

import android.app.Dialog
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    private lateinit var mProgressBar: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mProgressBar = Dialog(this)
        mProgressBar.setContentView(ProgressBar(this))
        mProgressBar.window?.setBackgroundDrawableResource(android.R.color.transparent)
        mProgressBar.setCancelable(false)
    }

    fun showToast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            mProgressBar.show()
        } else {
            mProgressBar.dismiss()
        }
    }
}