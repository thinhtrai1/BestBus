package com.bestbus.activities

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bestbus.R
import com.bestbus.adapters.TourListAdapter
import com.bestbus.models.Tour
import com.bestbus.utils.Constant
import com.bestbus.utils.IOnItemClickListener
import com.bestbus.utils.SharedPreferenceHelper
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_tour_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

class TourListActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tour_list)

        showLoading(true)
        Constant.client.getTour().enqueue(object : Callback<ArrayList<Tour>> {
            override fun onFailure(call: Call<ArrayList<Tour>>, t: Throwable) {
                showToast(t.message)
                showLoading(false)
            }

            override fun onResponse(call: Call<ArrayList<Tour>>, response: Response<ArrayList<Tour>>) {
                if (response.isSuccessful) {
                    rcvTourList.layoutManager = LinearLayoutManager(this@TourListActivity, LinearLayoutManager.VERTICAL, false)
                    rcvTourList.adapter = TourListAdapter(this@TourListActivity, response.body()!!, object : IOnItemClickListener {
                        override fun onClick(position: Int) {
                            if (SharedPreferenceHelper.instance.getString(Constant.PREF_USER) != null) {
                                startActivity(
                                    Intent(this@TourListActivity, SelectSeatActivity::class.java)
                                        .putExtra("tour", Gson().toJson(response.body()!![position]))
                                )
                            }
                        }
                    })
                } else {
                    showToast(response.errorBody()?.string())
                }
                showLoading(false)
            }
        })
    }
}