package bridge;

import bridge.common.Config;
import bridge.controller.GameController;

public final class Application {

    public static void main(final String[] args) {
        final Config config = new Config();

        final GameController gameController = config.getGameController();
        gameController.run();
    }
}
