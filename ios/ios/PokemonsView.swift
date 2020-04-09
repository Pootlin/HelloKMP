//
//  PokemonsView.swift
//  ios
//
//  Created by Eduardo Pool on 09/04/20.
//  Copyright © 2020 Eduardo Pool. All rights reserved.
//

import SwiftUI

struct PokemonsView: View {
    @ObservedObject var pokemonsBridge = PokemonsViewModelBridge()
    
    var body: some View {
        Text("Hello, World!")
    }
}

struct PokemonsView_Previews: PreviewProvider {
    static var previews: some View {
        PokemonsView()
    }
}
