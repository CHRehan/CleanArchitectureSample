package com.clean.architecture.features.countries.data.mapper

import com.clean.architecture.features.countries.data.datasource.local.entities.CountryEntity
import com.clean.architecture.features.countries.domain.model.CountryModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

/**
 * Created by Rehan Sarwar on 14/06/2022.
 */
private const val commonName = "Pakistan"
private const val officialName = "Islamic Republic Pakistan"
private const val unMember = true
private const val capitals = "Islamabad"
private const val region = "Asia"
private const val subregion = ""
private const val language = "English, Urdu"
private const val currencies = "Pakistani rupee"
private const val population = 22089331L
private const val timezone = "Utc+05:00"
private const val continent = "Asia"
private const val startOfWeek = "monday"
private const val flagSVG = "https://"

@RunWith(Parameterized::class)
class CountriesDatabaseToDomainMapperTest(
    private val input: List<CountryEntity>,
    private val expectedOutput: List<CountryModel>
) {

    companion object {

        @JvmStatic
        @Parameters(name = "Given {0} as database model When toDomain return expected domain model")
        fun inputs() =
            listOf(
                arrayOf(
                    listOf(
                        CountryEntity(
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
                    ),
                    listOf(
                        CountryModel(
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
                    )
                )
            )
    }

    private lateinit var classUnderTest: CountriesDatabaseToDomainMapper

    @Before
    fun setup() {
        classUnderTest = CountriesDatabaseToDomainMapper()
    }

    @Test
    fun `Given database model When toDomain return expected domain model`() {
        runBlocking {
            // When
            val actualValue = classUnderTest.toDomain(input)

            // Then
            assertEquals(expectedOutput, actualValue)
        }
    }
}
