package com.clean.architecture.features.countries.presentation

import com.clean.architecture.features.common.MainDispatcherRule
import com.clean.architecture.features.common.Resource
import com.clean.architecture.features.countries.domain.model.CountryModel
import com.clean.architecture.features.countries.domain.usecase.CountriesUseCase
import com.clean.architecture.features.countries.presentation.model.CountriesViewState
import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Rehan Sarwar on 16/06/2022.
 */
@RunWith(MockitoJUnitRunner::class)
class CountriesViewModelTest {
    private val commonName = "Pakistan"
    private val officialName = "Islamic Republic Pakistan"
    private val unMember = true
    private val capitals = "Islamabad"
    private val region = "Asia"
    private val subregion = ""
    private val language = "English, Urdu"
    private val currencies = "Pakistani rupee"
    private val population = 22089331L
    private val timezone = "Utc+05:00"
    private val continent = "Asia"
    private val startOfWeek = "monday"
    private val flagSVG = "https://"

    private val country = CountryModel(
        commonName = commonName,
        officialName = officialName,
        unMember = unMember,
        capitals = capitals,
        region = region,
        subregion = subregion,
        languages = language,
        currencies = currencies,
        population = population,
        timezones = timezone,
        continents = continent,
        flagSVG = flagSVG,
        startOfWeek = startOfWeek
    )
    private lateinit var classUnderTest: CountriesViewModel

    @Mock
    lateinit var countriesUseCase: CountriesUseCase

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup() {
        classUnderTest = CountriesViewModel(countriesUseCase)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Given initial state when called init Then set viewState dataLoading sets true`() {
        runBlocking {
            val expectedResult = CountriesViewState(
                isDataLoading = true,
                countries = emptyList(),
                errorMessage = ""
            )
            // When
            val actualResult = classUnderTest.currentViewState()

            // Then
            assertEquals(expectedResult, actualResult)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Given successfull usecase execution When called init Then update the view state with listOf countries`() {
        runTest {
            // Given
            given(countriesUseCase()).willReturn(
                flowOf(Resource.Success<Collection<CountryModel>>(listOf(country)))
            )
            advanceUntilIdle()
            val expectedResult = CountriesViewState(
                isDataLoading = false,
                countries = listOf(country),
                errorMessage = ""
            )

            // When
            val actualResult = classUnderTest.currentViewState()

            // Then
            assertEquals(expectedResult, actualResult)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Given failed usecase execution When called init Then update the view state and return Error`() {
        val expectedResult = CountriesViewState(
            isDataLoading = false,
            countries = emptyList(),
            errorMessage = "Something went wrong"
        )

        runTest {
            // Given
            given(countriesUseCase()).willReturn(
                flowOf(Resource.Error<Collection<CountryModel>>("Something went wrong"))
            )
            advanceUntilIdle()

            // When
            val actualResult = classUnderTest.currentViewState()

            // Then
            assertEquals(expectedResult, actualResult)
        }
    }
}
