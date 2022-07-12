package com.clean.architecture.ui

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.clean.architecture.MainActivity
import com.clean.architecture.R
import com.clean.architecture.features.countries.domain.model.CountryModel
import com.clean.architecture.features.countries.ui.countrylist.ItemCountryList
import com.clean.architecture.features.countries.ui.theme.CleanArchitectureSampleTheme
import com.clean.architecture.utils.TestTags.ITEM_COUNTRY_LIST
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Rehan Sarwar on 12/07/2022.
 */
@RunWith(AndroidJUnit4::class)
class ItemCountryListTest {
    private val country = CountryModel(
        commonName = "Australia",
        officialName = "Commonwealth of Australia",
        unMember = true,
        capitals = "Canberra",
        region = "Oceania",
        subregion = "Australia and New Zealand",
        languages = "English",
        currencies = "Australian dollar (Symbol:$)",
        population = 25687041,
        timezones = "UTC+05:00, UTC+06:30, UTC+07:00, UTC+08:00, UTC+09:30, UTC+10:00, UTC+10:30, UTC+11:30",
        continents = "Asia",
        flagSVG = "https://flagcdn.com/au.svg",
        startOfWeek = "monday"
    )

    @ExperimentalMaterialApi
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @ExperimentalMaterialApi
    @Test
    fun testCountryListItem() {
        composeTestRule.setContent {
            CleanArchitectureSampleTheme {
                ItemCountryList(country = country) {}
            }
        }
        composeTestRule.apply {
            onNodeWithTag(ITEM_COUNTRY_LIST)
                .assert(hasClickAction())
                .assert(hasText(country.commonName))
                .assert(
                    hasText(
                        composeTestRule.activity.getString(
                            R.string.capital_name,
                            country.capitals
                        )
                    )
                )
                .performClick()
        }
    }
}
