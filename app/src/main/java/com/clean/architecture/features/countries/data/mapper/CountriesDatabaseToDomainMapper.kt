package com.clean.architecture.features.countries.data.mapper

import com.clean.architecture.features.common.DatabaseToDomainMapper
import com.clean.architecture.features.countries.data.datasource.local.entities.CountryEntity
import com.clean.architecture.features.countries.domain.model.CountryModel

/**
 * Created by Rehan Sarwar on 07/06/2022.
 */
class CountriesDatabaseToDomainMapper :
    DatabaseToDomainMapper<List<CountryEntity>, List<CountryModel>>() {

    override fun map(input: List<CountryEntity>) = input.map { countryEntity ->
        countryEntity.toDomain()
    }

    private fun CountryEntity.toDomain() = CountryModel(
        commonName = this.commonName,
        officialName = this.officialName,
        unMember = this.unMember,
        capitals = this.capitals,
        region = this.region,
        subregion = this.subregion,
        languages = this.languages,
        currencies = this.currencies,
        population = this.population,
        timezones = this.timezones,
        continents = this.continents,
        flagSVG = this.flagSVG,
        startOfWeek = this.startOfWeek
    )
}
