import FLEX
import SwiftUI

enum Route: Hashable {
    case home
}

struct ContentView: View {
    var body: some View {
        NavigationView {
            VStack {
                NavigationLink(
                    destination: HomeView(),
                    label: {
                        Text("ホーム画面を開く")
                    }
                ).padding(8)
                NavigationLink(
                    destination: UseKotlinUseCaseView(),
                    label: {
                        Text("suspend 関数検証")
                    }
                ).padding(8)
                Button(action: {
                    FLEXManager.shared.showExplorer()
                }) {
                    Text("FLEX 起動")
                }.padding(8)
                Spacer()
            }.navigationBarTitle("最初の画面")
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
