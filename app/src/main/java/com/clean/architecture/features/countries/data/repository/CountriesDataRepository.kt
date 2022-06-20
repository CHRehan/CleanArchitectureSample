package com.clean.architecture.features.countries.data.repository

import com.clean.architecture.features.countries.data.datasource.remote.CountryAPI
import com.clean.architecture.features.countries.data.mapper.CountriesDataToDatabaseMapper
import com.clean.architecture.features.countries.data.mapper.CountriesDataToDomainMapper
import com.clean.architecture.features.countries.data.mapper.CountriesDatabaseToDomainMapper
import com.clean.architecture.features.countries.domain.datasource.db.CountryDao
import com.clean.architecture.features.countries.domain.model.CountryModel
import com.clean.architecture.features.countries.domain.repository.CountriesRepository
import javax.inject.Inject

/**
 * Created by Rehan Sarwar on 07/06/2022.
 */
class CountriesDataRepository @Inject constructor(
    private val countryAPI: CountryAPI,
    private val countryDao: CountryDao,
    private val countriesDataToDomainMapper: CountriesDataToDomainMapper,
    private val countriesDataToDatabaseMapper: CountriesDataToDatabaseMapper,
    private val countriesDatabaseToDomainMapper: CountriesDatabaseToDomainMapper
) : CountriesRepository {
    override suspend fun getAllCountries(): List<CountryModel> {
        val localData = countryDao.getCountries()
        return if (localData.isNullOrEmpty()) {
            val countiesApiData = countryAPI.getAllCountries()
            countryDao.insertAll(countriesDataToDatabaseMapper.toDatabase(countiesApiData))
            countriesDataToDomainMapper.toDomain(countiesApiData)
        } else {
            countriesDatabaseToDomainMapper.toDomain(localData)
        }
    }
}
