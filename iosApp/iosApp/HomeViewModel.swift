import Foundation
import kgsios

class HomeViewModel: ObservableObject {
    private let helper = HomeViewModelHelper()

    @Published var state = HomeState(keyword: "", items: [])

    init() {
        helper.setUp { [weak self] nextState in
            if let nextState = nextState {
                self?.state = nextState
            }
        }
    }

    func search(keyword: String) {
        helper.event(event: HomeEvent.SearchKeyword(keyword: keyword))
    }

    deinit {
        helper.clean()
    }
}
