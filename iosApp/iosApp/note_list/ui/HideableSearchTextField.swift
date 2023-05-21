//
//  HideableSearchTextField.swift
//  iosApp
//
//  Created by Ankit Kumar on 21/05/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct HideableSearchTextField<Destination: View>: View {
    
    var onSearchToggled: () -> Void
    var desintationProvider : () -> Destination
    var isSearchActive : Bool
    @Binding var searchText: String
    
    var body: some View {
        HStack {
            TextField("Search...", text: $searchText)
                .textFieldStyle(.roundedBorder)
                .opacity(isSearchActive ? 1 : 0)
            if !isSearchActive {
                Spacer()
            }
            Button(action: onSearchToggled) {
                Image(systemName: isSearchActive ? "xmark" : "magnifyingglass")
            }
            NavigationLink(destination: desintationProvider()) {
                Image(systemName: "plus")
            }
        }
    }
}

struct HideableSearchTextField_Previews: PreviewProvider {
    static var previews: some View {
        HideableSearchTextField(
            onSearchToggled: {},
            desintationProvider: { EmptyView() },
            isSearchActive: true,
            searchText: .constant("text")
        )
    }
}
