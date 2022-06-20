package com.clean.architecture.di

import android.content.Context
import com.clean.architecture.features.countries.domain.datasource.db.CountryDao
import com.clean.architecture.storage.CountryDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

/**
 * Created by Rehan Sarwar on 12/06/2022.
 */
@Module
@InstallIn(SingletonComponent::class)
class DbModule {
    @Provides
    fun providesCountryDb(@ApplicationContext context: Context) = CountryDb.create(context)

    @Provides
    fun providesCountryDao(countryDb: CountryDb): CountryDao = countryDb.CountryDao()
}
