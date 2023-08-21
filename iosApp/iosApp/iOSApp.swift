import kgsios
import SwiftUI

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
