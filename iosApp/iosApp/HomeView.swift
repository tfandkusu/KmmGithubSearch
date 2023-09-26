import kgsios
import KMPNativeCoroutinesAsync
import SwiftUI

struct HomeView: View {
    @StateObject var viewModel = HomeViewModel()

    @State private var searchText = ""

    var body: some View {
        VStack {
            TextField("Search", text: $searchText, onCommit: {
                viewModel.search(keyword: searchText)
            })
            .textFieldStyle(RoundedBorderTextFieldStyle())
            .keyboardType(.webSearch)
            .padding(.horizontal, 8)
            ScrollView {
                LazyVStack(alignment: .leading) {
                    ForEach(viewModel.state.items, id: \.self) { item in
                        HomeItemView(item: item)
                    }
                }
            }
        }.navigationBarTitle("GitHubリポジトリ検索").onAppear(perform: {
            // KMP-NativeCoroutines を使い
            // Swift から Kotlin の suspend 関数を呼ぶ実験
            Task {
                let useCase = ExampleUseCase()
                do {
                    let result = try await asyncFunction(for: useCase.execute())
                    print("result = \(result)")
                } catch {
                    // Kotlin の例外オブジェクトを取得する
                    let nsError = error as NSError
                    let kotlinException = nsError.userInfo["KotlinException"]
                    if let nonOpticalKotlinException = kotlinException {
                        if nonOpticalKotlinException is ExampleException {
                            let exampleException = nonOpticalKotlinException as! ExampleException
                            let code = exampleException.code
                            print("code = \(code)")
                        }
                    }
                }
            }
        })
    }
}
