package com.clean.architecture.features.countries.domain.repository

import com.clean.architecture.features.countries.domain.model.CountryModel

/**
 * Created by Rehan Sarwar on 07/06/2022.
 */
interface CountriesRepository {
    suspend fun getAllCountries(): List<CountryModel>
}
