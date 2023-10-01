import ComposableArchitecture
import Foundation
import kgsios

struct SuspendReducer: ComposableArchitecture.Reducer {
    private enum CancelID { case example }

    func reduce(into state: inout State, action: Action) -> ComposableArchitecture.Effect<Action> {
        switch action {
        case .callFunction:
            state.running = true
            return .run { send in
                try await ExampleKt.exampleFunction()
                await send(.completeFunction)
            }
        case .cancelFunction:
            return .cancel(id: CancelID.example)
        case .completeFunction:
            state.running = false
            state.completed = true
            return .none
        }
    }

    struct State: Equatable {
        var running = false // suspend 関数動作中
        var canceled = false // suspend 関数がキャンセルされた
        var completed = false // suspend 関数が完了した
    }

    enum Action: Equatable {
        case callFunction
        case cancelFunction
        case completeFunction
    }
}
