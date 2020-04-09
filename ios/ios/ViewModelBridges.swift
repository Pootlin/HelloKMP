//
//  ObservableObjects.swift
//  ios
//
//  Created by Eduardo Pool on 06/04/20.
//  Copyright © 2020 Eduardo Pool. All rights reserved.
//

import HelloKMP

class BaseViewModelBridge<VM, T>: ObservableObject where VM: ViewStateViewModel<T> {

    let viewModel: VM

    @Published private(set) var viewState: T

    init(viewModel: VM) {
        self.viewState = viewModel.initialState
        self.viewModel = viewModel
        self.viewModel.viewState.observe { newViewState in
            if let viewState = newViewState {
                self.viewState = viewState
            }
        }
    }

}

class MainViewModelBridge: BaseViewModelBridge<MainViewModel, MainViewModel.ViewState> {

    init() {
        super.init(viewModel: MainViewModel())
    }

}

class PokemonsViewModelBridge: BaseViewModelBridge<PokemonsViewModel, PokemonsViewModel.ViewState> {

    init() {
        super.init(viewModel: PokemonsViewModel())
    }

}
