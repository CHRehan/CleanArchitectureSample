package com.clean.architecture.features.countries.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.clean.architecture.R

/**
 * Created by Rehan Sarwar on 11/06/2022.
 */

@Composable
fun TryAgainWidget(message: String?, onTryAgain: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
            .padding(50.dp)
            .clickable { onTryAgain.invoke() }
    ) {
        Text(
            text = message ?: stringResource(R.string.something_went_wrong),
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.error
        )
        Text(
            text = stringResource(R.string.tap_to_retry),
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.error
        )
    }
}
