package com.pseudoankit.notesappkmm.data.local

import android.content.Context
import com.pseudoankit.notesappkmm.database.NotesDataBase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DataBaseDriverFactory(
    private val context: Context
) {

    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            schema = NotesDataBase.Schema,
            context = context,
            name = "note.db"
        )
    }
}