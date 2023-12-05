package bridge.domain.bridge;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import bridge.controller.dto.BridgeSquaresDto;
import bridge.domain.move.MovePosition;
import bridge.domain.move.MoveSquare;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

final class BridgeMapTest {

    @DisplayName("다리 길이가 3부터 20 사이의 범위라면 BridgeMap 생성")
    @ParameterizedTest
    @ValueSource(ints = {3, 20})
    void create_withValidBridgeLength_shouldBeOk(final int input) {
        // given
        // when
        final List<String> movePositions = new ArrayList<>();
        for (int i = 0; i < input; i++) {
            movePositions.add(MovePosition.D);
        }
        final BridgeMap bridgeMap = new BridgeMap(movePositions);
        final BridgeSquaresDto bridgeSquaresDto = bridgeMap.toBridgeSquaresDto();

        // then
        assertThat(bridgeMap.getSize()).isEqualTo(input);
        assertThat(bridgeMap.isWentWrongPosition()).isFalse();
        assertThat(bridgeMap.reachedToEnd()).isFalse();

        assertThat(bridgeSquaresDto.upsideSquares().size()).isEqualTo(0);
        assertThat(bridgeSquaresDto.downsideSquares().size()).isEqualTo(0);
    }

    @DisplayName("다리 길이가 3부터 20 사이의 범위가 아니라면 BridgeMap 생성 시 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = {2, 21})
    void create_withInvalidBridgeLength_shouldThrow(final int input) {
        // given
        // when
        final List<String> movePositions = new ArrayList<>();
        for (int i = 0; i < input; i++) {
            movePositions.add(MovePosition.D);
        }

        // then
        assertThatThrownBy(() ->
                new BridgeMap(movePositions))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("다리 길이는 3부터 20 사이의 숫자여야 합니다.");

    }

    @DisplayName("bridge와 일치하는 MovePosition만 입력한 경우 O를 포함")
    @Test
    void record_withMatchedMovePositions_shouldContainO() {
        // given
        // when
        final List<String> movePositions = List.of(
                MovePosition.U,
                MovePosition.U,
                MovePosition.D
        );
        final BridgeMap bridgeMap = new BridgeMap(movePositions);

        bridgeMap.recordNextMovePosition(MovePosition.U);
        bridgeMap.recordNextMovePosition(MovePosition.U);
        bridgeMap.recordNextMovePosition(MovePosition.D);

        final BridgeSquaresDto bridgeSquaresDto = bridgeMap.toBridgeSquaresDto();
        final List<String> upsideSquares = bridgeSquaresDto.upsideSquares();
        final List<String> downsideSquares = bridgeSquaresDto.downsideSquares();

        // then
        assertThat(upsideSquares.size()).isEqualTo(3);
        assertThat(downsideSquares.size()).isEqualTo(3);

        assertThat(upsideSquares.get(0)).isEqualTo(MoveSquare.O.getPattern());
        assertThat(upsideSquares.get(1)).isEqualTo(MoveSquare.O.getPattern());
        assertThat(upsideSquares.get(2)).isEqualTo(MoveSquare.NONE.getPattern());

        assertThat(downsideSquares.get(0)).isEqualTo(MoveSquare.NONE.getPattern());
        assertThat(downsideSquares.get(1)).isEqualTo(MoveSquare.NONE.getPattern());
        assertThat(downsideSquares.get(2)).isEqualTo(MoveSquare.O.getPattern());
    }

    @DisplayName("bridge와 일치하지 않는 MovePosition을 입력한 경우 X를 포함")
    @Test
    void record_withNotMatchedMovePositions_shouldContainX() {
        // given
        // when
        final List<String> movePositions = List.of(
                MovePosition.U,
                MovePosition.U,
                MovePosition.D
        );
        final BridgeMap bridgeMap = new BridgeMap(movePositions);

        bridgeMap.recordNextMovePosition(MovePosition.U);
        bridgeMap.recordNextMovePosition(MovePosition.U);
        bridgeMap.recordNextMovePosition(MovePosition.U);

        final BridgeSquaresDto bridgeSquaresDto = bridgeMap.toBridgeSquaresDto();
        final List<String> upsideSquares = bridgeSquaresDto.upsideSquares();
        final List<String> downsideSquares = bridgeSquaresDto.downsideSquares();

        // then
        assertThat(upsideSquares.size()).isEqualTo(3);
        assertThat(downsideSquares.size()).isEqualTo(3);

        assertThat(upsideSquares.get(0)).isEqualTo(MoveSquare.O.getPattern());
        assertThat(upsideSquares.get(1)).isEqualTo(MoveSquare.O.getPattern());
        assertThat(upsideSquares.get(2)).isEqualTo(MoveSquare.X.getPattern());

        assertThat(downsideSquares.get(0)).isEqualTo(MoveSquare.NONE.getPattern());
        assertThat(downsideSquares.get(1)).isEqualTo(MoveSquare.NONE.getPattern());
        assertThat(downsideSquares.get(2)).isEqualTo(MoveSquare.NONE.getPattern());
    }
}