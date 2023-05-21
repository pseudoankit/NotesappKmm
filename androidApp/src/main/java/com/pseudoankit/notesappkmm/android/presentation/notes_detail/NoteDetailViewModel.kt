package com.pseudoankit.notesappkmm.android.presentation.notes_detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pseudoankit.notesappkmm.android.presentation.notes_detail.ui.NoteDetailSideEffect
import com.pseudoankit.notesappkmm.android.presentation.util.postSideEffect
import com.pseudoankit.notesappkmm.android.presentation.util.setState
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
        state.setState { copy(noteTitle = text) }
    }

    fun onNoteContentChanged(text: String) {
        state.setState { copy(noteContent = text) }
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
            sideEffect.postSideEffect {
                NoteDetailSideEffect.NavigateBack
            }
        }
    }


}