package com.clean.architecture.features.countries.domain.usecase

import com.clean.architecture.features.common.Resource
import com.clean.architecture.features.countries.domain.model.CountryModel
import com.clean.architecture.features.countries.domain.repository.CountriesRepository
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

/**
 * Created by Rehan Sarwar on 07/06/2022.
 */

class CountriesUseCase @Inject constructor(
    private val countriesRepository: CountriesRepository
) {
    operator fun invoke(): Flow<Resource<List<CountryModel>>> = flow {
        try {
            val countries = countriesRepository
                .getAllCountries()
                .sortedBy { country -> country.commonName }
            emit(Resource.Success<List<CountryModel>>(countries))
        } catch (e: HttpException) {
            emit(
                Resource.Error<List<CountryModel>>(
                    e.localizedMessage ?: "An unexpected error occured"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<List<CountryModel>>("Couldn't reach the server. Check your internet connection"))
        }
    }
}
