package bridge.domain.bridge;

public enum GameCommand {
    REPLAY("R"),
    QUIT("Q");

    private final String value;

    GameCommand(final String value) {
        this.value = value;
    }


    public static GameCommand from(final String input) {
        if ("R".equals(input)) {
            return REPLAY;
        }
        if ("Q".equals(input)) {
            return QUIT;
        }

        throw new IllegalArgumentException("유효하지 않은 입력입니다. `R`, `Q` 중 하나를 입력해 주세요.");
    }

    public boolean isReplay() {
        return this == REPLAY;
    }
}
