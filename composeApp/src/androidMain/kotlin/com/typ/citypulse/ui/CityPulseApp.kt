package com.typ.citypulse.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.typ.citypulse.UserRole
import com.typ.citypulse.ui.theme.CityPulseTheme
import com.typ.citypulse.ui.utils.getHomeScreenForCurrentRole
import io.github.alexzhirkevich.cupertino.CupertinoScaffold
import io.github.alexzhirkevich.cupertino.ExperimentalCupertinoApi

@OptIn(ExperimentalCupertinoApi::class)
@Composable
fun CityPulseApp() {
    val userRole = remember { UserRole.USER }
    var navigator: Navigator? by remember { mutableStateOf(null) }
    var currentScreen: Screen? by remember { mutableStateOf(null) }

    CityPulseTheme {
        CupertinoScaffold(

        ) { deviceInsetPaddings ->
            Navigator(
                screen = getHomeScreenForCurrentRole(userRole)
            ) { nav ->
                SlideTransition(
                    navigator = nav,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(deviceInsetPaddings)
                )

                navigator = nav
                currentScreen = nav.lastItemOrNull
            }
        }
    }
}