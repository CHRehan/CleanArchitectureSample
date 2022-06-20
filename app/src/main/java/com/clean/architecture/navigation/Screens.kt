package com.clean.architecture.navigation

/**
 * Created by Rehan Sarwar on 07/06/2022.
 **/

sealed class Screens(val route: String) {
    // Argument Keys
    companion object {
        const val COUNTRY_ID = "country_id"
    }

    object CountryList : Screens("country_list")
    object CountryDetail : Screens("country_detail")
}
