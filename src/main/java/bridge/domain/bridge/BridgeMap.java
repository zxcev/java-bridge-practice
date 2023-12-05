package bridge.domain.bridge;

import bridge.controller.dto.BridgeSquaresDto;
import bridge.domain.move.MovePosition;
import bridge.domain.move.MoveSquare;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class BridgeMap {
    private final List<String> bridge;
    private final List<MoveSquare> upside = new ArrayList<>();
    private final List<MoveSquare> downside = new ArrayList<>();
    private int currentPosition = 0;
    private boolean wentWrongPosition = false;

    public BridgeMap(final List<String> bridge) {
        this.bridge = bridge;
    }

    public boolean reachedToEnd() {
        return currentPosition == getSize();
    }

    // movePosition, moveSquare
    public void recordNextMovePosition(final String nextMovePosition) {
        final MoveSquare moveSquare = nextMoveSquare(nextMovePosition);

        if (MovePosition.U.equals(nextMovePosition)) {
            upside.add(moveSquare);
            downside.add(MoveSquare.NONE);
        }

        if (MovePosition.D.equals(nextMovePosition)) {
            upside.add(MoveSquare.NONE);
            downside.add(moveSquare);
        }
    }

    private MoveSquare nextMoveSquare(final String nextMovePosition) {
        if (bridge.get(currentPosition++).equals(nextMovePosition)) {
            return MoveSquare.O;
        }
        wentWrongPosition = true;
        return MoveSquare.X;
    }

    public int getSize() {
        return bridge.size();
    }

    public boolean isWentWrongPosition() {
        return wentWrongPosition;
    }

    public BridgeMap initialized() {
        return new BridgeMap(bridge);
    }

    public BridgeSquaresDto toBridgeSquaresDto() {
        return new BridgeSquaresDto(
                toStringSquares(upside),
                toStringSquares(downside)
        );
    }


    private List<String> toStringSquares(final List<MoveSquare> moveSquares) {
        return moveSquares.stream()
                .map(MoveSquare::getPattern)
                .collect(Collectors.toList());
    }
}