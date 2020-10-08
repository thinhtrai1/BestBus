package com.bestbus.activities

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import android.widget.EditText
import androidx.core.content.ContextCompat
import com.bestbus.R
import com.bestbus.models.Ticket
import com.bestbus.models.Tour
import com.bestbus.models.User
import com.bestbus.utils.Constant
import com.bestbus.utils.Util
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_payment.*
import kotlinx.android.synthetic.main.dialog_booking_success.*
import net.glxn.qrgen.android.QRCode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class PaymentActivity : BaseActivity() {
    private var viewSelecting: EditText? = null
    private var user: User? = null
    private var colorNormal = 0
    private var colorSelect = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        user = Gson().fromJson(Util.sharedPreferences.getString(Constant.PREF_USER, ""), User::class.java)
        edtName.setText(user?.name)
        edtEmail.setText(user?.email)
        edtPhone.setText(user?.phone)

        colorNormal = ContextCompat.getColor(this, R.color.gray_dark)
        colorSelect = ContextCompat.getColor(this, R.color.color_selecting)

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

        viewCodeShipping.setOnClickListener {
            newSelect(edtCodeShipping)
        }

        edtAtBusStation.setOnClickListener {
            newSelect(edtAtBusStation)
        }

        btnFinish.setOnClickListener {
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

    private fun newSelect(viewSelect: EditText) {
        viewSelecting?.isSelected = false
        viewSelecting?.setTextColor(colorNormal)
        viewSelecting = viewSelect
        viewSelecting!!.isSelected = true
        viewSelecting!!.setTextColor(colorSelect)
    }

    private fun booking() {
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
        val code = UUID.randomUUID().toString() + UUID.randomUUID().toString() + UUID.randomUUID().toString()
        Util.apiClient.booking(
            user?.id,
            edtName.text.toString().trim(),
            edtEmail.text.toString().trim(),
            edtPhone.text.toString().trim(),
            tourData.id,
            Gson().toJson(tourData.seatSelected!!),
            paymentMethod,
            paymentInformation,
            tourData.amount,
            code
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
                            seat += (i / (tourData.column * 2) + 65).toChar().toString().plus(i % (tourData.column * 2) + 1) + ", "
                        }
                        val ticketInformation = """
                            ID: ${it.id}
                            Name: ${it.name}
                            Email: ${it.email}
                            Phone: ${it.phone}
                            Bus ID: ${tourData.tourName}
                            Seat: ${seat.substring(0, seat.length - 2) + "."}
                            From: ${tourData.fromCity}
                            To: ${tourData.toCity}
                            Start time: ${tourData.startTime}
                            Start date: ${tourData.date}
                            End time: ${Util.getEndTime(tourData.startTime, tourData.time)}
                            End date: ${Util.getEndDate(tourData.date, tourData.startTime, tourData.time)}
                            Time: ${tourData.time}
                            Payment method: $paymentMethod
                            --------------------------
                            Total Amount: USD ${Util.formatFloat(it.totalAmount)}
                            """.trimIndent()

                        Dialog(this@PaymentActivity).apply {
                            setContentView(R.layout.dialog_booking_success)
                            window?.attributes?.width = WindowManager.LayoutParams.MATCH_PARENT
                            window?.attributes?.height = WindowManager.LayoutParams.MATCH_PARENT
                            show()
                            tvInformation.text = ticketInformation
                            imvQRCode.setImageBitmap(QRCode.from(it.qrCode).withColor(Color.WHITE, Color.TRANSPARENT).bitmap())
                        }

//                            Bitmap bitmap = your bitmap;
//                            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//                            bitmap.compress(CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
//                            byte[] bitmapdata = bos.toByteArray();
//
////write the bytes in file
//                            FileOutputStream fos = new FileOutputStream(f);
//                            fos.write(bitmapdata);
//                            fos.flush();
//                            fos.close();
                    }
                } else {
                    showToast(response.errorBody()?.string())
                }
                showLoading(false)
            }
        })
    }
}