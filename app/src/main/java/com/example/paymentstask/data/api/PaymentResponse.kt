package com.example.paymentstask.data.api

import com.example.paymentstask.data.entity.Payment
import com.google.gson.annotations.SerializedName

data class PaymentResponse(
    val success: String,
    val response: List<Payment>
)

