package com.typ.citypulse.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.typ.citypulse.R
import com.typ.citypulse.UserRole
import com.typ.citypulse.ui.screens.BaseHomeScreen
import com.typ.citypulse.ui.screens.UserRoleHomeScreen
import com.typ.citypulse.ui.theme.CityPulseTheme
import com.typ.citypulse.ui.utils.getHomeScreenForCurrentRole
import io.github.alexzhirkevich.cupertino.CupertinoNavigateBackButton
import io.github.alexzhirkevich.cupertino.CupertinoScaffold
import io.github.alexzhirkevich.cupertino.CupertinoText
import io.github.alexzhirkevich.cupertino.CupertinoTopAppBar
import io.github.alexzhirkevich.cupertino.ExperimentalCupertinoApi

@OptIn(ExperimentalCupertinoApi::class)
@Composable
fun CityPulseApp() {
    val userRole = remember { UserRole.USER }
    var navigator: Navigator? by remember { mutableStateOf(null) }
    var currentScreen: Screen? by remember { mutableStateOf(null) }

    CityPulseTheme {
        CupertinoScaffold(
            topBar = {
                CupertinoTopAppBar(
                    title = {
                        CupertinoText(
                            text = stringResource(R.string.app_name)
                        )
                    },
                    navigationIcon = {
                        AnimatedVisibility(
                            visible = currentScreen !is BaseHomeScreen,
                        ) {
                            CupertinoNavigateBackButton(
                                onClick = { navigator?.pop() }
                            ) {
                                CupertinoText(stringResource(R.string.back))
                            }
                        }
                    }
                )
            }
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