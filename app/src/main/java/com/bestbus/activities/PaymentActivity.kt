package com.bestbus.activities

import android.Manifest
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.WindowManager.LayoutParams
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bestbus.R
import com.bestbus.models.Ticket
import com.bestbus.models.Tour
import com.bestbus.models.User
import com.bestbus.utils.Constant
import com.bestbus.utils.Util
import com.google.gson.Gson
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import kotlinx.android.synthetic.main.activity_payment.*
import kotlinx.android.synthetic.main.dialog_booking_success.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream

class PaymentActivity : BaseActivity() {
    private var viewSelecting: TextView? = null
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        user = Gson().fromJson(Util.sharedPreferences.getString(Constant.PREF_USER, ""), User::class.java)?.apply {
            edtName.setText(name)
            edtEmail.setText(email)
            edtPhone.setText(phone)
        }

        imvHome.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
        }

        edtCreditCard.setOnClickListener {
            AlertDialog
                .Builder(this)
                .setMessage(getString(R.string.coming_soon))
                .setPositiveButton(android.R.string.ok, null)
                .show()
        }

        edtNetBanking.setOnClickListener {
            AlertDialog
                .Builder(this)
                .setMessage(getString(R.string.coming_soon))
                .setPositiveButton(android.R.string.ok, null)
                .show()
        }

        edtCodeShipping.setOnFocusChangeListener { _, b ->
            if (b) {
                newSelect(edtCodeShipping)
            }
        }

        edtAtBusStation.setOnClickListener {
            newSelect(edtAtBusStation)
            edtCodeShipping.clearFocus()
        }

        btnConfirm.setOnClickListener {
            if (viewSelecting == null) {
                showToast(getString(R.string.please_choose_payment_method))
                return@setOnClickListener
            }
            AlertDialog
                .Builder(this)
                .setMessage(getString(R.string.are_your_sure))
                .setPositiveButton(android.R.string.ok) { _, _ -> booking() }
                .setNegativeButton(android.R.string.cancel, null)
                .show()
        }
    }

    private fun newSelect(viewSelect: TextView) {
        viewSelecting?.isSelected = false
        viewSelecting = viewSelect
        viewSelecting!!.isSelected = true
    }

    private fun booking() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)
                return
            }
        }
        showLoading(true)
        val paymentMethod: String
        val paymentInformation: String
        when (viewSelecting) {
            edtCreditCard -> {
                paymentMethod = "DEBIT/CREDIT_CARD"
                paymentInformation = edtCreditCard.text.toString().trim()
            }
            edtNetBanking -> {
                paymentMethod = "NET_BANKING"
                paymentInformation = edtNetBanking.text.toString().trim()
            }
            edtCodeShipping -> {
                paymentMethod = "CODE_SHIPPING"
                paymentInformation = edtCodeShipping.text.toString().trim()
            }
            else -> {
                paymentMethod = "AT_STATION"
                paymentInformation = ""
            }
        }
        val tourData = Gson().fromJson(intent.getStringExtra("tour"), Tour::class.java)
        Util.apiClient.booking(
            user?.id,
            edtName.text.toString().trim(),
            edtEmail.text.toString().trim(),
            edtPhone.text.toString().trim(),
            tourData.id,
            tourData.date,
            Gson().toJson(tourData.seatSelected!!),
            paymentMethod,
            paymentInformation,
            tourData.amount
        ).enqueue(object : Callback<Ticket> {
            override fun onFailure(call: Call<Ticket>, t: Throwable) {
                showToast(t.message)
                showLoading(false)
            }

            override fun onResponse(call: Call<Ticket>, response: Response<Ticket>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        var seat = ""
                        for (i in it.seatList) {
                            seat += (i / (it.tourData!!.count * 2) + 65).toChar().toString().plus(i % (it.tourData!!.count * 2) + 1) + ", "
                        }
                        val ticketInformation = getString(R.string.id) + ": " + it.id + "\n" +
                                getString(R.string.your_name) + ": " + it.name + "\n" +
                                getString(R.string.email) + ": " + it.email + "\n" +
                                getString(R.string.phone) + ": " + it.phone + "\n" +
                                getString(R.string.bus) + ": " + it.tourData!!.tourName + "\n" +
                                getString(R.string.seats) + " " + seat.substring(0, seat.length - 2) + "." + "\n" +
                                getString(R.string.from) + ": " + it.tourData.fromCity + "\n" +
                                getString(R.string.to) + ": " + it.tourData.toCity + "\n" +
                                getString(R.string.start_time) + ": " + it.tourData.startTime + "\n" +
                                getString(R.string.start_date) + ": " + it.date + "\n" +
                                getString(R.string.end_time) + ": " + Util.getEndTime(it.tourData.startTime, it.tourData.time) + "\n" +
                                getString(R.string.end_date) + ": " + Util.getEndDate(it.date, it.tourData.startTime, it.tourData.time) + "\n" +
                                getString(R.string.time) + ": " + getString(R.string.hours, Util.formatFloat(tourData.time)) + "\n" +
                                getString(R.string.payment_method) + ": " + it.paymentMethod + "\n" +
                                "--------------------------" + "\n" +
                                getString(R.string.total_amount) + " USD " + Util.formatFloat(it.totalAmount)
                        Dialog(this@PaymentActivity).apply {
                            window?.setFlags(LayoutParams.FLAG_SECURE, LayoutParams.FLAG_SECURE)
                            setContentView(R.layout.dialog_booking_success)
                            setCancelable(false)
                            window?.attributes?.width = LayoutParams.MATCH_PARENT
                            window?.attributes?.height = LayoutParams.MATCH_PARENT
                            show()
                            tvInformation.text = ticketInformation
                            imvQRCode.setImageBitmap(generateQRCode(it.qrCode))
                            btnFinish.setOnClickListener {
                                startActivity(Intent(this@PaymentActivity, HomeActivity::class.java)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
                            }
                            layoutTicket.post {
                                val bitmap = Bitmap.createBitmap(layoutTicket.width, layoutTicket.height, Bitmap.Config.ARGB_8888)
                                val canvas = Canvas(bitmap)
                                layoutTicket.draw(canvas)
                                Thread(Runnable {
                                    val path = File(Constant.TICKET_FOLDER)
                                    if (!path.exists()) {
                                        path.mkdirs()
                                    }
                                    val imageFile = File(path, Util.getTicketFileName(tourData.date, tourData.startTime, it.id))
                                    val outputStream = FileOutputStream(imageFile)
                                    bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream)
                                    outputStream.flush()
                                    outputStream.close()
                                }).start()
                            }
                        }
                    }
                } else {
                    showToast(response.errorBody()?.string())
                }
                showLoading(false)
            }
        })
    }

    private fun generateQRCode(data: String?, size: Int = 256): Bitmap {
        val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        try {
//            val hints: MutableMap<EncodeHintType?, Any?> = EnumMap(EncodeHintType::class.java)
//            hints[EncodeHintType.MARGIN] = 0
//            val bitMatrix = MultiFormatWriter().encode(code, BarcodeFormat.QR_CODE, size, size, hints)
            val bitMatrix = QRCodeWriter().encode(data, BarcodeFormat.QR_CODE, size, size)
            for (x in 0 until size) {
                for (y in 0 until size) {
                    bitmap.setPixel(x, y, if (bitMatrix[x, y]) Color.WHITE else Color.TRANSPARENT)
                }
            }
        } catch (e: WriterException) {
            e.printStackTrace()
        }
        return bitmap
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (requestCode == 0) booking()
        }
    }
}