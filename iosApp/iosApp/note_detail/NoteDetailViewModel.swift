//
//  NoteDetailViewModel.swift
//  iosApp
//
//  Created by Ankit Kumar on 21/05/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension NoteDetailScreen {
    @MainActor class NoteDetailViewModel : ObservableObject {
        private var noteRepository: NotesRepository? = nil
        private var noteId: Int64? = nil
        @Published var noteTitle = ""
        @Published var noteContent = ""
        @Published private(set) var noteColor = Note.Companion().generateRandomColor()
        
        init(noteRepository: NotesRepository? = nil) {
            self.noteRepository = noteRepository
        }
        
        func loadNoteIfExists(id: Int64?) {
            if id != nil {
                self.noteId = id
                noteRepository?.getNoteById(id: id!, completionHandler: {note, error in
                    self.noteTitle = note?.title ?? ""
                    self.noteContent = note?.content ?? ""
                    self.noteColor = note?.colorHex ?? Note.Companion().generateRandomColor()
                })
            }
        }
        
        func savenote(onSaved: @escaping () -> Void) {
            noteRepository?.insertNote(note: Note(
                id: noteId == nil ? nil : KotlinLong(value: noteId!),
                title: self.noteTitle,
                content: self.noteContent,
                colorHex: self.noteColor,
                createdAt: DateTimeUtil().now()
            ), completionHandler: { error in
                onSaved()
            })
        }
        
        func setParamsAndLoadNote(noteRepository: NotesRepository, noteId: Int64?) {
            self.noteRepository = noteRepository
            loadNoteIfExists(id: noteId)
        }
    }
}
