package com.clean.architecture.di

import com.clean.architecture.features.countries.data.datasource.remote.CountryAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApisModule {

    @Singleton
    @Provides
    fun provideCountriesApi(retrofit: Retrofit): CountryAPI =
        retrofit.create(CountryAPI::class.java)
}
