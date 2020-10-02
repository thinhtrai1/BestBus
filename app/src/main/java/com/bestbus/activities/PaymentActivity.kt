package com.bestbus.activities

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bestbus.R
import com.bestbus.models.Ticket
import com.bestbus.models.Tour
import com.bestbus.models.User
import com.bestbus.utils.Constant
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_payment.*
import net.glxn.qrgen.android.QRCode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class PaymentActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val user = Gson().fromJson(getSharedPreferences(Constant.APP_SHARED_PREF, Context.MODE_PRIVATE).getString(Constant.PREF_USER, ""), User::class.java)
        tvName.text = user.name
        tvEmail.text = user.email
        tvPhone.text = user.phone

        btnFinish.setOnClickListener {
            showLoading(true)
            val paymentMethod: String
            var paymentInformation: String?
            when {
                edtDebit.text.toString().isNotBlank() -> {
                    paymentMethod = "DEBIT/CREDIT_CARD"
                    paymentInformation = edtDebit.text.toString().trim()
                }
                edtNetBanking.text.toString().isNotBlank() -> {
                    paymentMethod = "NET_BANKING"
                    paymentInformation = edtNetBanking.text.toString().trim()
                }
                edtCodeShipping.text.toString().isNotBlank() -> {
                    paymentMethod = "CODE_SHIPPING"
                    paymentInformation = edtCodeShipping.text.toString().trim()
                }
                else -> {
                    paymentMethod = "AS_STATION"
                    paymentInformation = null
                }
            }
            val tourData = Gson().fromJson(intent.getStringExtra("tour"), Tour::class.java)
            Constant.client.booking(
                user.id,
                tourData.id,
                Gson().toJson(tourData.seatSelected!!),
                paymentMethod,
                paymentInformation,
                tourData.seatSelected!!.size * tourData.price
            ).enqueue(object : Callback<Ticket> {
                override fun onFailure(call: Call<Ticket>, t: Throwable) {
                    showToast(t.message)
                    showLoading(false)
                }

                override fun onResponse(call: Call<Ticket>, response: Response<Ticket>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            var seat = ""
                            for (s in it.seatList) {
                                seat += "$s, "
                            }
                            val ticketInformation = """
                            -------CODE: ${it.id}-------
                            Name: ${user.name}
                            Email: ${user.email}
                            Phone: ${user.phone}
                            Bus ID: ${it.tourId}
                            Seat: ${seat.substring(0, seat.lastIndex - 2)}
                            From: ${tourData.fromCity}
                            To: ${tourData.toCity}
                            Start time: ${SimpleDateFormat("HH:mm", Locale.US).format(Date(tourData.startTime))}
                            Start date: ${"..."}
                            End time: ${SimpleDateFormat("HH:mm", Locale.US).format(Date(tourData.startTime))}
                            End date: ${"..."}
                            Time: ${tourData.time}
                            Payment method: $paymentMethod
                            Payment Information: $paymentInformation
                            ---------------------
                            Total Amount: USD ${it.totalAmount}
                            """.trimIndent()

                            val textView = TextView(this@PaymentActivity)
                            textView.text = ticketInformation
                            val imageView = ImageView(this@PaymentActivity)
                            imageView.setImageBitmap(QRCode.from(ticketInformation).withColor(Color.WHITE, Color.TRANSPARENT).bitmap())
                            imageView.layoutParams = LinearLayout.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
                            val layout = LinearLayout(this@PaymentActivity)
                            layout.layoutParams = LinearLayout.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
                            layout.orientation = LinearLayout.VERTICAL
                            layout.setBackgroundResource(R.drawable.bg_main)
                            layout.addView(textView)
                            layout.addView(imageView)
                            layout.gravity = Gravity.CENTER
                            Dialog(this@PaymentActivity).apply {
                                setContentView(layout)
                                window?.attributes?.width = WindowManager.LayoutParams.MATCH_PARENT
                                window?.attributes?.height = WindowManager.LayoutParams.MATCH_PARENT
                                show()
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
}