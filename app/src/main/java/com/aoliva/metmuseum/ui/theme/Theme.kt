package com.aoliva.metmuseum.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = MetBlackJet,
    primaryVariant = MetBlackJetVariant,
    secondary = MetCandyAppleRed,
    background = MetWhiteCultured,
    surface = MetWhiteCultured,
    onPrimary = MetWhiteCultured,
    onSecondary = MetBlackJet,
    onBackground = MetBlackJet,
    onSurface = MetBlackJet,
)

private val LightColorPalette = lightColors(
    primary = MetBlackJet,
    primaryVariant = MetBlackJetVariant,
    secondary = MetCandyAppleRed,
    background = MetWhiteCultured,
    surface = MetWhiteCultured,
    onPrimary = MetWhiteCultured,
    onSecondary = MetBlackJet,
    onBackground = MetBlackJet,
    onSurface = MetBlackJet,
)

@Composable
fun MetMuseumTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(color = MetBlackJetVariant)

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