import Foundation

struct HomeState{
    let greet: String = "hello"
}

class HomeViewModel: ObservableObject {
    @Published var state = HomeState()
    
    
}
