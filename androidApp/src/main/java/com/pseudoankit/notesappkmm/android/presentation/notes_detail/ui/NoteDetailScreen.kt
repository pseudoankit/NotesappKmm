package com.pseudoankit.notesappkmm.android.presentation.notes_detail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.pseudoankit.notesappkmm.android.presentation.notes_detail.NoteDetailViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun NoteDetailScreen(
    noteId: Long,
    navController: NavController,
    viewModel: NoteDetailViewModel = getViewModel { parametersOf(noteId) }
) {
    val state by viewModel.state

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collectLatest {
            when (it) {
                NoteDetailSideEffect.NavigateBack -> navController.navigateUp()
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = viewModel::saveNote,
                backgroundColor = Color.Black
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Save note",
                    tint = Color.White
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .background(Color(state.noteColor))
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            TransparentHintTextField(
                text = state.noteTitle,
                hint = "Enter a title...",
                onValueChanged = viewModel::onNoteTitleChanged,
                singleLine = true,
                textStyle = TextStyle(fontSize = 20.sp),
                onFocusChanged = {
                    viewModel.onNoteTitleFocusChanged(it.isFocused)
                },
                isHintVisible = state.isTitleHintVisible
            )
            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = state.noteContent,
                hint = "Enter some content...",
                onValueChanged = viewModel::onNoteContentChanged,
                singleLine = false,
                textStyle = TextStyle(fontSize = 20.sp),
                modifier = Modifier.weight(1f),
                isHintVisible = state.isContentHintVisible,
                onFocusChanged = {
                    viewModel.onNoteContentFocusChanged(it.isFocused)
                }
            )
        }
    }
}