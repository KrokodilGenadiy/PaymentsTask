package com.example.paymentstask.data.api

import com.example.paymentstask.data.entity.LoginInfo
import com.google.gson.internal.LinkedTreeMap
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PaymentsApi {
    @POST("login")
    suspend fun login(@Body request: LoginInfo) : Response<LoginResponse?>

    @GET("payments")
    suspend fun getPayments(
    ) : Response<PaymentResponse>
}
