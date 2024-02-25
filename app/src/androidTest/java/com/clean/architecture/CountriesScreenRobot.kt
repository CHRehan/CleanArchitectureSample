package com.clean.architecture

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.test.ext.junit.rules.ActivityScenarioRule


@ExperimentalMaterialApi
internal fun countriesScreenRobot(
    composeTestRule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>,
    func: CountriesScreenRobot.() -> Unit
) = CountriesScreenRobot(composeTestRule).also { func }

internal open class CountriesScreenRobot @ExperimentalMaterialApi constructor(
    private val composeTestRule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>
) {

    @OptIn(ExperimentalMaterialApi::class)
    private val launchesListItems by lazy {
        composeTestRule.onAllNodesWithContentDescription(
            "ItemCountry",
            substring = true,
            useUnmergedTree = true
        )
    }

    fun listItemsShowed(numItemsShowed: Int) = launchesListItems.assertCountEquals(numItemsShowed)

    @ExperimentalMaterialApi
    fun advanceTimeBy(timeToAdvance: Long) = composeTestRule.mainClock.advanceTimeBy(timeToAdvance)

}
