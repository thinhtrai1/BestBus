package com.bestbus.activities

import android.app.ActivityOptions
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Pair
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.bestbus.R
import com.bestbus.adapters.BestDealAdapter
import com.bestbus.adapters.MyTicketAdapter
import com.bestbus.adapters.OfferAdapter
import com.bestbus.models.Deal
import com.bestbus.models.Offer
import com.bestbus.models.User
import com.bestbus.utils.Constant
import com.bestbus.utils.Util
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val user = Gson().fromJson<User?>(Util.sharedPreferences.getString(Constant.PREF_USER, null), User::class.java)
        if (user != null) {
            tvEmail.text = user.email
            if (user.name.isNullOrEmpty()) {
                tvName.visibility = View.GONE
            } else {
                tvName.text = user.name
            }
            if (user.phone.isNullOrEmpty()) {
                tvPhone.visibility = View.GONE
            } else {
                tvPhone.text = user.phone
            }
            viewLogin.visibility = View.GONE
        } else {
            viewProfile.visibility = View.GONE
            viewUpdateProfile.visibility = View.GONE
            viewChangePassword.visibility = View.GONE
            viewLogout.visibility = View.GONE
        }

        Util.apiClient.getDeal().enqueue(object : Callback<ArrayList<Deal>> {
            override fun onFailure(call: Call<ArrayList<Deal>>, t: Throwable) {
                showToast(t.message)
                progressBestDeal.visibility = View.GONE
            }

            override fun onResponse(call: Call<ArrayList<Deal>>, response: Response<ArrayList<Deal>>) {
                progressBestDeal.visibility = View.GONE
                if (response.isSuccessful) {
                    response.body()?.let {
                        rcvBestDeals.visibility = View.VISIBLE
                        rcvBestDeals.adapter = BestDealAdapter(this@HomeActivity, it)
                        rcvBestDeals.layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
                        LinearSnapHelper().attachToRecyclerView(rcvBestDeals)
                    }
                } else {
                    showToast(response.errorBody()?.string())
                }
            }
        })

        Util.apiClient.getOffer().enqueue(object : Callback<ArrayList<Offer>> {
            override fun onFailure(call: Call<ArrayList<Offer>>, t: Throwable) {
                showToast(t.message)
                progressOffer.visibility = View.GONE
            }

            override fun onResponse(call: Call<ArrayList<Offer>>, response: Response<ArrayList<Offer>>) {
                progressOffer.visibility = View.GONE
                if (response.isSuccessful) {
                    response.body()?.let {
                        rcvOffers.visibility = View.VISIBLE
                        rcvOffers.adapter = OfferAdapter(this@HomeActivity, it)
                        rcvOffers.layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
                    }
                } else {
                    showToast(response.errorBody()?.string())
                }
            }
        })

        listener()
    }

    override fun onResume() {
        super.onResume()
        viewAnimate1.clearAnimation()
        viewAnimate2.clearAnimation()
        viewAnimate3.clearAnimation()
        viewAnimate1.startAnimation(AnimationUtils.loadAnimation(this, R.anim.home_logo_zoom_in))
        Handler(Looper.getMainLooper()).postDelayed({
            viewAnimate2.startAnimation(AnimationUtils.loadAnimation(this, R.anim.home_logo_zoom_in))
            Handler(Looper.getMainLooper()).postDelayed({
                viewAnimate3.startAnimation(AnimationUtils.loadAnimation(this, R.anim.home_logo_zoom_in))
            }, 1000)
        }, 1000)
    }

    private fun listener() {
        imvLogo.setOnClickListener {
            startActivity(
                Intent(this, SearchTourActivity::class.java),
                ActivityOptions.makeSceneTransitionAnimation(
                    this,
                    Pair.create(imvLogo, "logo")
                ).toBundle()
            )
        }

        imvMenu.setOnClickListener {
            viewContainer.openDrawer(viewMenuLeft)
        }

        viewLogin.setOnClickListener {
            startActivity(
                Intent(this, LoginActivity::class.java),
                ActivityOptions.makeSceneTransitionAnimation(
                    this,
                    Pair.create(imvIcon, "iconLogo")
                ).toBundle()
            )
        }

        viewYourTicket.setOnClickListener {
            if (File(Constant.TICKET_FOLDER).listFiles().isNullOrEmpty()) {
                showToast(getString(R.string.no_ticket_found))
            } else {
                val files = ArrayList<File>()
                for (file in File(Constant.TICKET_FOLDER).listFiles()!!.sortedDescending()) {
                    if (file.name.toLowerCase(Locale.US).endsWith(".png")) {
                        files.add(file)
                    }
                }
                Dialog(this).apply {
                    val recyclerView = RecyclerView(this@HomeActivity)
                    recyclerView.adapter = MyTicketAdapter(this@HomeActivity, files)
                    recyclerView.layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.VERTICAL, false)
                    setContentView(recyclerView)
                    window?.attributes?.width = WindowManager.LayoutParams.MATCH_PARENT
                    window?.attributes?.height = WindowManager.LayoutParams.MATCH_PARENT
                    show()
                }
            }
        }

        viewUpdateProfile.setOnClickListener {
        }

        viewChangePassword.setOnClickListener {
        }

        viewLogout.setOnClickListener {
            viewProfile.visibility = View.GONE
            viewLogin.visibility = View.VISIBLE
            viewUpdateProfile.visibility = View.GONE
            viewChangePassword.visibility = View.GONE
            viewLogout.visibility = View.GONE
            Util.sharedPreferences.edit().clear().apply()
        }
    }
}