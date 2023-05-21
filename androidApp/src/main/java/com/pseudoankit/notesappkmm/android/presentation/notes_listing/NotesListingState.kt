package com.pseudoankit.notesappkmm.android.presentation.notes_listing

import com.pseudoankit.notesappkmm.domain.model.Note

data class NotesListingState(
    val notes: List<Note> = emptyList(),
    val searchText: String = "",
    val isSearchActive: Boolean = false
)