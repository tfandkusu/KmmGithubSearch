import kgsios
import SwiftUI

struct HomeItemView: View {
    let item: HomeState.Item

    var body: some View {
        switch onEnum(of: item) {
        case .progress:
            HStack(alignment: .center) {
                ProgressView().padding(EdgeInsets(top: 8, leading: 16, bottom: 8, trailing: 16))
            }.frame(maxWidth: .infinity)
        case let .repo(item):
            Text(item.repo.fullName).padding(EdgeInsets(top: 8, leading: 16, bottom: 8, trailing: 16))
        case .networkError:
            Text("ネットワークエラー").padding(EdgeInsets(top: 8, leading: 16, bottom: 8, trailing: 16))
        case .serverError:
            Text("サーバーエラー").padding(EdgeInsets(top: 8, leading: 16, bottom: 8, trailing: 16))
        }
    }
}
