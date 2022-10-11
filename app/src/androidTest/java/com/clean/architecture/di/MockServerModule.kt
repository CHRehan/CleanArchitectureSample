package com.clean.architecture.di

import android.content.Context
import com.clean.architecture.mockserver.ErrorDispatcher
import com.clean.architecture.mockserver.SuccessDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MockServerModule {

    @Provides
    @Singleton
    fun providesSuccessDispatcher() = SuccessDispatcher()

    @Provides
    @Singleton
    fun providesErrorDispatcher() = ErrorDispatcher()
}
