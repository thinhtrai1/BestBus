package com.bestbus.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.recyclerview.widget.GridLayoutManager
import com.bestbus.R
import com.bestbus.adapters.SimpleSeatAdapter
import com.bestbus.models.Tour
import com.bestbus.utils.Util
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail.*
import java.util.ArrayList

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val tourData = Gson().fromJson(intent.getStringExtra("tour"), Tour::class.java)
        tourData.let {
            tvName.text = it.tourName
            tvStartDate.text = it.date
            tvEndDate.text = Util.getEndDate(it.date, it.startTime, it.time)
            tvStartTime.text = it.startTime
            tvEndTime.text = Util.getEndTime(it.startTime, it.time)
            tvTime.text = getString(R.string.hours, Util.formatFloat(it.time))
            tvFrom.text = it.fromCity
            tvTo.text = it.toCity
            val vat = it.price * it.seatSelected!!.size * it.vat
            tvVAT.text = getString(R.string.additional_vat, Util.formatFloat(vat))
            it.amount = it.price * it.seatSelected!!.size + vat
            tvAmount.text = getString(R.string.usd, Util.formatFloat(it.amount))
            val seats = ArrayList<String>()
            for (i in it.seatSelected!!) {
                seats.add((i / (it.count * 2) + 65).toChar().toString().plus(i % (it.count * 2) + 1))
            }
            val count = DisplayMetrics().apply {
                if (Build.VERSION.SDK_INT >= 29) {
                    display?.getRealMetrics(this)
                } else {
                    @Suppress("DEPRECATION")
                    windowManager.defaultDisplay?.getRealMetrics(this)
                }
            }.widthPixels / 100
            rcvSeats.adapter = SimpleSeatAdapter(seats)
            rcvSeats.layoutManager = GridLayoutManager(this, if (resources.displayMetrics.densityDpi < 600) count else count - 1)
        }

        btnBook.setOnClickListener {
            startActivity(Intent(this, PaymentActivity::class.java).putExtra("tour", Gson().toJson(tourData)))
        }
    }
}