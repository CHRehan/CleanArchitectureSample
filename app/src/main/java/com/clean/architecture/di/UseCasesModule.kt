package com.clean.architecture.di

import com.clean.architecture.features.countries.domain.repository.CountriesRepository
import com.clean.architecture.features.countries.domain.usecase.CountriesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Rehan Sarwar on 07/06/2022.
 **/

@Module
@InstallIn(SingletonComponent::class)
class UseCasesModule {

    @Provides
    fun provideSectionsUseCase(countriesRepository: CountriesRepository) =
        CountriesUseCase(countriesRepository)
}
