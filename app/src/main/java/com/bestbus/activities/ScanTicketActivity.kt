package com.bestbus.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bestbus.R
import com.bestbus.models.Ticket
import com.bestbus.utils.Util
import com.google.zxing.Result
import kotlinx.android.synthetic.main.activity_scan_ticket.*
import me.dm7.barcodescanner.zxing.ZXingScannerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScanTicketActivity : AppCompatActivity(), ZXingScannerView.ResultHandler, Callback<Ticket> {
    private var isScanning = true

    override fun onPause() {
        super.onPause()
        scannerView.stopCamera()
    }

    override fun onResume() {
        super.onResume()
        scannerView.setResultHandler(this)
        scannerView.startCamera()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_ticket)

        btnScanMore.setOnClickListener {
            if (isScanning) {
                isScanning = false
                btnScanMore.text = getString(R.string.scan_more)
                scannerView.visibility = View.GONE
                scannerView.stopCamera()
            } else {
                isScanning = true
                tvTicket.text = ""
                btnScanMore.text = getString(android.R.string.cancel)
                scannerView.visibility = View.VISIBLE
                scannerView.setResultHandler(this)
                scannerView.startCamera()
            }
        }
    }

    override fun handleResult(rawResult: Result?) {
        isScanning = false
        btnScanMore.text = getString(R.string.scan_more)
        scannerView.visibility = View.GONE
        scannerView.stopCamera()
        if (rawResult?.text == null) {
            tvTicket.text = getString(R.string.an_error_occurred)
        } else {
            progressBar.visibility = View.VISIBLE
            Util.apiClient.scanTicket(rawResult.text).enqueue(this)
        }
    }

    override fun onFailure(call: Call<Ticket>, t: Throwable) {
        progressBar.visibility = View.GONE
        tvTicket.text = t.message
    }

    override fun onResponse(call: Call<Ticket>, response: Response<Ticket>) {
        progressBar.visibility = View.GONE
        if (response.isSuccessful) {
            if (response.body()?.tourData != null) {
                response.body()!!.let {
                    var seat = ""
                    for (i in it.seatList) {
                        var s: Int = i / (it.tourData!!.count * 2) + 65
                        if (s > 90) {
                            s += 6
                        }
                        seat += s.toChar().toString().plus(i % (it.tourData!!.count * 2) + 1) + ", "
                    }
                    val ticketInformation = """
                            ID: ${it.id}
                            Name: ${it.name}
                            Email: ${it.email}
                            Phone: ${it.phone}
                            Bus: ${it.tourData!!.tourName}
                            Seat: ${seat.substring(0, seat.length - 2) + "."}
                            From: ${it.tourData.fromCity}
                            To: ${it.tourData.toCity}
                            Start time: ${it.tourData.startTime}
                            Start date: ${it.date}
                            End time: ${Util.getEndTime(it.tourData.startTime, it.tourData.time)}
                            End date: ${Util.getEndDate(it.date, it.tourData.startTime, it.tourData.time)}
                            Time: ${getString(R.string.hours, Util.formatFloat(it.tourData.time))}
                            Payment method: ${it.paymentMethod}
                            --------------------------
                            Total Amount: USD ${Util.formatFloat(it.totalAmount)}
                            """.trimIndent()
                    tvTicket.text = ticketInformation
                }
            } else {
                tvTicket.text = getString(R.string.an_error_occurred)
            }
        } else {
            tvTicket.text = response.errorBody()?.string()
        }
    }
}