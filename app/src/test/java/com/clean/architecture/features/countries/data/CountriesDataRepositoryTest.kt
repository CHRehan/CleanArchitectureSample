package com.clean.architecture.features.countries.data

import com.clean.architecture.features.common.Resource
import com.clean.architecture.features.countries.data.datasource.local.entities.CountryEntity
import com.clean.architecture.features.countries.data.datasource.remote.CountryAPI
import com.clean.architecture.features.countries.data.mapper.CountriesDataToDatabaseMapper
import com.clean.architecture.features.countries.data.mapper.CountriesDataToDomainMapper
import com.clean.architecture.features.countries.data.mapper.CountriesDatabaseToDomainMapper
import com.clean.architecture.features.countries.data.model.CountryDataModel
import com.clean.architecture.features.countries.data.model.CurrencyDetail
import com.clean.architecture.features.countries.data.model.Flags
import com.clean.architecture.features.countries.data.model.Name
import com.clean.architecture.features.countries.data.repository.CountriesDataRepository
import com.clean.architecture.features.countries.domain.datasource.db.CountryDao
import com.clean.architecture.features.countries.domain.model.CountryModel
import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Rehan Sarwar on 14/06/2022.
 */
@RunWith(MockitoJUnitRunner::class)
class CountriesDataRepositoryTest {

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
    private val countryEntity = CountryEntity(
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

    private val countryData = CountryDataModel(
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

    @Mock
    lateinit var countryAPI: CountryAPI

    @Mock
    lateinit var countryDao: CountryDao

    @Mock
    lateinit var countriesDataToDomainMapper: CountriesDataToDomainMapper

    @Mock
    lateinit var countriesDataToDatabaseMapper: CountriesDataToDatabaseMapper

    @Mock
    lateinit var countriesDatabaseToDomainMapper: CountriesDatabaseToDomainMapper
    private lateinit var classUnderTest: CountriesDataRepository

    @Before
    fun setup() {
        classUnderTest = CountriesDataRepository(
            countryAPI = countryAPI,
            countryDao = countryDao,
            countriesDatabaseToDomainMapper = countriesDatabaseToDomainMapper,
            countriesDataToDomainMapper = countriesDataToDomainMapper,
            countriesDataToDatabaseMapper = countriesDataToDatabaseMapper
        )
    }

    @Test
    fun `When getAllCountries called Then return listOf CountryModel`() {
        runBlocking {
            // Given
            val expectedResult = listOf(country)

            given(countryAPI.getAllCountries())
                .willReturn(listOf(countryData))

            given(countryDao.getCountries())
                .willReturn(listOf(countryEntity))

            given(countriesDataToDomainMapper.toDomain(listOf(countryData)))
                .willReturn(listOf(country))

            given(countriesDataToDatabaseMapper.toDatabase(listOf(countryData)))
                .willReturn(listOf(countryEntity))

            given(countriesDatabaseToDomainMapper.toDomain(listOf(countryEntity)))
                .willReturn(listOf(country))

            // When
            val actualResult = classUnderTest.getAllCountries()

            // Then
            assertEquals(expectedResult, actualResult)
        }
    }
}
