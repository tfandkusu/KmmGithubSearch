import ComposableArchitecture
import kgsios

struct UseCaseHelper {
    var searchGithub: (String) async throws -> KgsResultSealed<GithubRepoList>
}

extension UseCaseHelper: DependencyKey {
    static let liveValue = Self(
        searchGithub: { keyword in
            try await IosUseCaseHelper().searchGithub(keyword: keyword)
        }
    )
}

extension DependencyValues {
    var useCaseHelper: UseCaseHelper {
        get { self[UseCaseHelper.self] }
        set { self[UseCaseHelper.self] = newValue }
    }
}
