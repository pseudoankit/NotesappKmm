//
//  NotesListScreen.swift
//  iosApp
//
//  Created by Ankit Kumar on 21/05/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct NotesListScreen: View {
    private var notesRepository: NotesRepository
    @StateObject var viewModel = NotesListViewModel(notesRepository: nil)
    
    @State private var isNoteSelected = false
    @State private var selectedNoteId: Int64? = nil
    
    init(notesRepository: NotesRepository) {
        self.notesRepository = notesRepository
    }
    
    var body: some View {
        VStack {
            ZStack {
                NavigationLink(destination: NoteDetailScreen(
                    noteRepository: notesRepository,
                    noteId: selectedNoteId
                ),
               isActive: $isNoteSelected) {
                    EmptyView()
                }.hidden()
                HideableSearchTextField<NoteDetailScreen>(onSearchToggled: {
                    viewModel.toggleIsSearchActive()
                }, destinationProvider: {
                    NoteDetailScreen(
                        noteRepository: notesRepository,
                        noteId: selectedNoteId
                    )
                }, isSearchActive: viewModel.isSearchActive, searchText: $viewModel.searchText)
                .frame(maxWidth: .infinity, minHeight: 40)
                .padding()
                
                if !viewModel.isSearchActive {
                    Text("All notes")
                        .font(.title2)
                }
            }
            
            List {
                ForEach(viewModel.filteredNotes, id: \.self.id) { note in
                    Button(action: {
                        isNoteSelected = true
                        selectedNoteId = note.id?.int64Value
                    }) {
                        NoteItem(
                            note: note,
                            onNoteDeleteClicked : {
                                viewModel.deleteNoteById(id: note.id?.int64Value)
                            }
                        )
                    }
                }
            }
            .onAppear {
                viewModel.loadNotes()
            }
            .listStyle(.plain)
            .listRowSeparator(.hidden)
        }
        .onAppear {
            viewModel.setNotesRepository(notesRepository: notesRepository)
        }
    }
}


