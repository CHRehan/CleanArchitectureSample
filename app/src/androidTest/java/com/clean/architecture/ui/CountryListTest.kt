package com.clean.architecture.ui

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.clean.architecture.MainActivity
import com.clean.architecture.features.countries.ui.countrylist.CountryList
import com.clean.architecture.features.countries.ui.theme.CleanArchitectureSampleTheme
import com.clean.architecture.utils.TestTags.COUNTRY_LIST
import org.junit.Rule
import org.junit.runner.RunWith

/**
 * Created by Rehan Sarwar on 16/06/2022.
 */
@RunWith(AndroidJUnit4::class)
class CountryListTest {
    @ExperimentalMaterialApi
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @ExperimentalMaterialApi
    fun `Given empty list Then Empty List`() {
        composeTestRule.setContent {
            CleanArchitectureSampleTheme {
                CountryList(emptyList()) {}
            }
        }
        composeTestRule.onNodeWithTag(COUNTRY_LIST)
            .onChildren()
            .assertCountEquals(0)
    }
}
