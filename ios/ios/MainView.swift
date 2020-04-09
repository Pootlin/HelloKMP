//
//  MainView.swift
//  ios
//
//  Created by Eduardo Pool on 09/04/20.
//  Copyright © 2020 Eduardo Pool. All rights reserved.
//

import SwiftUI

struct MainView: View {

    @ObservedObject var bridge = MainViewModelBridge()

    var body: some View {
        NavigationView {
            ZStack(alignment: .center) {
                if self.bridge.viewState.isSuccess {
                    Text(self.bridge.viewState.username)
                }
                if self.bridge.viewState.isLoading {
                    ActivityIndicator(style: .large)
                }
            }.navigationBarTitle("Hello KMP")
            .alert(isPresented: .constant(self.bridge.viewState.hasError)) {
                Alert(
                    title: Text("There was an error"),
                    message: Text(self.bridge.viewState.errorMessage),
                    dismissButton: .default(Text("Retry"), action: { self.bridge.viewModel.retry() })
                )
            }
        }.onAppear {
            self.bridge.viewModel.getUser(username: "epool")
        }.onDisappear {
            self.bridge.viewModel.onCleared()
        }
    }
}

struct MainView_Previews: PreviewProvider {
    static var previews: some View {
        MainView()
    }
}
