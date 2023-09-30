import ComposableArchitecture
import SwiftUI

struct Home2View: View {
    let store: StoreOf<Home2Reducer>

    var body: some View {
        WithViewStore(self.store, observe: { $0 }) { viewStore in
            VStack {
                TextField("Search", text: viewStore.binding(get: \.keyword, send: { .inputKeyword($0) }),
                          onCommit: {
                              viewStore.send(.searchKeyword)
                          })
                          .textFieldStyle(RoundedBorderTextFieldStyle())
                          .keyboardType(.webSearch)
                          .padding(.horizontal, 8)
                ScrollView {
                    LazyVStack(alignment: .leading) {
                        ForEach(viewStore.repos, id: \.self) { repo in
                            Text(repo.fullName).padding(EdgeInsets(top: 8, leading: 16, bottom: 8, trailing: 16))
                        }
                    }
                }
            }.navigationBarTitle("suspend 関数検証")
        }
    }
}

// struct Home2View_Previews: PreviewProvider {
//    static var previews: some View {
//        Home2View()
//    }
// }
