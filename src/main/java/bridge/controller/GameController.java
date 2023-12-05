package bridge.controller;

import bridge.controller.dto.BridgeSquaresDto;
import bridge.domain.bridge.BridgeGame;
import bridge.domain.bridge.BridgeMaker;
import bridge.domain.bridge.BridgeSize;
import bridge.domain.bridge.NextStatus;
import bridge.view.InputView;
import bridge.view.OutputView;
import java.util.List;

public final class GameController {

    private final BridgeMaker bridgeMaker;

    public GameController(final BridgeMaker bridgeMaker) {
        this.bridgeMaker = bridgeMaker;
    }

    public void run() {
        final BridgeGame bridgeGame = createBridgeGame();
        play(bridgeGame);
        OutputView.printResult(bridgeGame.toBridgeGameDto());
    }

    private void play(final BridgeGame bridgeGame) {
        playRoundRecursive(bridgeGame);

        if (bridgeGame.isFailed()) {
            final NextStatus nextStatus = InputView.readGameCommand();

            if (nextStatus.willReplay()) {
                bridgeGame.retry();
                play(bridgeGame);
            }
        }
    }

    private void playRoundRecursive(final BridgeGame bridgeGame) {
        final String nextMovePosition = InputView.readMoving();
        bridgeGame.move(nextMovePosition);

        final BridgeSquaresDto bridgeSquaresDto = bridgeGame.toBridgeSquaresDto();
        OutputView.printMap(bridgeSquaresDto);

        if (!bridgeGame.isFailed() && !bridgeGame.isEnded()) {
            playRoundRecursive(bridgeGame);
        }
    }

    private BridgeGame createBridgeGame() {
        // 1. 다리 길이 입력
        final BridgeSize bridgeSize = InputView.readBridgeSize();

        // 2. 다리 생성
        final List<String> bridge = bridgeMaker.makeBridge(bridgeSize.value());

        // 3. 다리 게임 생성
        return new BridgeGame(bridge);
    }
}
