import ComposableArchitecture
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
                let result = try await useCaseHelper.searchGithub(keyword)
                switch onEnum(of: result) {
                case let .success(data):
                    await send(.searchSuccess(repos: data.value!.repos))
                case let .failure(data):
                    switch onEnum(of: data.error) {
                    case .network:
                        await send(.searchNetworkError)
                    case let .server(data):
                        await send(.searchServerError(Int(data.statusCode)))
                    }
                }
            }.cancellable(id: CancelID.search)
        case let .searchSuccess(repos):
            state.progress = false
            state.repos = repos
            return .none
        case .alertDismissed:
            state.networkError = false
            state.serverError = 0
            return .none
        case .searchNetworkError:
            state.progress = false
            state.networkError = true
            return .none
        case let .searchServerError(statusCode):
            state.progress = false
            state.serverError = statusCode
            return .none
        case .onDisappear:
            state.keyword = ""
            state.progress = false
            state.repos = []
            return .cancel(id: CancelID.search)
        }
    }

    struct State: Equatable {
        var keyword = ""
        var progress = false
        var repos: [GithubRepo] = []
        var networkError = false
        var serverError: Int = 0
    }

    enum Action: Equatable {
        case inputKeyword(String)
        case searchKeyword
        case alertDismissed
        case searchSuccess(repos: [GithubRepo])
        case searchNetworkError
        case searchServerError(Int)
        case onDisappear
    }
}
