package com.example.paymentstask.view.fragment.login_fragment

import androidx.lifecycle.ViewModel
import com.example.paymentstask.data.base.ResultResponse
import com.example.paymentstask.data.api.LoginResponse
import com.example.paymentstask.data.entity.LoginInfo
import com.example.paymentstask.domain.AuthenticationManager
import com.example.paymentstask.domain.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: MainRepository, private val authenticationManager: AuthenticationManager): ViewModel() {

    suspend fun login(data: LoginInfo): ResultResponse<LoginResponse> = repository.login(data)

    fun saveToken(token: String?) {
        authenticationManager.authToken = token
    }

    companion object {
        const val TOKEN = "token"
    }
}