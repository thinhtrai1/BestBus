package com.bestbus.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.bestbus.R
import com.bestbus.models.User
import com.bestbus.utils.Constant
import com.bestbus.utils.SharedPreferenceHelper
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : BaseActivity() {
    private var isLogin = true
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        
        tvSignUp.setOnClickListener {
            if (isLogin) {
                btnLogin.text = "Sign Up"
                tvSignUp.text = getString(R.string.login)
                edtName.visibility = View.VISIBLE
                isLogin = false
            } else {
                btnLogin.text = "Login"
                tvSignUp.text = getString(R.string.register)
                edtName.visibility = View.GONE
                isLogin = true
            }
        }
        
        btnLogin.setOnClickListener {
            showLoading(true)
            if (isLogin) {
                Constant.client.login(edtEmail.text.toString(), edtPassword.text.toString()).enqueue(object : Callback<User> {
                    override fun onFailure(call: Call<User>, t: Throwable) {
                        showToast(t.message)
                        showLoading(false)
                    }

                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        if (response.isSuccessful) {
                            response.body()?.let {
                                SharedPreferenceHelper.instance
                                    .putString(Constant.PREF_EMAIL, it.email)
                                    .putString(Constant.PREF_PASSWORD, it.password)
                                    .putString(Constant.PREF_USER, Gson().toJson(it))
                                startActivity(Intent(this@LoginActivity, HomeActivity::class.java)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
                            }
                        } else {
                            showToast(response.errorBody()?.string())
                        }
                        showLoading(false)
                    }
                })
            } else {
                Constant.client.signUp(edtEmail.text.toString(), edtPassword.text.toString(), edtName.text.toString()).enqueue(object : Callback<User> {
                    override fun onFailure(call: Call<User>, t: Throwable) {
                        showToast(t.message)
                        showLoading(false)
                    }

                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        if (response.isSuccessful) {
                            response.body()?.let {
                                SharedPreferenceHelper.instance
                                    .putString(Constant.PREF_EMAIL, it.email)
                                    .putString(Constant.PREF_PASSWORD, it.password)
                                    .putString(Constant.PREF_USER, Gson().toJson(it))
                                startActivity(Intent(this@LoginActivity, HomeActivity::class.java)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
                            }
                        } else {
                            showToast(response.errorBody()?.string())
                        }
                        showLoading(false)
                    }
                })
            }
        }
    }
}