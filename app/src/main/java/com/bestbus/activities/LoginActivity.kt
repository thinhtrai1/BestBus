package com.bestbus.activities

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import com.bestbus.R
import com.bestbus.models.User
import com.bestbus.utils.Constant
import com.bestbus.utils.Util
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

//        tvSignUp.paintFlags = tvSignUp.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        tvSignUp.setOnClickListener {
            if (isLogin) {
                btnLogin.text = getString(R.string.register)
                tvSignUp.text = getString(R.string.login)
                edtName.visibility = View.VISIBLE
                isLogin = false
            } else {
                btnLogin.text = getString(R.string.login)
                tvSignUp.text = getString(R.string.register)
                edtName.visibility = View.GONE
                isLogin = true
            }
        }

        btnLogin.setOnClickListener {
            if (!isValid(isLogin)) {
                return@setOnClickListener
            }
            showLoading(true)
            if (isLogin) {
                Util.apiClient.login(edtEmail.text.toString(), edtPassword.text.toString()).enqueue(object : Callback<User> {
                    override fun onFailure(call: Call<User>, t: Throwable) {
                        showToast(t.message)
                        showLoading(false)
                    }

                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        if (response.isSuccessful) {
                            response.body()?.let {
                                Util.sharedPreferences.edit()
                                    .putString(Constant.PREF_EMAIL, it.email)
                                    .putString(Constant.PREF_PASSWORD, it.password)
                                    .putString(Constant.PREF_USER, Gson().toJson(it))
                                    .apply()
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
                Util.apiClient.signUp(edtEmail.text.toString(), edtPassword.text.toString(), edtName.text.toString()).enqueue(object : Callback<User> {
                    override fun onFailure(call: Call<User>, t: Throwable) {
                        showToast(t.message)
                        showLoading(false)
                    }

                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        if (response.isSuccessful) {
                            response.body()?.let {
                                Util.sharedPreferences.edit()
                                    .putString(Constant.PREF_EMAIL, it.email)
                                    .putString(Constant.PREF_PASSWORD, it.password)
                                    .putString(Constant.PREF_USER, Gson().toJson(it))
                                    .apply()
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

    private fun isValid(isLogin: Boolean): Boolean {
        var b = true
        if (edtEmail.text.toString().trim().isEmpty()) {
            edtEmail.error = getString(R.string.tv_please_enter_email)
            b = false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(edtEmail.text.toString()).matches()) {
            edtEmail.error = getString(R.string.tv_invalid_email)
            b = false
        }
        if (edtPassword.text.toString().trim().isEmpty()) {
            edtPassword.error = getString(R.string.tv_please_enter_password)
            b = false
        }
        if (!isLogin && edtName.text.isBlank()) {
            edtName.error = getString(R.string.tv_please_enter_your_name)
            b = false
        }
        return b
    }
}