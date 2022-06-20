package com.clean.architecture.di

import com.clean.architecture.features.countries.data.datasource.remote.CountryAPI
import com.clean.architecture.features.countries.data.mapper.CountriesDataToDatabaseMapper
import com.clean.architecture.features.countries.data.mapper.CountriesDataToDomainMapper
import com.clean.architecture.features.countries.data.mapper.CountriesDatabaseToDomainMapper
import com.clean.architecture.features.countries.data.repository.CountriesDataRepository
import com.clean.architecture.features.countries.domain.datasource.db.CountryDao
import com.clean.architecture.features.countries.domain.repository.CountriesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Rehan Sarwar on 07/06/2022.
 **/

@Module
@InstallIn(SingletonComponent::class)
class RepositoriesModule {

    @Singleton
    @Provides
    fun provideCountriesDataRepository(
        countryAPI: CountryAPI,
        countryDao: CountryDao,
        countriesDataToDomainMapper: CountriesDataToDomainMapper,
        countriesDataToDatabaseMapper: CountriesDataToDatabaseMapper,
        countriesDatabaseToDomainMapper: CountriesDatabaseToDomainMapper
    ): CountriesRepository {
        return CountriesDataRepository(
            countryAPI,
            countryDao,
            countriesDataToDomainMapper,
            countriesDataToDatabaseMapper,
            countriesDatabaseToDomainMapper
        )
    }
}
