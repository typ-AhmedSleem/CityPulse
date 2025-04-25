package com.typ.citypulse.ui.theme

import androidx.compose.runtime.Composable
import io.github.alexzhirkevich.cupertino.theme.ColorScheme
import io.github.alexzhirkevich.cupertino.theme.CupertinoTheme
import io.github.alexzhirkevich.cupertino.theme.Typography
import io.github.alexzhirkevich.cupertino.theme.lightColorScheme

@Composable
fun CityPulseTheme(
    colorScheme: ColorScheme = lightColorScheme(),
    typography: Typography = Typography(),
    content: @Composable () -> Unit,
) {
    CupertinoTheme(
        colorScheme= colorScheme,
        typography= typography,
        content = content
    )
}