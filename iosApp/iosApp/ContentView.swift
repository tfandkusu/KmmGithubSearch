import SwiftUI
import common
import remote

struct ContentView: View {
	let greet = Greeting().greet()
    
    let test = TestForIos()
        
	var body: some View {
        Text(greet).onAppear {
            test.callApi()
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
