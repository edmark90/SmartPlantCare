package com.example.smartplantcare.ui.theme


import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val PlantColorScheme = lightColorScheme(
    primary        = GreenPrimary,
    onPrimary      = Color.White,
    background     = Color.White,
    surface        = SurfaceWhite,
    onSurface      = TextDark,
    onBackground   = TextDark
)

@Composable
fun PlantAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = PlantColorScheme,
        typography  = Typography(),
        content     = content
    )
}