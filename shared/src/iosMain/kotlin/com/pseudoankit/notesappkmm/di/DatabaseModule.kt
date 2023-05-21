package com.pseudoankit.notesappkmm.di

import com.pseudoankit.notesappkmm.data.local.DataBaseDriverFactory
import com.pseudoankit.notesappkmm.data.repository.NotesRepositoryImpl
import com.pseudoankit.notesappkmm.database.NotesDataBase
import com.pseudoankit.notesappkmm.domain.repository.NotesRepository

class DatabaseModule {
    private val factory by lazy { DataBaseDriverFactory() }
    val notesRepository: NotesRepository by lazy {
        NotesRepositoryImpl(NotesDataBase(factory.createDriver()))
    }
}