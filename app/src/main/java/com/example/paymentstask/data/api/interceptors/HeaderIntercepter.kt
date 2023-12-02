package com.example.paymentstask.data.api.interceptors

import com.example.paymentstask.data.api.ApiConstants.KEY
import com.example.paymentstask.data.api.ApiConstants.VERSION
import com.example.paymentstask.domain.AuthenticationManager
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(private val authenticationManager: AuthenticationManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("app-key", KEY)
                .addHeader("v", VERSION)
                .apply {
                    authenticationManager.authToken?.let { token ->
                        addHeader("token", token)
                    }
                }
                .build()
        )
    }
}