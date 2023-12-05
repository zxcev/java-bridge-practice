package bridge.domain.bridge;

public enum NextStatus {
    REPLAY("R"),
    QUIT("Q");

    private final String value;

    NextStatus(final String value) {
        this.value = value;
    }


    public static NextStatus from(final String input) {
        if ("R".equals(input)) {
            return REPLAY;
        }
        if ("Q".equals(input)) {
            return QUIT;
        }

        throw new IllegalArgumentException("유효하지 않은 입력입니다. `R`, `Q` 중 하나를 입력해 주세요.");
    }

    public boolean willReplay() {
        return this == REPLAY;
    }
}
