package com.pseudoankit.notesappkmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform