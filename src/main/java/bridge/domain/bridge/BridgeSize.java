package bridge.domain.bridge;

public record BridgeSize(
        int value
) {

    private static final int MIN_LEN = 3;
    private static final int MAX_LEN = 20;


    public BridgeSize {
        validateBridgeLen(value);
    }

    private static void validateBridgeLen(final int value) {
        if (value < MIN_LEN || value > MAX_LEN) {
            throw new IllegalArgumentException("다리 길이는 3부터 20 사이의 숫자여야 합니다.");
        }
    }
}
