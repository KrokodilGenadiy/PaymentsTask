package com.example.paymentstask.di.modules

import android.content.Context
import com.example.paymentstask.data.api.PaymentsApi
import com.example.paymentstask.domain.AuthenticationManager
import com.example.paymentstask.domain.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    @Singleton
    fun provideApplicationContext(@ApplicationContext appContext: Context) = appContext

    @Singleton
    @Provides
    fun provideMainRepository(repository: PaymentsApi): MainRepository = MainRepository(repository)

    @Provides
    @Singleton
    fun provideAuthenticationManager(context: Context): AuthenticationManager = AuthenticationManager(context)
}