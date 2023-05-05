import Foundation
import kgsios

struct HomeState{
    var keyword: String = ""
}

class HomeViewModel: ObservableObject {
    
    private let helper = HomeViewModelHelper()
    
    @Published var state = HomeState()
    
    init() {
//        helper.setUp { [weak self] state in
//            self.state = state
//        }
    }
    
    func search(keyword: String) {
        // state = HomeState(keyword: keyword)
    }
    
    deinit {
        NSLog("HomeViewModel deinit")
    }
}
