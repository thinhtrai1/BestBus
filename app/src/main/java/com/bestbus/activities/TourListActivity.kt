package com.bestbus.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bestbus.R
import com.bestbus.adapters.TourListAdapter
import com.bestbus.models.Tour
import com.bestbus.utils.Util
import kotlinx.android.synthetic.main.activity_tour_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

class TourListActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tour_list)

        val from = intent.getStringExtra("from") ?: ""
        val to = intent.getStringExtra("to") ?: ""
        val date = intent.getStringExtra("date")
        tvFrom.text = if (from.isNotEmpty()) from else "..."
        tvTo.text = if (to.isNotEmpty()) to else "..."
        tvDate.text = date

        Util.apiClient.getTour(from, to).enqueue(object : Callback<ArrayList<Tour>> {
            override fun onFailure(call: Call<ArrayList<Tour>>, t: Throwable) {
                progressBar.visibility = View.GONE
                showToast(t)
            }

            override fun onResponse(call: Call<ArrayList<Tour>>, response: Response<ArrayList<Tour>>) {
                progressBar.visibility = View.GONE
                if (response.isSuccessful) {
                    response.body()?.let {
                        rcvTourList.visibility = View.VISIBLE
                        rcvTourList.layoutManager = LinearLayoutManager(this@TourListActivity, LinearLayoutManager.VERTICAL, false)
                        rcvTourList.adapter = TourListAdapter(this@TourListActivity, it, date)
                    }
                } else {
                    showToast(response.errorBody()?.string())
                }
            }
        })

        imvHome.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
        }
    }
}