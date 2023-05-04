import SwiftUI
import kgsios

@main
struct iOSApp: App {
    
    init() {
        InitKoinForIosKt.doInitKoinForIos()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
