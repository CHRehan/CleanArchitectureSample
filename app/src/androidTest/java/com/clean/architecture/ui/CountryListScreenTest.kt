package com.clean.architecture.ui

import androidx.compose.material.ExperimentalMaterialApi
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.clean.architecture.BaseUiTest
import com.clean.architecture.countriesScreenRobot
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.InternalCoroutinesApi
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalMaterialApi
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class CountryListScreenTest :BaseUiTest() {

    @Test
    @InternalCoroutinesApi
    fun visibleItemsCountAfterOpeningTheScreen() {
        mockWebServer.dispatcher = successDispatcher
        setMainContent()

        countriesScreenRobot(composeTestRule) {
            advanceTimeBy(2000)
            listItemsShowed(6)
        }
    }

}