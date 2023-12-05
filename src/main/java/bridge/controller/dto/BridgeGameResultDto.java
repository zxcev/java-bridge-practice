package bridge.controller.dto;

public record BridgeGameResultDto(
        int attemptedCount,
        boolean isClear,
        BridgeSquaresDto squares
) {
}
