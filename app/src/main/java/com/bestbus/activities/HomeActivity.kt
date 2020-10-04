package com.bestbus.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.bestbus.R
import com.bestbus.adapters.BestDealAdapter
import com.bestbus.adapters.OfferAdapter
import com.bestbus.models.Deal
import com.bestbus.models.Offer
import com.bestbus.models.User
import com.bestbus.utils.Constant
import com.bestbus.utils.SharedPreferenceHelper
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : BaseActivity() {
    private lateinit var mAnimation1: Animation
    private lateinit var mAnimation2: Animation
    private lateinit var mAnimation3: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        mAnimation1 = AnimationUtils.loadAnimation(this, R.anim.home_logo_zoom_in)
        mAnimation2 = AnimationUtils.loadAnimation(this, R.anim.home_logo_zoom_in)
        mAnimation3 = AnimationUtils.loadAnimation(this, R.anim.home_logo_zoom_in)
        startLogoAnimation()

        val user = Gson().fromJson<User?>(SharedPreferenceHelper.instance.getString(Constant.PREF_USER), User::class.java)
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

        Constant.client.getDeal().enqueue(object : Callback<ArrayList<Deal>> {
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

        Constant.client.getOffer().enqueue(object : Callback<ArrayList<Offer>> {
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

    private fun startLogoAnimation() {
        viewAnimate1.startAnimation(mAnimation1)
        Handler(Looper.getMainLooper()).postDelayed( {
            viewAnimate2.startAnimation(mAnimation2)
            Handler(Looper.getMainLooper()).postDelayed( {
                viewAnimate3.startAnimation(mAnimation3)
            }, 1000)
        }, 1000)
    }

    override fun onResume() {
        super.onResume()
        viewAnimate1.clearAnimation()
        viewAnimate2.clearAnimation()
        viewAnimate3.clearAnimation()
        startLogoAnimation()
    }

    private fun listener() {
        imvLogo.setOnClickListener {
            startActivity(Intent(this, TourListActivity::class.java))
        }

        imvMenu.setOnClickListener {
            viewContainer.openDrawer(viewMenuLeft)
        }

        viewLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
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
            SharedPreferenceHelper.instance.clearAll()
        }
    }
}