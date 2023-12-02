package com.example.paymentstask.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.paymentstask.R
import com.example.paymentstask.databinding.ActivityMainBinding
import com.example.paymentstask.view.fragment.login_fragment.LoginFragment
import com.example.paymentstask.view.fragment.payments_fragment.PaymentFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        openLogin()
    }

    fun openLogin() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, LoginFragment())
            .commit()
    }

    fun openPayments() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, PaymentFragment())
            .commit()
    }


}