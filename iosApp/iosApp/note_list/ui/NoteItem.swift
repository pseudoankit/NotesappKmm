//
//  NoteItem.swift
//  iosApp
//
//  Created by Ankit Kumar on 21/05/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct NoteItem: View {
    var note: Note
    var onNoteDeleteClicked: () -> Void
    
    var body: some View {
        VStack(alignment: .leading) {
            HStack {
                Text(note.title)
                    .font(.title3)
                    .fontWeight(.semibold)
                Spacer()
                Button(action: onNoteDeleteClicked) {
                    Image(systemName: "xmark").foregroundColor(.black)
                }
            }.padding(.bottom, 3)
            
            Text(note.content)
                .fontWeight(.light)
                .padding(.bottom, 3)
            
            HStack {
                Spacer()
                Text(DateTimeUtil().formatNoteDate(dateTime: note.createdAt))
                    .font(.footnote)
                    .fontWeight(.light)
            }
        }
            .padding()
            .background(Color(hex: note.colorHex))
            .clipShape(RoundedRectangle(cornerRadius: 5.0))
    }
}

struct NoteItem_Previews: PreviewProvider {
    static var previews: some View {
        NoteItem(
            note: Note(
                id: 0,
                title: "hey",
                content: "how it",
                colorHex: 0xffffff,
                createdAt: DateTimeUtil().now()
            ),
            onNoteDeleteClicked: { }
        )
    }
}
