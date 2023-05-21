package com.pseudoankit.notesappkmm.android.presentation.notes_listing

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pseudoankit.notesappkmm.android.presentation.util.setState
import com.pseudoankit.notesappkmm.domain.repository.NotesRepository
import com.pseudoankit.notesappkmm.domain.usecase.SearchNotesUseCase
import kotlinx.coroutines.launch

class NotesListingViewModel(
    private val notesRepository: NotesRepository,
    private val searchNotesUseCase: SearchNotesUseCase
) : ViewModel() {

    val state = mutableStateOf(NotesListingState())
    private suspend fun dbNotes() = notesRepository.getAllNotes()

//    TODO do via save state handle
//    private val notes = savedStateHandle.getStateFlow("notes", emptyList<Note>())
//    private val searchText = savedStateHandle.getStateFlow("searchText", "")
//    private val isSearchActive = savedStateHandle.getStateFlow("isSearchActive", false)

//    val state = combine(notes, searchText, isSearchActive) { notes, searchText, isSearchActive ->
//        NotesListingState(
//            notes = searchNotes.execute(notes, searchText),
//            searchText = searchText,
//            isSearchActive = isSearchActive
//        )
//    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NotesListingState())

    fun loadNotes() {
        viewModelScope.launch {
            state.setState {
                copy(
                    notes = dbNotes()
                )
            }
        }
    }

    fun onSearchTextChange(text: String) {
        viewModelScope.launch {
            state.setState {
                copy(
                    searchText = text,
                    notes = searchNotesUseCase(dbNotes(), text)
                )
            }
        }
    }

    fun onToggleSearch() {
        state.setState {
            copy(
                isSearchActive = !isSearchActive
            )
        }
        if (state.value.isSearchActive.not()) {
            onSearchTextChange("")
        }
    }

    fun deleteNoteById(id: Long) {
        viewModelScope.launch {
            notesRepository.deleteNoteById(id)
            loadNotes()
        }
    }
}