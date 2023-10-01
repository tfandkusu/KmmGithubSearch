import ComposableArchitecture
import SwiftUI

struct SuspendView: View {
    let store: StoreOf<SuspendReducer>

    var body: some View {
        Text(/*@START_MENU_TOKEN@*/"Hello, World!"/*@END_MENU_TOKEN@*/)
            .navigationBarTitle("suspend 関数動作確認")
    }
}
