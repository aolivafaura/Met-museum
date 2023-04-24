package com.aoliva.metmuseum.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorPalette = darkColorScheme(
    primary = MetBlackJet,
    secondary = MetBlackJetVariant,
    tertiary = MetCandyAppleRed,
    background = MetWhiteCultured,
    surface = MetWhiteCultured,
    onPrimary = MetWhiteCultured,
    onSecondary = MetBlackJet,
    onBackground = MetBlackJet,
    onSurface = MetBlackJet,
)

private val LightColorPalette = lightColorScheme(
    primary = MetBlackJet,
    secondary = MetBlackJetVariant,
    tertiary = MetCandyAppleRed,
    background = MetWhiteCultured,
    surface = MetWhiteCultured,
    onPrimary = MetWhiteCultured,
    onSecondary = MetBlackJet,
    onBackground = MetBlackJet,
    onSurface = MetBlackJet,
)

@Composable
fun MetMuseumTheme(isDarkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = MetBlackJetVariant.toArgb()
            window.navigationBarColor = MetBlackJetVariant.toArgb()

            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = isDarkTheme
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = isDarkTheme
        }
    }

    val colors = if (isDarkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}