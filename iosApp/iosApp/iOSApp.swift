import SwiftUI
import kgsios

@main
struct iOSApp: App {
    
    init() {
        InitForIosKt.doInitForIos()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
