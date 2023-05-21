package com.pseudoankit.notesappkmm.android.di

import com.pseudoankit.notesappkmm.android.presentation.notes_detail.NoteDetailViewModel
import com.pseudoankit.notesappkmm.data.repository.NotesRepositoryImpl
import com.pseudoankit.notesappkmm.domain.repository.NotesRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module

fun loadNotesDetailModules() {
    loadKoinModules(modules)
}

fun unLoadNotesDetailModules() {
    unloadKoinModules(modules)
}

private val modules = module {
    factory<NotesRepository> { NotesRepositoryImpl(get()) }
    viewModel { NoteDetailViewModel(get()) }
}