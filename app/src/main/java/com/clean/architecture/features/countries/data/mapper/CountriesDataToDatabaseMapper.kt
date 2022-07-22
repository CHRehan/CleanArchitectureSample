package com.clean.architecture.features.countries.data.mapper

import com.clean.architecture.features.common.DataToDatabaseMapper
import com.clean.architecture.features.countries.data.datasource.local.entities.CountryEntity
import com.clean.architecture.features.countries.data.model.CountryDataModel

/**
 * Created by Rehan Sarwar on 07/06/2022.
 */
class CountriesDataToDatabaseMapper :
    DataToDatabaseMapper<List<CountryDataModel>, List<CountryEntity>>() {
    override fun map(input: List<CountryDataModel>) = input.map { countryData ->
        countryData.toDatabase()
    }

    private fun CountryDataModel.toDatabase() = CountryEntity(
        commonName = this.name?.common.orEmpty(),
        officialName = this.name?.official.orEmpty(),
        unMember = this.unMember ?: false,
        capitals = this.capital.orEmpty().joinToString(),
        region = this.region.orEmpty(),
        subregion = this.subregion.orEmpty(),
        languages = this.languages?.map { it.value }.orEmpty().joinToString(),
        currencies = this.currencies?.map { currenciesMap ->
            currenciesMap.value.name.plus(" (Symbol: ${currenciesMap.value.symbol})")
        }.orEmpty().joinToString(),
        population = this.population ?: -1,
        timezones = this.timezones.orEmpty().joinToString(),
        continents = this.continents.orEmpty().joinToString(),
        flagSVG = this.flags?.svg.orEmpty(),
        startOfWeek = this.startOfWeek.orEmpty()
    )
}
