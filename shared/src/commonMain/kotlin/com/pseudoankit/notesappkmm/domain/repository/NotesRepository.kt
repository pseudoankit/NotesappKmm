package com.pseudoankit.notesappkmm.domain.repository

import com.pseudoankit.notesappkmm.domain.model.Note

interface NotesRepository {
    suspend fun insertNote(note: Note)
    suspend fun getNoteById(id: Long): Note?
    suspend fun getAllNotes(): List<Note>
    suspend fun deleteNoteById(id: Long)
}
