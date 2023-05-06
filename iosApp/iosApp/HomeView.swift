import SwiftUI

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
        });
    }
}
