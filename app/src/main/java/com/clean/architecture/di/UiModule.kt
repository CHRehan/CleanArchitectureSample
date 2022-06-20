package com.clean.architecture.di

import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.text.NumberFormat

/**
 * Created by Rehan Sarwar on 12/06/2022.
 */
@Module
@InstallIn(SingletonComponent::class)
class UiModule {
    @Provides
    @Reusable
    fun providesNumberFormat(): NumberFormat = NumberFormat.getNumberInstance()
}
