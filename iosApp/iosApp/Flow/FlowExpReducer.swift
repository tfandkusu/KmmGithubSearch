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
                    class Collector: Kotlinx_coroutines_coreFlowCollector {
                        let sendTask: (Int) async -> Void

                        init(sendTask: @escaping (Int) async -> Void) {
                            self.sendTask = sendTask
                        }

                        // SKIE でメソッド名が変化している
                        func __emit(value: Any?) async throws {
                            if let intValue = value as! Int? {
                                await self.sendTask(intValue)
                            }
                        }
                    }
                    let collector = Collector(
                        sendTask: { intValue in
                            print("sendTask intVlaue = \(intValue)")
                            await send(.updateCount(count: intValue))
                        }
                    )
                    do {
                        // Global Functions 機能でクラス名の指定不要
                        try await getExampleFlow().collect(collector: collector)
                    } catch {
                        // 処理がキャンセルされると、CancellationError 例外が発生する
                        if error is CancellationError {
                            // キャンセルされたことが分かるように標準出力する
                            print("CancellationError")
                        }
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
