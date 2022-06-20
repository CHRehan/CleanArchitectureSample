package com.clean.architecture.features.countries.ui.countrydetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.imageLoader
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.clean.architecture.R
import com.clean.architecture.features.common.getCountryDetail
import com.clean.architecture.features.countries.domain.model.CountryModel
import com.clean.architecture.utils.TestTags

/**
 * Created by Rehan Sarwar on 11/06/2022.
 */

@Composable
fun CountryDetailScreen(
    country: CountryModel?,
    navController: NavController
) {

    country?.let {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = country.commonName) },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                Icons.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.back_button)
                            )
                        }
                    },
                    backgroundColor = MaterialTheme.colors.primary

                )
            }
        ) {
            LazyColumn {
                item {
                    FlagCard(country.flagSVG)
                }
                val countryDetail = country.getCountryDetail()
                items(countryDetail.size, { itemKey ->
                    itemKey.toString()
                }, itemContent = { index ->
                        CountryDetailItem(countryDetail[index].title, countryDetail[index].value)
                    })
            }
        }
    }
}

@Composable
private fun CountryDetailItem(title: String, value: String) {
    Column {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = title.plus(" : "),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colors.onBackground
            )
            Text(
                text = value,
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.onBackground.copy(alpha = 0.7f)
            )
        }
        Divider(modifier = Modifier.padding(horizontal = 16.dp))
    }
}

@Composable
private fun FlagCard(flagSVG: String) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .testTag(TestTags.COUNTRY_DETAIL),
        shape = MaterialTheme.shapes.medium,
        elevation = 6.dp
    ) {
        val imageRequest = ImageRequest.Builder(context)
            .data(flagSVG)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .diskCachePolicy(CachePolicy.ENABLED)
            .build()

        val painter = rememberAsyncImagePainter(imageRequest, context.imageLoader)

        Image(
            painter = painter,
            contentDescription = flagSVG,
            modifier = Modifier
                .size(180.dp),
            contentScale = ContentScale.FillBounds
        )
    }
}
