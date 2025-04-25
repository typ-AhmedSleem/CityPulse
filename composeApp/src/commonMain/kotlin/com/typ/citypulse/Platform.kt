package com.typ.citypulse

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform