package com.clean.architecture.features.common

import com.clean.architecture.features.countries.domain.model.CountryModel
import com.clean.architecture.features.countries.ui.countrydetail.model.CountryDetail
import retrofit2.Response

/**
 * Created by Rehan Sarwar on 11/06/2022.
 */

fun CountryModel.getCountryDetail(): List<CountryDetail> {
    val countryDetails = ArrayList<CountryDetail>()

    countryDetails.add(CountryDetail("Official Name", officialName))
    countryDetails.add(CountryDetail("Common Name", commonName))
    countryDetails.add(CountryDetail("Start Of Week", startOfWeek))
    countryDetails.add(CountryDetail("Is UN member", if (unMember) "Yes" else "No"))
    countryDetails.add(CountryDetail("Capital(s)", capitals))
    countryDetails.add(CountryDetail("Region", region))
    countryDetails.add(CountryDetail("Languages", languages))
    countryDetails.add(CountryDetail("Currencies", currencies))
    countryDetails.add(CountryDetail("Population", population.format()))
    countryDetails.add(CountryDetail("Time Zone(s)", timezones))
    countryDetails.add(CountryDetail("Continents", continents))

    return countryDetails
}

fun Long.format(): String {
    return this.toString()
//    return "%,.2f".format(Locale.ENGLISH, this)
}

internal fun Response<*>.getException(): Exception {
    return Exception(
        message()
    )
}

internal fun <DataModel, T : DataModel> Response<T>.extractResult(): Resource<DataModel> {
    val successfulResponse = body()

    if (isSuccessful && successfulResponse != null) {
        return Resource.Success(successfulResponse)
    }

    return Resource.Error(getException().localizedMessage ?: "Something went wrong")
}
