package com.pseudoankit.notesappkmm.android.presentation.notes_detail

data class NoteDetailState(
    val noteTitle: String = "",
    val noteContent: String = "",
    val noteColor: Long = 0xFFFFFF,
    val isNoteTitleFocused: Boolean = false,
    val isNoteContentFocused: Boolean = false,
) {
    val isTitleHintVisible = !isNoteTitleFocused && noteTitle.isBlank()
    val isContentHintVisible = !isNoteContentFocused && noteContent.isBlank()
}