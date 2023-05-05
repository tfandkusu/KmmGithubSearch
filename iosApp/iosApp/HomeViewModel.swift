import Foundation
import kgsios

struct HomeState{
    var keyword: String = ""
}

class HomeViewModel: ObservableObject {
    
    private let helper = HomeViewModelHelper()
    
    @Published var state = HomeState()
    
    init() {

    }
    
    func search(keyword: String) {
        // state = HomeState(keyword: keyword)
    }
    
    deinit {
        NSLog("HomeViewModel deinit")
    }
}
