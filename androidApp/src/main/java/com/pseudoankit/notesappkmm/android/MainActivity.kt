package com.pseudoankit.notesappkmm.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pseudoankit.notesappkmm.android.di.loadNotesDetailModules
import com.pseudoankit.notesappkmm.android.di.loadNotesListingModules
import com.pseudoankit.notesappkmm.android.di.unLoadNotesDetailModules
import com.pseudoankit.notesappkmm.android.di.unLoadNotesListingModules
import com.pseudoankit.notesappkmm.android.presentation.notes_detail.ui.NoteDetailScreen
import com.pseudoankit.notesappkmm.android.presentation.notes_listing.ui.NoteListScreen
import com.pseudoankit.notesappkmm.android.presentation.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // TODO move to screen
        loadNotesListingModules()
        loadNotesDetailModules()
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "note_list") {
                    composable(route = "note_list") {
                        NoteListScreen(navController = navController)
                    }
                    composable(
                        route = "note_detail/{noteId}",
                        arguments = listOf(
                            navArgument(name = "noteId") {
                                type = NavType.LongType
                                defaultValue = -1L
                            }
                        )
                    ) { backStackEntry ->
                        val noteId = backStackEntry.arguments?.getLong("noteId") ?: -1L
                        NoteDetailScreen(noteId = noteId, navController = navController)
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        unLoadNotesDetailModules()
        unLoadNotesListingModules()
        super.onDestroy()
    }
}
