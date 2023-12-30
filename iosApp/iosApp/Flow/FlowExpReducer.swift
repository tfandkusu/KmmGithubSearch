import ComposableArchitecture
import kgsios

@Reducer
struct FlowExpReducer {
    struct State: Equatable {
        var count = 0
    }

    enum Action {
        case task
        // Count Up ボタンが押されたときに呼ばれる
        case countUp
        // 表示カウントを更新する
        case updateCount(count: Int)
    }

    var body: some ComposableArchitecture.Reducer<State, Action> {
        Reduce { state, action in
            switch action {
            case .task:
                return .run { send in
                    for await value in getExampleFlow() {
                        await send(.updateCount(count: Int(truncating: value)))
                    }
                }
            case .countUp:
                countUpExampleFlowValue()
                return .none
            case let .updateCount(count: count):
                state.count = count
                return .none
            }
        }
    }
}
