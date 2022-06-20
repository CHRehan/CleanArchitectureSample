package com.clean.architecture.di

import com.clean.architecture.features.countries.data.mapper.CountriesDataToDatabaseMapper
import com.clean.architecture.features.countries.data.mapper.CountriesDataToDomainMapper
import com.clean.architecture.features.countries.data.mapper.CountriesDatabaseToDomainMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Rehan Sarwar on 11/06/2022.
 */
@Module
@InstallIn(SingletonComponent::class)
class MappersModule {
    @Singleton
    @Provides
    fun provideCountriesDataToDomainMapper(): CountriesDataToDomainMapper =
        CountriesDataToDomainMapper()

    @Singleton
    @Provides
    fun provideCountriesDatabaseToDomainMapper(): CountriesDatabaseToDomainMapper =
        CountriesDatabaseToDomainMapper()

    @Singleton
    @Provides
    fun provideCountriesDataToDatabaseMapper(): CountriesDataToDatabaseMapper =
        CountriesDataToDatabaseMapper()
}
