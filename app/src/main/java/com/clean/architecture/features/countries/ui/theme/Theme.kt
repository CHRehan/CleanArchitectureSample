package com.clean.architecture.features.countries.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = DarkGreen,
    onPrimary = White,

    background = GreenishBlack,
    onBackground = White
)

private val LightColorPalette = lightColors(
    primary = Green,
    onPrimary = White,

    background = White,
    onBackground = GreenishBlack
)

@Composable
fun CleanArchitectureSampleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
