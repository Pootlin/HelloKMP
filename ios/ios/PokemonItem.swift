//
//  PokemonItem.swift
//  ios
//
//  Created by Eduardo Pool on 09/04/20.
//  Copyright © 2020 Eduardo Pool. All rights reserved.
//

import SwiftUI
import HelloKMP

struct PokemonItem: View {

    var pokemon: PokemonIdentity
    
    var body: some View {
        HStack {
            AsyncImage(url: URL(string: pokemon.imageUrl)!, placeholder: Text("Loading..."), configuration: { $0.resizable() })
            .frame(width: 100, height: 100)
            Text(pokemon.name)
                .font(.title)
        }
    }

}

struct PokemonItem_Previews: PreviewProvider {
    static var previews: some View {
        PokemonItem(
            pokemon: PokemonIdentity(
                id: 25,
                name: "Pikemon",
                imageUrl: "https://pokeapi.co/api/v2/pokemon/25/"
            )
        )
    }
}
