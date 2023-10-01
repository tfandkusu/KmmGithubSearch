import ComposableArchitecture
import Foundation
import kgsios

struct SuspendReducer: ComposableArchitecture.Reducer {
    // キャンセル ID を定義
    private enum CancelID { case example }

    func reduce(into state: inout State, action: Action) -> ComposableArchitecture.Effect<Action> {
        // 呼ばれた action に対して現在の state を次の state へ更新したり、次の action を返却する。
        switch action {
        case .callFunction:
            state.status = .running
            // この処理のキャンセルは CancelID.example を指定することで行えるようにした
            return .run { send in
                do {
                    // Kotlin の suspend 関数呼び出し
                    try await ExampleKt.exampleFunction()
                    await send(.completeFunction)
                } catch {
                    // 処理がキャンセルされると、CancellationError 例外が発生する
                    if error is CancellationError {
                        // キャンセルされたことが分かるように標準出力する
                        print("CancellationError")
                    }
                }
            }.cancellable(id: CancelID.example)
        case .cancelFunction:
            // 処理のキャンセル
            state.status = .canceled
            return .cancel(id: CancelID.example)
        case .completeFunction:
            // 処理の完了
            state.status = .completed
            return .none
        }
    }

    enum Status: Equatable {
        case initial // 初期状態
        case running // suspend 関数動作中
        case canceled // suspend 関数がキャンセルされた
        case completed // suspend 関数が完了した
    }

    // 画面の状態
    struct State: Equatable {
        var status = Status.initial
    }

    // 画面の操作や非同期処理の結果
    enum Action: Equatable {
        case callFunction // suspend 関数を呼び出す
        case cancelFunction // suspend 関数をキャンセルする
        case completeFunction // suspend 関数が完了した
    }
}
