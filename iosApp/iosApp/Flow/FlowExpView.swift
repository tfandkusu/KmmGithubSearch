import ComposableArchitecture
import SwiftUI

struct FlowExpView: View {
    let store: StoreOf<FlowExpReducer>

    var body: some View {
        WithViewStore(self.store, observe: { $0 }) { viewStore in
            VStack {
                Text("Count = \(viewStore.count)").padding(8)
                Button(
                    action: {
                        viewStore.send(.countUp)
                    }
                ) {
                    Text("Count Up")
                }.padding(8)
                Spacer()
            }.navigationBarTitle("Flow 動作確認")
                .task {
                    await viewStore.send(.task).finish()
                }
        }
    }
}
