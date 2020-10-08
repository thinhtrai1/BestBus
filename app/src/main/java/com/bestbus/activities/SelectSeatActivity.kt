package com.bestbus.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.recyclerview.widget.GridLayoutManager
import com.bestbus.R
import com.bestbus.adapters.SeatAdapter
import com.bestbus.models.Tour
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_select_seat.*

class SelectSeatActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_seat)

        val tourData = Gson().fromJson(intent.getStringExtra("tour"), Tour::class.java)

        val metrics = DisplayMetrics()
        if (Build.VERSION.SDK_INT >= 29) {
            display?.getRealMetrics(metrics)
        } else {
            @Suppress("DEPRECATION")
            windowManager.defaultDisplay?.getRealMetrics(metrics)
        }
        val padding = metrics.widthPixels / (tourData.column * 6)
        rcvSeat.setPadding(padding, 0, padding, 0)

        val adapter = SeatAdapter(this, tourData.seatQuantity, tourData.seatSelected, tourData.column, metrics.widthPixels)
        rcvSeat.adapter = adapter
        rcvSeat.layoutManager = GridLayoutManager(this, tourData.column)

        btnSelectSeat.setOnClickListener {
            if (adapter.selectingList.isNotEmpty()) {
                tourData.seatSelected = adapter.selectingList.apply { sort() }
                startActivity(Intent(this, DetailActivity::class.java).putExtra("tour", Gson().toJson(tourData)))
            } else {
                showToast(getString(R.string.please_choose_seat))
            }
        }
    }
}