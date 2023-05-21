package com.pseudoankit.notesappkmm.domain.mapper

import com.pseudoankit.notesappkmm.domain.model.Note
import database.NoteEntity
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun NoteEntity.mapToDomain(): Note {
    return Note(
        id = id,
        title = title,
        content = content,
        colorHex = colorHex,
        createdAt = Instant
            .fromEpochMilliseconds(created)
            .toLocalDateTime(TimeZone.currentSystemDefault())
    )
}