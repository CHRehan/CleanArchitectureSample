package com.clean.architecture.features.countries.ui.countrylist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.clean.architecture.R
import com.clean.architecture.features.countries.presentation.CountriesViewModel
import com.clean.architecture.features.countries.ui.component.TryAgainWidget
import com.clean.architecture.navigation.Screens
import com.clean.architecture.navigation.Screens.Companion.COUNTRY_ID

/**
 * Created by Rehan Sarwar on 11/06/2022.
 */
@ExperimentalMaterialApi
@Composable
fun CountryListScreen(
    navController: NavController,
    viewModel: CountriesViewModel = hiltViewModel()
) {
    val state = viewModel.viewState.value

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name)
                    )
                },
                backgroundColor = MaterialTheme.colors.primary
            )
        }
    ) {
        CountryList(list = state.countries.toList()) { country ->
            navController.currentBackStackEntry?.savedStateHandle?.set(COUNTRY_ID, country)
            navController.navigate(Screens.CountryDetail.route)
        }

        if (state.isDataLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        if (state.errorMessage.isNotBlank()) {
            TryAgainWidget(state.errorMessage) {
                viewModel.getCountries()
            }
        }
    }
}
