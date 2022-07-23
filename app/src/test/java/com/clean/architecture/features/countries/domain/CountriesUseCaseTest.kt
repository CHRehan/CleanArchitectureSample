package com.clean.architecture.features.countries.domain

import com.clean.architecture.features.countries.domain.model.CountryModel
import com.clean.architecture.features.countries.domain.repository.CountriesRepository
import com.clean.architecture.features.countries.domain.usecase.CountriesUseCase
import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Rehan Sarwar on 15/06/2022.
 */
@RunWith(MockitoJUnitRunner::class)
class CountriesUseCaseTest {
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

    @Mock
    lateinit var countriesRepository: CountriesRepository

    private lateinit var classUnderTest: CountriesUseCase

    @Before
    internal fun setUp() {
        classUnderTest = CountriesUseCase(countriesRepository)
    }

    @Test
    fun `Given CountriesUseCase when invoke then return listOf CountryModel`() {
        runBlocking {
            // given
            val expectedResult = listOf(country)

            given(countriesRepository.getAllCountries()).willReturn(
                listOf(country)
            )

            // when
            var actualResult: Collection<CountryModel>? = null
            classUnderTest().collect {
                actualResult = it.data
            }

            // then
            assertEquals(expectedResult, actualResult)
        }
    }
}
