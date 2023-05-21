package com.pseudoankit.notesappkmm.android.presentation.notes_listing

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pseudoankit.notesappkmm.domain.model.Note
import com.pseudoankit.notesappkmm.domain.repository.NotesRepository
import com.pseudoankit.notesappkmm.domain.usecase.SearchNotesUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NotesListingViewModel(
    private val notesRepository: NotesRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val searchNotes = SearchNotesUseCase()

    private val notes = savedStateHandle.getStateFlow("notes", emptyList<Note>())
    private val searchText = savedStateHandle.getStateFlow("searchText", "")
    private val isSearchActive = savedStateHandle.getStateFlow("isSearchActive", false)

    val state = combine(notes, searchText, isSearchActive) { notes, searchText, isSearchActive ->
        NotesListingState(
            notes = searchNotes.execute(notes, searchText),
            searchText = searchText,
            isSearchActive = isSearchActive
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NotesListingState())

    fun loadNotes() {
        viewModelScope.launch {
            savedStateHandle["notes"] = notesRepository.getAllNotes()
        }
    }

    fun onSearchTextChange(text: String) {
        savedStateHandle["searchText"] = text
    }

    fun onToggleSearch() {
        savedStateHandle["isSearchActive"] = !isSearchActive.value
        if(!isSearchActive.value) {
            savedStateHandle["searchText"] = ""
        }
    }

    fun deleteNoteById(id: Long) {
        viewModelScope.launch {
            notesRepository.deleteNoteById(id)
            loadNotes()
        }
    }
}