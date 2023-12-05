package bridge.domain.move;

public enum MoveSquare {
    O("O"),
    X("X"),
    NONE(" ");

    private final String pattern;

    MoveSquare(final String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }

}
