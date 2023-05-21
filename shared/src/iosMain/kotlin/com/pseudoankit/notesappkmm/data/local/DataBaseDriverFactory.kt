package com.pseudoankit.notesappkmm.data.local

import com.pseudoankit.notesappkmm.database.NotesDataBase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DataBaseDriverFactory {

    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(
            schema = NotesDataBase.Schema,
            name = "note.db"
        )
    }
}