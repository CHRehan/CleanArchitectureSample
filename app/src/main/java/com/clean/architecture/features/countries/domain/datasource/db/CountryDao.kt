package com.clean.architecture.features.countries.domain.datasource.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.clean.architecture.features.countries.data.datasource.local.entities.CountryEntity

/**
 * Created by Rehan Sarwar on 12/06/2022.
 */
@Dao
interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(countries: Collection<CountryEntity>)

    @Query("SELECT * FROM countries")
    suspend fun getCountries(): List<CountryEntity>?
}
