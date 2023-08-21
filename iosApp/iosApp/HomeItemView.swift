import kgsios
import SwiftUI

// 実験
// https://github.com/icerockdev/https://github.com/icerockdev/moko-kswift
// で自動生成した enum に変換する
enum HomeStateItemKs {
    case networkError
    case progress
    case repo(HomeState.ItemRepo)
    case serverError(HomeState.ItemServerError)

    public var sealed: HomeState.Item {
        switch self {
        case .networkError:
            return kgsios.HomeState.ItemNetworkError() as kgsios.HomeState.Item
        case .progress:
            return kgsios.HomeState.ItemProgress() as kgsios.HomeState.Item
        case let .repo(obj):
            return obj as kgsios.HomeState.Item
        case let .serverError(obj):
            return obj as kgsios.HomeState.Item
        }
    }

    public init(_ obj: HomeState.Item) {
        if obj is kgsios.HomeState.ItemNetworkError {
            self = .networkError
        } else if obj is kgsios.HomeState.ItemProgress {
            self = .progress
        } else if let obj = obj as? kgsios.HomeState.ItemRepo {
            self = .repo(obj)
        } else if let obj = obj as? kgsios.HomeState.ItemServerError {
            self = .serverError(obj)
        } else {
            fatalError("HomeStateItemKs not synchronized with HomeState.Item class")
        }
    }
}

struct HomeItemView: View {
    let item: HomeState.Item

    var body: some View {
        let enumItem = HomeStateItemKs(item)
        switch enumItem {
        case .progress:
            Text("読み込み中").padding(EdgeInsets(top: 8, leading: 16, bottom: 8, trailing: 16))
        case .networkError:
            Text("ネットワークエラー").padding(EdgeInsets(top: 8, leading: 16, bottom: 8, trailing: 16))
        case let .repo(item):
            Text(item.repo.fullName).padding(EdgeInsets(top: 8, leading: 16, bottom: 8, trailing: 16))
        case .serverError:
            Text("サーバーエラー").padding(EdgeInsets(top: 8, leading: 16, bottom: 8, trailing: 16))
        }
    }
}
