import SwiftUI
import common
import remote

struct ContentView: View {
    @ObservedObject var viewModel = HomeViewModel()
        
	var body: some View {
        Text(viewModel.state.greet)
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
