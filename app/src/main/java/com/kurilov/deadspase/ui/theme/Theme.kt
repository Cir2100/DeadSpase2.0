package com.kurilov.deadspase.ui.theme

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    //primary = color_light_primary,
    //onPrimary = color_light_onPrimary,
    surface = color_light_surface,
    onSurface = color_light_onSurface,
    surfaceVariant = color_light_surfaceVariant,
    onSurfaceVariant = color_light_onSurfaceVariant,
    secondary = color_light_secondary,
    background = color_light_background,
    /*primary = color_light_primary,
    onPrimary = color_light_onPrimary,
    primaryContainer = color_light_primaryContainer,
    onPrimaryContainer = color_light_onPrimaryContainer,
    inversePrimary = color_light_inversePrimary,
    secondary = color_light_secondary,
    onSecondary = color_light_onSecondary,
    secondaryContainer = color_light_secondaryContainer,
    onSecondaryContainer = color_light_onSecondaryContainer,
    tertiary = color_light_tertiary,
    onTertiary = color_light_onTertiary,
    tertiaryContainer = color_light_tertiaryContainer,
    onTertiaryContainer = color_light_onTertiaryContainer,
    background = color_light_background,
    onBackground = color_light_onBackground,
    surface = color_light_surface,
    onSurface = color_light_onSurface,
    surfaceVariant = color_light_surfaceVariant,
    onSurfaceVariant = color_light_onSurfaceVariant,
    inverseSurface = color_light_inverseSurface,
    inverseOnSurface = color_light_inverseOnSurface,
    error = color_light_error,
    onError = color_light_onError,
    errorContainer = color_light_errorContainer,
    onErrorContainer = color_light_onErrorContainer,
    outline = color_light_outline,*/
)

private val DarkColorScheme = darkColorScheme(
    primary = color_dark_primary,
    onPrimary = color_dark_onPrimary,
    surface = color_dark_surface,
    onSurface = color_dark_onSurface,
    surfaceVariant = color_dark_surfaceVariant,
    onSurfaceVariant = color_dark_onSurfaceVariant,
    secondary = color_dark_secondary,
    background = color_dark_background,
    /*primary = color_dark_primary,
    onPrimary = color_dark_onPrimary,
    primaryContainer = color_dark_primaryContainer,
    onPrimaryContainer = color_dark_onPrimaryContainer,
    inversePrimary = color_dark_inversePrimary,
    secondary = color_dark_secondary,
    onSecondary = color_dark_onSecondary,
    secondaryContainer = color_dark_secondaryContainer,
    onSecondaryContainer = color_dark_onSecondaryContainer,
    tertiary = color_dark_tertiary,
    onTertiary = color_dark_onTertiary,
    tertiaryContainer = color_dark_tertiaryContainer,
    onTertiaryContainer = color_dark_onTertiaryContainer,
    background = color_dark_background,
    onBackground = color_dark_onBackground,
    surface = color_dark_surface,
    onSurface = color_dark_onSurface,
    surfaceVariant = color_dark_surfaceVariant,
    onSurfaceVariant = color_dark_onSurfaceVariant,
    inverseSurface = color_dark_inverseSurface,
    inverseOnSurface = color_dark_inverseOnSurface,
    error = color_dark_error,
    onError = color_dark_onError,
    errorContainer = color_dark_errorContainer,
    onErrorContainer = color_dark_onErrorContainer,
    outline = color_dark_outline,*/
)

@Composable
fun DeadSpaseTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    StaticAppTheme(darkTheme, content)
    /*&if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        DynamicAppTheme(darkTheme, dynamicColor, content)
    } else {
        StaticAppTheme(darkTheme, content)
    }*/
}

@Composable
fun StaticAppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (!useDarkTheme) {
        LightColorScheme
    } else {
        DarkColorScheme
    }
    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun DynamicAppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    isDynamic: Boolean = true,
    content: @Composable() () -> Unit
) {
    val colors = if (isDynamic) {
        val context = LocalContext.current
        if (useDarkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    } else {
        if (useDarkTheme) DarkColorScheme else LightColorScheme
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}