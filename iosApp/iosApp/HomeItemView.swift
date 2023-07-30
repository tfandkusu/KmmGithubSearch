import SwiftUI
import kgsios

struct HomeItemView : View {
    
    let item: HomeState.Item
    
    var body: some View {
        switch item {
        case is HomeState.ItemProgress:
            Text("読み込み中").padding(EdgeInsets(top: 8, leading: 16, bottom: 8, trailing: 16))
        case is HomeState.ItemNetworkError:
            Text("ネットワークエラー").padding(EdgeInsets(top: 8, leading: 16, bottom: 8, trailing: 16))
        case is HomeState.ItemServerError:
            Text("サーバーエラー").padding(EdgeInsets(top: 8, leading: 16, bottom: 8, trailing: 16))
        case let item as HomeState.ItemRepo:
            Text(item.repo.fullName).padding(EdgeInsets(top: 8, leading: 16, bottom: 8, trailing: 16))
        default:
            Text("これは表示されない").padding(EdgeInsets(top: 8, leading: 16, bottom: 8, trailing: 16))
        }
    }
}
