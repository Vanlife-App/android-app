package com.vanlife.android_app.presentation.ui.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

private val primaryHighlight = Color(0xFFFF6448)
private val faded = Color(4287532690)

val LightColors = lightColors(
    primary = primaryHighlight,
    secondary = Color(4293719286),
    secondaryVariant = faded,
    background = Color(4294900479),
    error = Color.Red,
    onPrimary = Color.White,
    onSecondary = Color(4281019179),
    onBackground = Color(4287532690),
    onError = Color.White
)

val DarkColors = darkColors(
    primary = primaryHighlight,
    secondary = Color(4281350972),
    secondaryVariant = faded,
    background = Color(4279440151),
    error = Color.Red,
    onPrimary = Color.White,
    onSecondary = Color(4293322470),
    onBackground = Color(4294901502),
    onError = Color.White
)