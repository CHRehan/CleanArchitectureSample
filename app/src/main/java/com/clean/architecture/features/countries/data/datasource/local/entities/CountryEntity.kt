package com.clean.architecture.features.countries.data.datasource.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Rehan Sarwar on 12/06/2022.
 */
@Entity(tableName = "countries")
data class CountryEntity(
    val commonName: String,
    val officialName: String,
    val unMember: Boolean,
    val capitals: String,
    val region: String,
    val subregion: String,
    val languages: String,
    val currencies: String,
    val population: Long,
    val timezones: String,
    val continents: String,
    val flagSVG: String,
    val startOfWeek: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
