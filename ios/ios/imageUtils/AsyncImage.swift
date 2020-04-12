//
//  AsyncImage.swift
//  ios
//
//  Created by Eduardo Pool on 11/04/20.
//  Copyright © 2020 Eduardo Pool. All rights reserved.
//

import SwiftUI

struct AsyncImage<Placeholder: View>: View {

    @ObservedObject private var loader: ImageLoader
    private let placeholder: Placeholder?
    private let configuration: (Image) -> Image

    init(url: URL, placeholder: Placeholder? = nil, configuration: @escaping (Image) -> Image = { $0 }) {
        self.loader = ImageLoader(url: url, cache: TemporaryImageCache())
        self.placeholder = placeholder
        self.configuration = configuration
    }

    var body: some View {
        image
            .onAppear(perform: loader.load)
            //.onDisappear(perform: loader.cancel)
    }

    private var image: some View {
        Group {
            if loader.image != nil {
                configuration(Image(uiImage: loader.image!))
            } else {
                placeholder
            }
        }
    }
}

struct AsyncImage_Previews: PreviewProvider {
    static var previews: some View {
        AsyncImage<EmptyView>(url: URL(string: "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png")!)
    }
}
