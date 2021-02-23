package com.bestbus.activities

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bestbus.R
import java.io.IOException

abstract class BaseActivity : AppCompatActivity() {
    private lateinit var mProgressBar: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mProgressBar = Dialog(this)
        mProgressBar.setContentView(ProgressBar(this))
        mProgressBar.window?.setBackgroundDrawableResource(android.R.color.transparent)
        mProgressBar.setCancelable(false)
    }

    protected fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            mProgressBar.show()
        } else {
            mProgressBar.dismiss()
        }
    }

    protected fun showToast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    protected fun showToast(throwable: Throwable) {
        if (throwable is IOException) {
            showToast(getString(R.string.please_check_the_network_connection))
        } else {
            showToast(throwable.message)
        }
    }

    override fun startActivity(intent: Intent) {
        super.startActivity(intent)
        overridePendingTransition(R.anim.anim_scale_open, R.anim.anim_scale_exit)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.anim_scale_open, R.anim.anim_scale_exit)
    }
}