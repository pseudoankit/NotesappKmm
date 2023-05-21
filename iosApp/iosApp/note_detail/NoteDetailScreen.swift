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
    
    private var noteRepository: NotesRepository? = nil
    private var noteId: Int64? = nil
    
    init(noteRepository: NotesRepository? = nil, noteId: Int64? = nil) {
        self.noteRepository = noteRepository
        self.noteId = noteId
    }
    
    var body: some View {
        Text(/*@START_MENU_TOKEN@*/"Hello, World!"/*@END_MENU_TOKEN@*/)
    }
}

struct NoteDetailScreen_Previews: PreviewProvider {
    static var previews: some View {
        NoteDetailScreen()
    }
}
