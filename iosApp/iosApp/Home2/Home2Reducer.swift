import ComposableArchitecture
import Foundation
import kgsios

struct Home2Reducer: ComposableArchitecture.Reducer {
    @Dependency(\.useCaseHelper) var useCaseHelper

    private enum CancelID { case search }

    func reduce(into state: inout State, action: Action) -> ComposableArchitecture.Effect<Action> {
        switch action {
        case let .inputKeyword(keyword):
            state.keyword = keyword
            return .none
        case .searchKeyword:
            let keyword = state.keyword
            state.repos = []
            state.progress = true
            return .run { send in
                do {
                    let repos = try await useCaseHelper.searchGithub(keyword)
                    await send(.searchSuccess(repos: repos))
                } catch let error as NSError {
                    switch error.kotlinException {
                    case let myError as MyError:
                        switch onEnum(of: myError) {
                        case .network:
                            await send(.networkError)
                        case let .server(data):
                            await send(.serverError(Int(data.statusCode)))
                        }
                    default:
                        await send(.unknownError)
                    }
                }
            }.cancellable(id: CancelID.search)
        case let .searchSuccess(repos):
            state.progress = false
            state.repos = repos
            return .none
        case .alertDismissed:
            state.alert = .none
            return .none
        case .networkError:
            state.progress = false
            state.alert = .init(title: .init("エラー"), message: .init("ネットワークエラー"))
            return .none
        case let .serverError(statusCode):
            state.alert = .init(title: .init("エラー"), message: .init("サーバエラー: \(statusCode)"))
            return .none
        case .unknownError:
            state.alert = .init(title: .init("エラー"), message: .init("未知のエラー"))
            return .none
        case .onDisappear:
            state = State()
            return .cancel(id: CancelID.search)
        case .alert:
            return .none
        }
    }

    struct State: Equatable {
        var keyword = ""
        var progress = false
        var repos: [GithubRepo] = []
        @PresentationState var alert: AlertState<Action.Alert>? = .none
    }

    enum Action: Equatable {
        case inputKeyword(String)
        case searchKeyword
        case alertDismissed
        case searchSuccess(repos: [GithubRepo])
        case networkError
        case serverError(Int)
        case unknownError
        case onDisappear
        case alert(PresentationAction<Alert>)
        enum Alert: Equatable {}
    }
}
