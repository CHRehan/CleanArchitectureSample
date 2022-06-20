package com.clean.architecture.features.countries.data.model

data class CountryDataModel(
    val name: Name?,
    val unMember: Boolean?,
    val capital: List<String>?,
    val region: String?,
    val subregion: String?,
    val languages: Map<String, String>?,
    val currencies: Map<String, CurrencyDetail>?,
    val population: Long?,
    val timezones: List<String>?,
    val continents: List<String>?,
    val flags: Flags?,
    val startOfWeek: String?,
)
