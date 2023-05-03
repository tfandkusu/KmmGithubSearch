import SwiftUI
import common
import remote

struct ContentView: View {
    @ObservedObject var viewModel = HomeViewModel()
    
    @State private var searchText = ""
        
	var body: some View {
        VStack() {
            Text("GitHubリポジトリ検索").font(.body).padding(8)
            TextField("Search", text: $searchText, onCommit: {
                viewModel.search(keyword: searchText)
            })
            .textFieldStyle(RoundedBorderTextFieldStyle())
            .keyboardType(.webSearch)
            .padding(.horizontal, 8)
            Text("検索キーワードは" + viewModel.state.keyword).font(.body).padding(8)
            Spacer()
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
