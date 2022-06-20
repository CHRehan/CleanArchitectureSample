package com.clean.architecture.features.countries.ui.countrylist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.clean.architecture.features.countries.domain.model.CountryModel
import com.clean.architecture.utils.TestTags.COUNTRY_LIST

/**
 * Created by Rehan Sarwar on 11/06/2022.
 */

@ExperimentalMaterialApi
@Composable
fun CountryList(
    list: List<CountryModel>,
    onCountryClick: (CountryModel) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.testTag(COUNTRY_LIST)
    ) {
        items(list.size, { itemKey ->
            itemKey.toString()
        }, itemContent = { itemIndex ->
                ItemCountryList(country = list[itemIndex]) {
                    onCountryClick(list[itemIndex])
                }
            })
    }
}
