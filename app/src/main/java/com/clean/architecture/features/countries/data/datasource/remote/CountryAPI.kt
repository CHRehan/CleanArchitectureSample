package com.clean.architecture.features.countries.data.datasource.remote

import com.clean.architecture.features.countries.data.model.CountryDataModel
import retrofit2.http.GET

interface CountryAPI {
    @GET("all")
    suspend fun getAllCountries(): Collection<CountryDataModel>
}
