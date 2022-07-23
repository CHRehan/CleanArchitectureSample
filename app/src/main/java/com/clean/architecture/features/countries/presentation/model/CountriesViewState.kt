package com.clean.architecture.features.countries.presentation.model

import com.clean.architecture.features.countries.domain.model.CountryModel
import com.clean.architecture.features.countries.presentation.ViewState

/**
 * Created by Rehan Sarwar on 07/06/2022.
 */
data class CountriesViewState(
    val isDataLoading: Boolean,
    val countries: Collection<CountryModel>,
    val errorMessage: String
) : ViewState

fun emptyViewState() = CountriesViewState(
    isDataLoading = false,
    countries = emptyList(),
    errorMessage = ""
)
