package com.vanlife.android_app.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun AndroidAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalSpacing provides Spacing()) {
        MaterialTheme(
            colors = if (darkTheme) DarkColors else LightColors,
            typography = SegoeTypography
        ) {
            val systemUiController = rememberSystemUiController()
            if (darkTheme) {
                systemUiController.setSystemBarsColor(
                    color = DarkColors.background
                )
                systemUiController.statusBarDarkContentEnabled = false
            } else {
                systemUiController.setSystemBarsColor(
                    color = LightColors.background
                )
                systemUiController.statusBarDarkContentEnabled = true
            }
            content()
        }
    }
}