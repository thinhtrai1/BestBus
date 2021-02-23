package com.bestbus.activities

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import com.bestbus.R
import com.bestbus.utils.Constant
import kotlinx.android.synthetic.main.activity_search_tour.*
import java.util.*

class SearchTourActivity : BaseActivity() {
    private val mCalendar: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_tour)

        edtDate.setText(Constant.dateFormat.format(mCalendar.time))
        edtDate.setOnClickListener {
            DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                mCalendar.set(year, month, dayOfMonth)
                edtDate.setText(Constant.dateFormat.format(mCalendar.time))
            }, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        btnSearch.setOnClickListener {
            startActivity(
                Intent(this, TourListActivity::class.java)
                    .putExtra("from", edtFrom.text.toString().trim())
                    .putExtra("to", edtTo.text.toString().trim())
                    .putExtra("date", edtDate.text.toString())
            )
        }
    }
}