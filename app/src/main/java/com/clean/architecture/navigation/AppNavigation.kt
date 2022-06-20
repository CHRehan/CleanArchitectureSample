package com.clean.architecture.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.clean.architecture.features.countries.domain.model.CountryModel
import com.clean.architecture.features.countries.ui.countrydetail.CountryDetailScreen
import com.clean.architecture.features.countries.ui.countrylist.CountryListScreen
import com.clean.architecture.navigation.Screens.Companion.COUNTRY_ID

/**
 * Created by Rehan Sarwar on 07/06/2022.
 **/

@ExperimentalMaterialApi
@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.CountryList.route) {
        // e.g will add auth routes here if when we will extend project
        composable(Screens.CountryList.route) {
            CountryListScreen(navController)
        }
        composable(
            Screens.CountryDetail.route
        ) {
            val countryDetail =
                navController.previousBackStackEntry?.savedStateHandle?.get<CountryModel>(
                    COUNTRY_ID
                )
            CountryDetailScreen(countryDetail, navController)
        }
    }
}
