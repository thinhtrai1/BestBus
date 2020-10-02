package com.bestbus.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        val adapter = SeatAdapter(this, tourData.seatQuantity, tourData.seatSelected)
        rcvSeat.adapter = adapter
        rcvSeat.layoutManager = GridLayoutManager(this, 6)

        btnSelectSeat.setOnClickListener {
            if (adapter.selectingList.isNotEmpty()) {
                tourData.seatSelected = adapter.selectingList
                startActivity(Intent(this, PaymentActivity::class.java).putExtra("tour", Gson().toJson(tourData)))
            } else {
                showToast("Please choose seat")
            }
        }
    }
}