import SwiftUI
import kgsios

struct HomeView: View {
    @StateObject var viewModel = HomeViewModel()
    
    @State private var searchText = ""

    var body: some View {
        VStack() {
            TextField("Search", text: $searchText, onCommit: {
                viewModel.search(keyword: searchText)
            })
            .textFieldStyle(RoundedBorderTextFieldStyle())
            .keyboardType(.webSearch)
            .padding(.horizontal, 8)
            Text(String(format: "検索結果は%d件", viewModel.state.items.count)).font(.body).padding(8)
            Spacer()
        }.navigationBarTitle("GitHubリポジトリ検索").onAppear(perform: {
            let useCase = TryUseCase()
            useCase.execute { (result, error) in
                if let nonOpticalResult = result {
                    print("result = ", nonOpticalResult.intValue)
                }
                if let nonOpticalError = error {
                    print("error = ", type(of: nonOpticalError))
                    let nsError = nonOpticalError as NSError
                    let kotlinException = nsError.userInfo["KotlinException"]
                    if let nonOpticalKotlinException = kotlinException {
                        print("KotlinException = ", type(of: nonOpticalKotlinException))
                    }
                }
            }
        });
    }
}
