package bridge.domain.move;

public final class MovePosition {

    public static final String U = "U";
    public static final String D = "D";

    public static String from(final String input) {
        if ("U".equals(input)) {
            return U;
        }
        if ("D".equals(input)) {
            return D;
        }

        throw new IllegalArgumentException("이동할 위치는 반드시 `U`, `D`만 입력 가능합니다.");
    }

    public static String from(final int input) {
        if (input == 0) {
            return D;
        }
        if (input == 1) {
            return U;
        }

        throw new IllegalArgumentException("이동할 위치는 반드시 `0`, `1`만 입력 가능합니다.");
    }

}
