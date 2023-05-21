package com.pseudoankit.notesappkmm.android.presentation.notes_listing

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pseudoankit.notesappkmm.domain.repository.NotesRepository
import com.pseudoankit.notesappkmm.domain.usecase.SearchNotesUseCase
import kotlinx.coroutines.launch

class NotesListingViewModel(
    private val notesRepository: NotesRepository,
    private val searchNotesUseCase: SearchNotesUseCase
) : ViewModel() {


    val state = mutableStateOf(NotesListingState())

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
            setState {
                copy(
                    notes = notesRepository.getAllNotes()
                )
            }
        }
    }

    fun onSearchTextChange(text: String) {
        setState {
            copy(
                searchText = searchText,
                notes = searchNotesUseCase(notes, searchText)
            )
        }
    }

    fun onToggleSearch() {
        setState {
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

    private inline fun setState(block: NotesListingState.() -> NotesListingState) {
        state.value = state.value.block()
    }
}