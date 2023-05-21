//
//  NotesListViewModel.swift
//  iosApp
//
//  Created by Ankit Kumar on 21/05/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension NotesListScreen {
    @MainActor class NotesListViewModel : ObservableObject {
        
        private let searchNotesUseCase = SearchNotesUseCase()
        private var notesRepository : NotesRepository? = nil
        private var notes = [Note]()
        
        @Published private(set) var filteredNotes = [Note]()
        @Published var searchText = "" {
            didSet {
                self.filteredNotes =
                    searchNotesUseCase.invoke(notes: self.notes, query: searchText)
            }
        }
        @Published private(set) var isSearchActive = false
        
        
        init(notesRepository: NotesRepository? = nil) {
            self.notesRepository = notesRepository
        }
        
        func loadNotes() {
            notesRepository?.getAllNotes(completionHandler: { notes, error in
                self.notes = notes ?? []
                self.filteredNotes = self.notes
            })
        }
        
        func deleteNoteById(id: Int64?) {
            if(id != nil) {
                notesRepository?.deleteNoteById(id: id!, completionHandler: { error in
                    self.loadNotes()
                })
            }
        }
        
        func toggleIsSearchActive() {
            self.isSearchActive = !self.isSearchActive
            if (!isSearchActive) {
                searchText = ""
            }
        }
        
        func setNotesRepository(notesRepository: NotesRepository) {
            self.notesRepository = notesRepository
        }
    }
}
