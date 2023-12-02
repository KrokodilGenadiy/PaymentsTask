package com.example.paymentstask.data.api

import com.google.gson.internal.LinkedTreeMap

data class LoginResponse(
    val responseStatus : String,
    val response: LinkedTreeMap<String,String>
)