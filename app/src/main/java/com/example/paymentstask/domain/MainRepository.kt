package com.example.paymentstask.domain

import com.example.paymentstask.data.base.ResultResponse
import com.example.paymentstask.data.api.PaymentsApi
import com.example.paymentstask.data.api.LoginResponse
import com.example.paymentstask.data.api.PaymentResponse
import com.example.paymentstask.data.entity.LoginInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainRepository (private val retrofitService: PaymentsApi) {

    suspend fun login(data: LoginInfo): ResultResponse<LoginResponse> {
        return try {
            val response: Response<LoginResponse?> = retrofitService.login(data)
            if (response.isSuccessful && response.body()?.response != null) {
                ResultResponse.Success(response.body()!!)
            } else {
                ResultResponse.Error(response.message())
            }
        } catch (e: Exception) {
            ResultResponse.Error(e.message ?: "An error occurred")
        }
    }

    fun getPaymentsFromWeb(): Flow<ResultResponse<PaymentResponse>> = flow {
        emit(ResultResponse.Loading)
        emit(getPayments())
    }.flowOn(Dispatchers.IO)

    private suspend fun getPayments(): ResultResponse<PaymentResponse> {
        return try {
            val response: Response<PaymentResponse> = retrofitService.getPayments()
            if (response.isSuccessful && response.body()?.response != null) {
                ResultResponse.Success(response.body()!!)
            } else {
                ResultResponse.Error(response.message())
            }
        } catch (e: Exception) {
            ResultResponse.Error(e.message ?: "An error occurred")
        }
    }


}