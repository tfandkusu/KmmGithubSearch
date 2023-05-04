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
            Text("検索キーワードは" + viewModel.state.keyword).font(.body).padding(8)
            Spacer()
        }.navigationBarTitle("GitHubリポジトリ検索")
    }
}
