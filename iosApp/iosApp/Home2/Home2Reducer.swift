import ComposableArchitecture
import kgsios

struct Home2Reducer: ComposableArchitecture.Reducer {
    @Dependency(\.useCaseHelper) var useCaseHelper

    func reduce(into state: inout State, action: Action) -> ComposableArchitecture.Effect<Action> {
        switch action {
        case let .inputKeyword(keyword):
            state.keyword = keyword
            return .none
        case let .searchKeyword(keyword):
            state.repos = []
            state.progress = true
            return .run { send in
                let repos = try await useCaseHelper.searchGithub(keyword)
                await send(.searchResponse(repos: repos))
            }
        case let .searchResponse(repos):
            state.repos = repos
            return .none
        case .alertDismissed:
            state.networkError = false
            state.serverError = .none
            return .none
        }
    }

    struct State: Equatable {
        var keyword = ""
        var progress = false
        var repos: [GithubRepo]
        var networkError = false
        var serverError: Int?
    }

    enum Action: Equatable {
        case inputKeyword(String)
        case searchKeyword(String)
        case searchResponse(repos: [GithubRepo])
        case alertDismissed
    }
}
