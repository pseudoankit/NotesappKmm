package com.pseudoankit.notesappkmm.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pseudoankit.notesappkmm.android.di.loadNotesListingModules
import com.pseudoankit.notesappkmm.android.di.unLoadNotesListingModules
import com.pseudoankit.notesappkmm.android.presentation.notes_listing.ui.NoteListScreen
import com.pseudoankit.notesappkmm.android.presentation.theme.MyApplicationTheme
import org.koin.core.context.loadKoinModules

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        loadNotesListingModules()
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NoteListScreen()
                }
            }
        }
    }

    override fun onDestroy() {
        unLoadNotesListingModules()
        super.onDestroy()
    }
}
