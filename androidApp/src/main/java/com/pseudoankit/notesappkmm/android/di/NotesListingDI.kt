package com.pseudoankit.notesappkmm.android.di

import com.pseudoankit.notesappkmm.android.presentation.notes_listing.NotesListingViewModel
import com.pseudoankit.notesappkmm.data.repository.NotesRepositoryImpl
import com.pseudoankit.notesappkmm.domain.repository.NotesRepository
import com.pseudoankit.notesappkmm.domain.usecase.SearchNotesUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module

fun loadNotesListingModules() {
    loadKoinModules(notesListingDI)
}

fun unLoadNotesListingModules() {
    unloadKoinModules(notesListingDI)
}

val notesListingDI = module {
    factory { SearchNotesUseCase() }
    factory<NotesRepository> { NotesRepositoryImpl(get()) }
    viewModel { NotesListingViewModel(get(), get()) }
}