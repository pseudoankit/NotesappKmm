package com.pseudoankit.notesappkmm.data.local

import com.squareup.sqldelight.db.SqlDriver

expect class DataBaseDriverFactory {
    fun createDriver(): SqlDriver
}