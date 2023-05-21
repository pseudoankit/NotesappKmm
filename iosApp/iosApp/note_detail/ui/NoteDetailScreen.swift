//
//  NoteDetailScreen.swift
//  iosApp
//
//  Created by Ankit Kumar on 21/05/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct NoteDetailScreen: View {
    
    private var noteRepository: NotesRepository
    private var noteId: Int64? = nil
    
    @StateObject private var viewModel = NoteDetailViewModel(noteRepository: nil)
    @Environment(\.presentationMode) var presentation
    
    init(noteRepository: NotesRepository, noteId: Int64? = nil) {
        self.noteRepository = noteRepository
        self.noteId = noteId
    }
    
    var body: some View {
        VStack(alignment: .leading) {
            TextField("Enter a title", text: $viewModel.noteTitle)
                .font(.title)
            TextField("Enter some content", text: $viewModel.noteContent)
            Spacer()
        }.toolbar(content: {
            Button(action: {
                viewModel.savenote {
                    self.presentation.wrappedValue.dismiss()
                }
            }) {
                Image(systemName: "checkmark")
            }
        })
        .padding()
        .background(Color(hex: viewModel.noteColor))
        .onAppear {
            viewModel.setParamsAndLoadNote(noteRepository: noteRepository, noteId: noteId)
        }
    }
}

