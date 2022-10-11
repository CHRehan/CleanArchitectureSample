package com.clean.architecture

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavHostController
import com.clean.architecture.features.countries.ui.countrylist.CountryListScreen
import com.clean.architecture.features.countries.ui.theme.CleanArchitectureSampleTheme
import com.clean.architecture.mockserver.ErrorDispatcher
import com.clean.architecture.mockserver.SuccessDispatcher
import dagger.hilt.android.testing.HiltAndroidRule
import kotlinx.coroutines.InternalCoroutinesApi
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject

/**
 * Created by Rehan Sarwar on 24/07/2022.
 */
open class BaseUiTest {

    @Inject
    lateinit var successDispatcher: SuccessDispatcher

    @Inject
    lateinit var errorDispatcher: ErrorDispatcher

    @get:Rule
    val hiltRule by lazy { HiltAndroidRule(this) }

    @ExperimentalMaterialApi
    @get:Rule
    val composeTestRule by lazy { createAndroidComposeRule<MainActivity>() }

    val mockWebServer by lazy { MockWebServer() }

    @Before
    fun setUp() {
        hiltRule.inject()
        mockWebServer.start(8080)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @ExperimentalMaterialApi
    @InternalCoroutinesApi
    fun setMainContent() {
        composeTestRule.setContent {
            CleanArchitectureSampleTheme {
                val context = LocalContext.current
                val navController = remember { NavHostController(context) }
                CountryListScreen(navController = navController)
            }
        }
    }
}
