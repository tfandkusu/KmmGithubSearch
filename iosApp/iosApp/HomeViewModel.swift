import Foundation

struct HomeState{
    var keyword: String = ""
}

class HomeViewModel: ObservableObject {
    @Published var state = HomeState()
    
    func search(keyword: String) {
        state = HomeState(keyword: keyword)
    }
    
    deinit {
        NSLog("HomeViewModel deinit")
    }
}
