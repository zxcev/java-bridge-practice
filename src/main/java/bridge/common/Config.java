package bridge.common;

import bridge.controller.GameController;
import bridge.domain.bridge.BridgeMaker;
import bridge.domain.bridge.BridgeRandomNumberGenerator;

public final class Config {
    final BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
    final GameController gameController = new GameController(bridgeMaker);

    public BridgeMaker getBridgeMaker() {
        return bridgeMaker;
    }

    public GameController getGameController() {
        return gameController;
    }
}
