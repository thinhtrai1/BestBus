package com.bestbus.utils

import com.bestbus.models.*
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @FormUrlEncoded
    @POST("login.php")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String): Call<User>

    @FormUrlEncoded
    @POST("signUp.php")
    fun signUp(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("name") name: String): Call<User>

    @GET("getTour.php")
    fun getTour(@Query("from") from: String,
                @Query("to") to: String): Call<ArrayList<Tour>>

    @FormUrlEncoded
    @POST("bookTour.php")
    fun booking(
        @Field("userId") userId: Int?,
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("phone") phone: String,
        @Field("tourId") tourId: Int,
        @Field("date") date: String?,
        @Field("seatList") seatSelected: String,
        @Field("paymentMethod") paymentMethod: String,
        @Field("paymentInformation") paymentInformation: String,
        @Field("totalAmount") totalAmount: Float): Call<Ticket>

    @GET("getDeal.php")
    fun getDeal(): Call<ArrayList<Deal>>

    @GET("getOffer.php")
    fun getOffer(): Call<ArrayList<Offer>>

    @GET("scanTicket.php")
    fun scanTicket(@Query("qrCode") qrCode: String): Call<Ticket>
}