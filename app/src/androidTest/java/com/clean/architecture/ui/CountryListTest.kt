package com.clean.architecture.ui

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onLast
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.clean.architecture.MainActivity
import com.clean.architecture.R
import com.clean.architecture.features.countries.domain.model.CountryModel
import com.clean.architecture.features.countries.ui.countrylist.CountryList
import com.clean.architecture.features.countries.ui.theme.CleanArchitectureSampleTheme
import com.clean.architecture.utils.TestTags.COUNTRY_LIST
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Rehan Sarwar on 16/06/2022.
 */
@RunWith(AndroidJUnit4::class)
class CountryListTest {

    private val country1 = CountryModel(
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

    private val country2 = CountryModel(
        commonName = "Estonia",
        officialName = "Republic of Estonia",
        unMember = true,
        capitals = "Islamabad",
        region = "Europe",
        subregion = "Northern Europe",
        languages = "Estonian",
        currencies = "Euro (Symbol:€)",
        population = 1331057L,
        timezones = "UTC+02:00",
        continents = "Europe",
        flagSVG = "https://flagcdn.com/ee.svg",
        startOfWeek = "monday"
    )

    private val country3 = CountryModel(
        commonName = "Sweden",
        officialName = "Kingdom of Sweden",
        unMember = true,
        capitals = "Islamabad",
        region = "Europe",
        subregion = "Northern Europe",
        languages = "Swedish",
        currencies = "Swedish krona (Symbol:kr)",
        population = 10353442L,
        timezones = "UTC+01:00",
        continents = "Europe",
        flagSVG = "https://flagcdn.com/se.svg",
        startOfWeek = "monday"
    )

    private val country4 = CountryModel(
        commonName = "Pakistan",
        officialName = "Islamic Republic of Pakistan",
        unMember = true,
        capitals = "Islamabad",
        region = "Asia",
        subregion = "Southern Asia",
        languages = "English,Urdu",
        currencies = "Pakistani rupee (Symbol:₨)",
        population = 220892331L,
        timezones = "UTC+05:00",
        continents = "Asia",
        flagSVG = "https://flagcdn.com/pk.svg",
        startOfWeek = "monday"
    )

    private val countries = listOf(country1, country2, country3, country4)

    @ExperimentalMaterialApi
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @ExperimentalMaterialApi
    @Test
    fun testCountryListScreenIfCountriesAreEmpty() {
        composeTestRule.setContent {
            CleanArchitectureSampleTheme {
                CountryList(emptyList()) {}
            }
        }
        composeTestRule.onNodeWithTag(COUNTRY_LIST)
            .onChildren()
            .assertCountEquals(0)
    }

    @ExperimentalMaterialApi
    @Test
    fun testTaskListScreenIfCountriesAreNotEmpty() {
        composeTestRule.setContent {
            CleanArchitectureSampleTheme {
                CountryList(countries) {}
            }
        }
        composeTestRule.onNodeWithTag(COUNTRY_LIST)
            .onChildren()
            .assertCountEquals(4)
    }

    @ExperimentalMaterialApi
    @Test
    fun testFirstAndLastCountryItemDescription() {
        composeTestRule.setContent {
            CleanArchitectureSampleTheme {
                CountryList(countries) {}
            }
        }

        composeTestRule.apply {
            onNodeWithTag(COUNTRY_LIST)
                .onChildren()
                .onFirst()
                .assert(hasText(countries[0].commonName))

            onNodeWithTag(COUNTRY_LIST)
                .onChildren()
                .onLast()
                .assert(
                    hasText(
                        composeTestRule.activity.getString(
                            R.string.capital_name,
                            countries[countries.size - 1].capitals
                        )
                    )
                )
        }
    }
}
