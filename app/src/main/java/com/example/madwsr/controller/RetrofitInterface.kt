package com.example.madwsr.controller

import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.example.madwsr.model.feeling.Feelings
import com.example.madwsr.model.quotes.Quotes
import com.example.madwsr.model.user.UserG
import com.example.madwsr.model.user.UserP
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST


interface RetrofitInterface {

    companion object {
        val BASE_URL = "http://mskko2021.mad.hakta.pro/"
        var apiService: RetrofitInterface? = null

        fun getInstance(): RetrofitInterface {
            if (apiService == null) {
                apiService = Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build()
                    .create(RetrofitInterface::class.java)
            }
            return apiService!!
        }

    }

    @GET("api/feelings")
    suspend fun getFeelings(): Feelings

    @GET("api/quotes")
    suspend fun getQuotes(): Quotes

    //@Headers
    @POST("api/user/login")
    suspend fun postData(@Body body: UserP): UserG
}

