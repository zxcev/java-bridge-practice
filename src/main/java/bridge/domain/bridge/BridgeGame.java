package bridge.domain.bridge;

import bridge.controller.dto.BridgeGameResultDto;
import bridge.controller.dto.BridgeSquaresDto;
import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public final class BridgeGame {

    private int attemptedCount;
    private BridgeMap bridgeMap;

    public BridgeGame(final List<String> bridge) {
        this(1, new BridgeMap(bridge));
    }

    private BridgeGame(
            final int attemptedCount,
            final BridgeMap bridgeMap
    ) {
        this.attemptedCount = attemptedCount;
        this.bridgeMap = bridgeMap;
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void move(final String nextMovePosition) {
        bridgeMap.recordNextMovePosition(nextMovePosition);
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
        this.attemptedCount++;
        this.bridgeMap = bridgeMap.initialized();
    }

    public BridgeGameResultDto toBridgeGameDto() {
        return new BridgeGameResultDto(
                attemptedCount,
                isClear(),
                toBridgeSquaresDto()
        );
    }

    public BridgeSquaresDto toBridgeSquaresDto() {
        return bridgeMap.toBridgeSquaresDto();
    }


    public boolean isEnded() {
        return bridgeMap.reachedToEnd();
    }

    public boolean isClear() {
        return !isFailed() && isEnded();
    }

    public boolean isFailed() {
        return bridgeMap.isWentWrongPosition();
    }
}
