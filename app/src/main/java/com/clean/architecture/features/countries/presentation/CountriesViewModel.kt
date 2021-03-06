package com.clean.architecture.features.countries.presentation

import androidx.lifecycle.viewModelScope
import com.clean.architecture.features.common.Resource
import com.clean.architecture.features.countries.domain.usecase.CountriesUseCase
import com.clean.architecture.features.countries.presentation.model.CountriesViewState
import com.clean.architecture.features.countries.presentation.model.emptyViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Rehan Sarwar on 07/06/2022.
 */
@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val countriesUseCase: CountriesUseCase
) : BaseViewModel<CountriesViewState>() {

    override fun initialState() = emptyViewState()

    init {
        getCountries()
    }

    fun getCountries() {
        updateState { currentViewState -> currentViewState.copy(isDataLoading = true) }
        viewModelScope.launch {
            countriesUseCase().collect { countriesApiResource ->
                when (countriesApiResource) {
                    is Resource.Success -> {
                        updateState { currentViewState ->
                            currentViewState.copy(
                                isDataLoading = false,
                                countries = countriesApiResource.data ?: emptyList()
                            )
                        }
                    }
                    is Resource.Error -> {
                        updateState { currentViewState ->
                            currentViewState.copy(
                                isDataLoading = false,
                                errorMessage = countriesApiResource.message
                                    ?: "Something went wrong"
                            )
                        }
                    }
                }
            }
        }
    }
}
