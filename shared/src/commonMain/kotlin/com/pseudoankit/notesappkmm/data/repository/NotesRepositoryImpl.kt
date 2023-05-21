package com.pseudoankit.notesappkmm.data.repository

import com.pseudoankit.notesappkmm.database.NotesDataBase
import com.pseudoankit.notesappkmm.domain.mapper.mapToDomain
import com.pseudoankit.notesappkmm.domain.model.Note
import com.pseudoankit.notesappkmm.domain.repository.NotesRepository
import com.pseudoankit.notesappkmm.util.DateTimeUtil

class NotesRepositoryImpl(
    dataBase: NotesDataBase
) : NotesRepository {

    private val queries = dataBase.notesQueries


    override suspend fun insertNote(note: Note) {
        queries.insertNote(
            id = note.id,
            title = note.title,
            content = note.content,
            colorHex = note.colorHex,
            created = DateTimeUtil.toEpochMillis(note.createdAt)
        )
    }

    override suspend fun getNoteById(id: Long): Note? {
        return queries
            .getNoteById(id)
            .executeAsOneOrNull()
            ?.mapToDomain()
    }

    override suspend fun getAllNotes(): List<Note> {
        return queries
            .getAllNotes()
            .executeAsList()
            .map { it.mapToDomain() }
    }

    override suspend fun deleteNoteById(id: Long) {
        queries.deleteNoteById(id)
    }
}