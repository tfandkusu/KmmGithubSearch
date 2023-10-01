import ComposableArchitecture
import SwiftUI

struct Home2View: View {
    let store: StoreOf<Home2Reducer>

    var body: some View {
        WithViewStore(self.store, observe: { $0 }) { viewStore in
            VStack(alignment: .leading) {
                TextField("Search", text: viewStore.binding(get: \.keyword, send: { .inputKeyword($0) }),
                          onCommit: {
                              viewStore.send(.searchKeyword)
                          })
                          .textFieldStyle(RoundedBorderTextFieldStyle())
                          .keyboardType(.webSearch)
                          .padding(.horizontal, 8)
                if viewStore.progress {
                    HStack(alignment: .center) {
                        ProgressView().padding(EdgeInsets(top: 8, leading: 16, bottom: 8, trailing: 16))
                    }.frame(maxWidth: .infinity)
                    Spacer()
                } else {
                    ScrollView {
                        LazyVStack(alignment: .leading) {
                            ForEach(viewStore.repos, id: \.self) { repo in
                                Text(repo.fullName).padding(EdgeInsets(top: 8, leading: 16, bottom: 8, trailing: 16))
                            }
                        }
                    }
                }
            }
            .alert(isPresented: viewStore.binding(get: \.networkError, send: .alertDismissed)) {
                Alert(
                    title: Text("エラー"),
                    message: Text("ネットワークエラー")
                )
            }
            .alert(isPresented: viewStore.binding(get: { $0.serverError != 0 }, send: .alertDismissed)) {
                Alert(
                    title: Text("エラー"),
                    message: Text("サーバーエラー: ステータスコード = \(viewStore.serverError)")
                )
            }
            .onDisappear {
                viewStore.send(.onDisappear)
            }
            .navigationBarTitle("GitHubリポジトリ検索")
        }
    }
}
