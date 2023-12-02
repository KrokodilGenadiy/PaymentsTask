package com.example.paymentstask.di

import android.content.Context
import com.example.paymentstask.di.modules.DomainModule
import com.example.paymentstask.di.modules.RemoteModule
import com.example.paymentstask.view.fragment.login_fragment.LoginViewModel
import com.example.paymentstask.view.fragment.payments_fragment.PaymentsViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DomainModule::class,RemoteModule::class])
interface AppComponent {
    fun inject(viewModel: LoginViewModel)
    fun inject(viewModel: PaymentsViewModel)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun build(): AppComponent
    }
}