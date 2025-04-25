package com.typ.citypulse.ui.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.typ.citypulse.UserRole
import com.typ.citypulse.UserRole.AGENCY
import com.typ.citypulse.UserRole.USER
import com.typ.citypulse.ui.screens.UserRoleHomeScreen
import com.typ.citypulse.ui.theme.CityPulseTheme
import io.github.alexzhirkevich.cupertino.CupertinoScaffold

fun getHomeScreenForCurrentRole(role: UserRole): Screen {
    return when (role) {
        USER -> UserRoleHomeScreen()
        AGENCY -> TODO("to be implemented.")
    }
}

@Composable
fun PreviewContainer(content: @Composable () -> Unit) {
    CityPulseTheme {
        CupertinoScaffold {
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(
                        vertical = 24.dp,
                        horizontal = 16.dp
                    )
            ) {
                content()
            }
        }
    }
}

fun Modifier.previewContainerPaddings(paddings: Int = 24) = this.padding(
    vertical = paddings.dp,
    horizontal = paddings.dp
)