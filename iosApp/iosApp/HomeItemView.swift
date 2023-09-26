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
        case let .repo(data):
            Text(data.repo.fullName).padding(EdgeInsets(top: 8, leading: 16, bottom: 8, trailing: 16))
        case .networkError:
            Text(NSLocalizedString("network error", comment: "network error"))
                .padding(EdgeInsets(top: 8, leading: 16, bottom: 8, trailing: 16))
        case let .serverError(data):
            let format = NSLocalizedString("server error", comment: "network error")
            Text(String.localizedStringWithFormat(format, data.statusCode))
                .padding(EdgeInsets(top: 8, leading: 16, bottom: 8, trailing: 16))
        }
    }
}
