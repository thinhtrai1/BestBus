package com.bestbus.utils

import com.bestbus.models.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
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

    @Multipart
    @POST("updateProfile.php")
    fun updateProfile(
        @Part("userId") userId: RequestBody,
        @Part("name") address: RequestBody,
        @Part("email") contact_no: RequestBody,
        @Part("phone") password: RequestBody,
        @Part("password") latitude: RequestBody,
        @Part image: MultipartBody.Part?
    ): Call<User>

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

    @FormUrlEncoded
    @POST("addTour.php")
    fun addTour(
        @Field("tourName") tourName: String,
        @Field("oldPrice") oldPrice: String,
        @Field("price") price: String,
        @Field("startTime") startTime: String,
        @Field("time") time: String,
        @Field("fromCity") fromCity: String?,
        @Field("toCity") toCity: String,
        @Field("seatQuantity") seatQuantity: String,
        @Field("count") count: String,
        @Field("vat") vat: String): Call<String>
}