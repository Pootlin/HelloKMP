//
//  PokemonItem.swift
//  ios
//
//  Created by Eduardo Pool on 09/04/20.
//  Copyright © 2020 Eduardo Pool. All rights reserved.
//

import SwiftUI

struct PokemonItem: View {
    
    var pokemonName: String
    var pokemonImageUrl: String
    
    var body: some View {
        HStack {
            Text(pokemonName)
        }
    }

}

struct PokemonItem_Previews: PreviewProvider {
    static var previews: some View {
        PokemonItem(pokemonName: "Pikachu", pokemonImageUrl: "https://pokeapi.co/api/v2/pokemon/25/")
    }
}
