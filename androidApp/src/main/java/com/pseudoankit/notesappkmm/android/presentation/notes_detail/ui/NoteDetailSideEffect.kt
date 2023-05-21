package com.pseudoankit.notesappkmm.android.presentation.notes_detail.ui

sealed interface NoteDetailSideEffect {
    object NavigateBack : NoteDetailSideEffect
}