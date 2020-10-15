package com.bestbus.activities

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import com.bestbus.R
import com.bestbus.utils.Util
import kotlinx.android.synthetic.main.activity_add_tour.*
import kotlinx.android.synthetic.main.dialog_time_picker.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddTourActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_tour)

        edtStartTime.setOnClickListener {
            Dialog(this).apply {
                setContentView(R.layout.dialog_time_picker)
                show()
                btnCancel.setOnClickListener {
                    dismiss()
                }
                btnOk.setOnClickListener {
                    dismiss()
                    this@AddTourActivity.edtStartTime.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        String.format("%02d", timerPicker.hour) + ":" + String.format("%02d", timerPicker.minute)
                    } else {
                        @Suppress("DEPRECATION")
                        String.format("%02d", timerPicker.currentHour) + ":" + String.format("%02d", timerPicker.currentMinute)
                    }
                }
            }
        }

        btnConfirm.setOnClickListener {
            if (!isValid()) {
                return@setOnClickListener
            }
            showLoading(true)
            Util.apiClient.addTour(
                edtTourName.text.toString().trim(),
                edtOldPrice.text.toString(),
                edtPrice.text.toString(),
                edtStartTime.text.toString(),
                edtTime.text.toString(),
                edtFrom.text.toString().trim(),
                edtTo.text.toString().trim(),
                edtSeatQuantity.text.toString(),
                edtCount.text.toString(),
                edtVAT.text.toString()
            ).enqueue(object : Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    showLoading(false)
                    showToast(t.message)
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if (response.isSuccessful) {
                        showToast(response.body())
                    } else {
                        showToast(response.errorBody()?.string())
                    }
                    showLoading(false)
                }
            })
        }
    }

    private fun isValid(): Boolean {
        var b = true
        if (edtTourName.text.isBlank()) {
            edtTourName.error = ""
            b = false
        }
        if (edtPrice.text.isBlank()) {
            edtPrice.error = ""
            b = false
        }
        if (edtOldPrice.text.isBlank()) {
            edtOldPrice.setText(edtTourName.text.toString())
            b = false
        }
        if (edtVAT.text.isBlank()) {
            edtVAT.setText("0.05")
            b = false
        }
        if (edtStartTime.text.isBlank()) {
            edtStartTime.error = ""
            b = false
        }
        if (edtTime.text.isBlank()) {
            edtTime.error = ""
            b = false
        }
        if (edtFrom.text.isBlank()) {
            edtFrom.error = ""
            b = false
        }
        if (edtTo.text.isBlank()) {
            edtTo.error = ""
            b = false
        }
        if (edtSeatQuantity.text.isBlank()) {
            edtSeatQuantity.error = ""
            b = false
        }
        if (edtCount.text.isBlank()) {
            edtCount.setText("3")
            b = false
        }
        return b
    }
}