//
//  PokemonsView.swift
//  ios
//
//  Created by Eduardo Pool on 09/04/20.
//  Copyright © 2020 Eduardo Pool. All rights reserved.
//

import SwiftUI

struct PokemonsView: View {

    @ObservedObject var bridge = PokemonsViewModelBridge()

    var body: some View {
        NavigationView {
            List(bridge.viewState.pokemons, id: \.id) { pokemon in
                PokemonItem(pokemon: pokemon)
            }.onPull(perform: { self.bridge.viewModel.loadPokemons() }, isLoading: bridge.viewState.isLoading)
            .navigationBarTitle("Hello KMP")
        }.onAppear {
            self.bridge.viewModel.loadPokemons()
        }.onDisappear {
            self.bridge.viewModel.onCleared()
        }
    }
}

struct PokemonsView_Previews: PreviewProvider {
    static var previews: some View {
        PokemonsView()
    }
}
