package com.pseudoankit.notesappkmm.android

import android.app.Application
import com.pseudoankit.notesappkmm.data.local.DataBaseDriverFactory
import com.pseudoankit.notesappkmm.database.NotesDataBase
import com.squareup.sqldelight.db.SqlDriver
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.loadKoinModules
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class NotesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoinAndLoadModules()
    }

    private fun initKoinAndLoadModules() {
        startKoin {
            androidContext(this@NotesApplication)
        }

        // db
        loadKoinModules(
            module {
                single<SqlDriver> { DataBaseDriverFactory(applicationContext).createDriver() }
                single<NotesDataBase> { NotesDataBase(get()) }
            }
        )
    }
}