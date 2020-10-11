package com.bestbus.activities

import android.Manifest
import android.app.ActivityOptions
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Pair
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
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
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val user = Gson().fromJson<User?>(Util.sharedPreferences.getString(Constant.PREF_USER, null), User::class.java)
        if (user != null) {
            tvName.text = user.name
            tvEmail.text = user.email
            if (user.phone.isNullOrEmpty()) {
                tvPhone.visibility = View.GONE
            } else {
                tvPhone.text = user.phone
            }
            viewLogin.visibility = View.GONE
            if (user.isAdmin == 1) {
                viewScanTicket.visibility = View.VISIBLE
                viewBookTour.visibility = View.GONE
                viewYourTicket.visibility = View.GONE
            } else {
                viewScanTicket.visibility = View.GONE
                viewBookTour.visibility = View.VISIBLE
                viewYourTicket.visibility = View.VISIBLE
            }
        } else {
            viewScanTicket.visibility = View.GONE
            viewProfile.visibility = View.GONE
            viewUpdateProfile.visibility = View.GONE
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

        viewBookTour.setOnClickListener {
            startActivity(
                Intent(this, SearchTourActivity::class.java),
                ActivityOptions.makeSceneTransitionAnimation(
                    this,
                    Pair.create(iconBookTour, "logo")
                ).toBundle()
            )
        }

        viewLogin.setOnClickListener {
            startActivity(
                Intent(this, LoginActivity::class.java),
                ActivityOptions.makeSceneTransitionAnimation(
                    this,
                    Pair.create(iconLogin, "iconLogo")
                ).toBundle()
            )
        }

        viewYourTicket.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 0)
                    return@setOnClickListener
                }
            }
            if (File(Constant.TICKET_FOLDER).listFiles().isNullOrEmpty()) {
                showToast(getString(R.string.no_ticket_found))
            } else {
                val files = ArrayList<File>()
                for (file in File(Constant.TICKET_FOLDER).listFiles()!!) {
                    val name = file.name
                    if (name.toLowerCase(Locale.US).endsWith(".png")) {
                        try {
                            name.substring(0, 13).toLong()
                            name.substring(13, name.length - 4).toInt().toString()
                            files.add(0, file)
                        } catch (e: Exception) {
                            files.add(file)
                        }
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

        viewLogout.setOnClickListener {
            viewProfile.visibility = View.GONE
            viewLogin.visibility = View.VISIBLE
            viewBookTour.visibility = View.VISIBLE
            viewYourTicket.visibility = View.VISIBLE
            viewUpdateProfile.visibility = View.GONE
            viewScanTicket.visibility = View.GONE
            viewLogout.visibility = View.GONE
            Util.sharedPreferences.edit().clear().apply()
        }

        viewScanTicket.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(arrayOf(Manifest.permission.CAMERA), 1)
                }
            } else {
                startActivity(Intent(this, ScanTicketActivity::class.java))
            }
            viewContainer.closeDrawer(viewMenuLeft)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (requestCode == 0) {
                viewYourTicket.performClick()
            } else if (requestCode == 1) {
                startActivity(Intent(this, ScanTicketActivity::class.java))
            }
        }
    }
}