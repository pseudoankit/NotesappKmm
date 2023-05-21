package com.pseudoankit.notesappkmm.android.presentation.notes_detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pseudoankit.notesappkmm.android.presentation.notes_detail.ui.NoteDetailSideEffect
import com.pseudoankit.notesappkmm.domain.model.Note
import com.pseudoankit.notesappkmm.domain.repository.NotesRepository
import com.pseudoankit.notesappkmm.util.DateTimeUtil
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class NoteDetailViewModel(
    private val notesRepository: NotesRepository
) : ViewModel() {

    val state = mutableStateOf(NoteDetailState())
    val sideEffect = MutableSharedFlow<NoteDetailSideEffect>()

    private var existingNoteId: Long? = null

    fun onNoteTitleChanged(text: String) {
        setState { copy(noteTitle = text) }
    }

    fun onNoteContentChanged(text: String) {
        setState { copy(noteContent = text) }
    }

    fun saveNote() {
        viewModelScope.launch {
            notesRepository.insertNote(
                Note(
                    id = existingNoteId,
                    title = state.value.noteTitle,
                    content = state.value.noteContent,
                    colorHex = state.value.noteColor,
                    createdAt = DateTimeUtil.now()
                )
            )
            postSideEffect {
                NoteDetailSideEffect.NavigateBack
            }
        }
    }

    private inline fun setState(block: NoteDetailState.() -> NoteDetailState) {
        state.value = state.value.block()
    }

    private suspend inline fun postSideEffect(block: () -> NoteDetailSideEffect) {
        sideEffect.emit(block())
    }
}