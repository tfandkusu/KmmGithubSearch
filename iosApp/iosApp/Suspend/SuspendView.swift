import ComposableArchitecture
import SwiftUI

struct SuspendView: View {
    let store: StoreOf<SuspendReducer>

    var body: some View {
        WithViewStore(self.store, observe: { $0 }) { viewStore in
            VStack {
                HStack {
                    Button(
                        action: {
                            viewStore.send(.callFunction)
                        }
                    ) {
                        Text("関数呼び出し")
                    }.padding(16)
                    Button(
                        action: {
                            viewStore.send(.cancelFunction)
                        }
                    ) {
                        Text("関数キャンセル")
                    }.padding(16)
                }
                switch viewStore.status {
                case .initial:
                    EmptyView()
                case .running:
                    Text("suspend 関数動作中")
                case .completed:
                    Text("suspend 関数完了")
                case .canceled:
                    Text("suspend 関数はキャンセルされた")
                }
                Spacer()
            }.navigationBarTitle("suspend 関数動作確認")
        }
    }
}
