package com.typ.citypulse.ui.utils

import cafe.adriel.voyager.core.screen.Screen
import com.typ.citypulse.UserRole
import com.typ.citypulse.UserRole.*
import com.typ.citypulse.ui.screens.UserRoleHomeScreen

fun getHomeScreenForCurrentRole(role: UserRole) : Screen{
    return when(role) {
        USER -> UserRoleHomeScreen()
        AGENCY -> TODO("to be implemented.")
    }
}