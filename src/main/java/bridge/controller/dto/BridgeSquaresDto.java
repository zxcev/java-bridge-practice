package bridge.controller.dto;

import java.util.List;

public record BridgeSquaresDto(
        List<String> upsideSquares,
        List<String> downsideSquares
) {
}
