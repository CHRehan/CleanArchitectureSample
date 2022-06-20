package com.clean.architecture.features.countries.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountryModel(
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
    val startOfWeek: String,
) : Parcelable
