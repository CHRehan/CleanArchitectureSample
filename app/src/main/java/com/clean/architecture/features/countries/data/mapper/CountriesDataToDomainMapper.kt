package com.clean.architecture.features.countries.data.mapper

import com.clean.architecture.features.common.DataToDomainMapper
import com.clean.architecture.features.countries.data.model.CountryDataModel
import com.clean.architecture.features.countries.domain.model.CountryModel

/**
 * Created by Rehan Sarwar on 07/06/2022.
 */
class CountriesDataToDomainMapper :
    DataToDomainMapper<Collection<CountryDataModel>, Collection<CountryModel>>() {
    override fun map(input: Collection<CountryDataModel>) = input.map { countryDataModel ->
        countryDataModel.toDomain()
    }

    private fun CountryDataModel.toDomain() = CountryModel(
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
