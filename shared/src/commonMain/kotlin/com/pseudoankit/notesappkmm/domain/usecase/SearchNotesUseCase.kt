package com.pseudoankit.notesappkmm.domain.usecase

import com.pseudoankit.notesappkmm.domain.model.Note
import com.pseudoankit.notesappkmm.util.DateTimeUtil

class SearchNotesUseCase {

    fun execute(notes: List<Note>, query: String): List<Note> {
        if (query.isBlank()) return notes

        return notes.filter {
            it.title.trim().lowercase().contains(query.lowercase()) ||
                    it.content.trim().lowercase().contains(query.lowercase())
        }.sortedBy {
            DateTimeUtil.toEpochMillis(it.createdAt)
        }
    }
}