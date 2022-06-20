package com.clean.architecture.features.countries.data.mapper

import com.clean.architecture.features.countries.data.model.CountryDataModel
import com.clean.architecture.features.countries.data.model.CurrencyDetail
import com.clean.architecture.features.countries.data.model.Flags
import com.clean.architecture.features.countries.data.model.Name
import com.clean.architecture.features.countries.domain.model.CountryModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

/**
 * Created by Rehan Sarwar on 15/06/2022.
 */
private const val commonName = "Pakistan"
private const val officialName = "Islamic Republic Pakistan"
private const val unMember = true
private const val capitals = "Islamabad"
private const val region = "Asia"
private const val subregion = ""
private const val language = "English, Urdu"
private const val currencies = "Pakistani rupee (Symbol: ₨)"
private const val population = 22089331L
private const val timezone = "Utc+05:00"
private const val continent = "Asia"
private const val startOfWeek = "monday"
private const val flagSVG = "https://"

@RunWith(Parameterized::class)
class CountriesDataToDomainMapperTest(
    private val input: List<CountryDataModel>,
    private val expectedOutput: List<CountryModel>
) {
    companion object {
        private fun getLanguagesHashMap(): HashMap<String, String>? {
            val hashmap: HashMap<String, String>? = HashMap()
            hashmap?.put("en", "English")
            hashmap?.put("ur", "Urdu")
            return hashmap
        }

        private fun getCurrencyHashMap(): HashMap<String, CurrencyDetail>? {
            val hashmap: HashMap<String, CurrencyDetail>? = HashMap()
            hashmap?.put("PKR", CurrencyDetail("Pakistani rupee", "₨"))
            return hashmap
        }

        @JvmStatic
        @Parameters(name = "Given {0} as data model When toDomain return expected domain model")
        fun inputs() =
            listOf(
                arrayOf(
                    listOf(
                        CountryDataModel(
                            name = Name(
                                common = commonName,
                                official = officialName
                            ),
                            unMember = unMember,
                            capital = listOf(capitals),
                            region = region,
                            subregion = subregion,
                            languages = getLanguagesHashMap(),
                            currencies = getCurrencyHashMap(),
                            population = population,
                            timezones = listOf(timezone),
                            continents = listOf(continent),
                            flags = Flags(flagSVG),
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

    private lateinit var classUnderTest: CountriesDataToDomainMapper

    @Before
    fun setup() {
        classUnderTest = CountriesDataToDomainMapper()
    }

    @Test
    fun `Given data model When toDomain return expected domain model`() {
        runBlocking {
            // When
            val actualValue = classUnderTest.toDomain(input)

            // Then
            Assert.assertEquals(expectedOutput, actualValue)
        }
    }
}
