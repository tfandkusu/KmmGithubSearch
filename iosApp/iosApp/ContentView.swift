import SwiftUI
import common
import remote

struct ContentView: View {
    @ObservedObject var viewModel = HomeViewModel()
        
	var body: some View {
        VStack() {
            Text("GitHubリポジトリ検索").font(.body).padding(16)
            Spacer()
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
