package com.example.paymentstask.view.fragment.payments_fragment

import androidx.lifecycle.ViewModel
import com.example.paymentstask.domain.AuthenticationManager
import com.example.paymentstask.domain.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PaymentsViewModel @Inject constructor(private val repository: MainRepository,private val authenticationManager: AuthenticationManager): ViewModel() {
    fun removeToken() {
        authenticationManager.clearAuthToken()
    }

    fun getPayments() = repository.getPaymentsFromWeb()
}