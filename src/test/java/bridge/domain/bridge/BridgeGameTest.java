package bridge.domain.bridge;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import bridge.controller.dto.BridgeGameResultDto;
import bridge.controller.dto.BridgeSquaresDto;
import bridge.domain.move.MovePosition;
import bridge.domain.move.MoveSquare;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

final class BridgeGameTest {


    @DisplayName("성공 케이스")
    @Test
    void move_withMatchedMovePositions_shouldContainO() {
        // given
        final BridgeGame bridgeGame = new BridgeGame(List.of(
                MovePosition.U,
                MovePosition.U,
                MovePosition.D
        ));
        final List<String> nextMovePositions = List.of(
                MovePosition.U,
                MovePosition.U,
                MovePosition.D
        );

        // when
        bridgeGame.move(nextMovePositions.get(0));
        bridgeGame.move(nextMovePositions.get(1));
        bridgeGame.move(nextMovePositions.get(2));

        final BridgeGameResultDto bridgeGameDto = bridgeGame.toBridgeGameDto();
        final int attemptedCount = bridgeGameDto.attemptedCount();
        final BridgeSquaresDto squares = bridgeGameDto.squares();

        // then
        assertThat(bridgeGame.isClear()).isTrue();
        assertThat(bridgeGame.isEnded()).isTrue();
        assertThat(bridgeGame.isFailed()).isFalse();

        assertThat(bridgeGameDto.isClear()).isTrue();
        assertThat(attemptedCount).isEqualTo(1);
        assertThat(squares.upsideSquares().size())
                .isEqualTo(squares.downsideSquares().size())
                .isEqualTo(3);

        assertThat(squares.upsideSquares()).asList()
                .containsExactly(
                        MoveSquare.O.getPattern(),
                        MoveSquare.O.getPattern(),
                        MoveSquare.NONE.getPattern()
                );
        assertThat(squares.downsideSquares()).asList()
                .containsExactly(
                        MoveSquare.NONE.getPattern(),
                        MoveSquare.NONE.getPattern(),
                        MoveSquare.O.getPattern()
                );
    }

    @DisplayName("실패 케이스")
    @Test
    void move_withNoneMatchedMovePositions_shouldContainX() {
        // given
        final BridgeGame bridgeGame = new BridgeGame(List.of(
                MovePosition.U,
                MovePosition.U,
                MovePosition.D
        ));
        final List<String> nextMovePositions = List.of(
                MovePosition.U,
                MovePosition.U,
                MovePosition.U
        );

        // when
        bridgeGame.move(nextMovePositions.get(0));
        bridgeGame.move(nextMovePositions.get(1));
        bridgeGame.move(nextMovePositions.get(2));

        final BridgeGameResultDto bridgeGameDto = bridgeGame.toBridgeGameDto();
        final int attemptedCount = bridgeGameDto.attemptedCount();
        final BridgeSquaresDto squares = bridgeGameDto.squares();

        // then
        assertThat(bridgeGame.isClear()).isFalse();
        assertThat(bridgeGame.isEnded()).isTrue();
        assertThat(bridgeGame.isFailed()).isTrue();

        assertThat(bridgeGameDto.isClear()).isFalse();
        assertThat(attemptedCount).isEqualTo(1);
        assertThat(squares.upsideSquares().size())
                .isEqualTo(squares.downsideSquares().size())
                .isEqualTo(3);

        assertThat(squares.upsideSquares()).asList()
                .containsExactly(
                        MoveSquare.O.getPattern(),
                        MoveSquare.O.getPattern(),
                        MoveSquare.X.getPattern()
                );
        assertThat(squares.downsideSquares()).asList()
                .containsExactly(
                        MoveSquare.NONE.getPattern(),
                        MoveSquare.NONE.getPattern(),
                        MoveSquare.NONE.getPattern()
                );
    }

    @DisplayName("재시도 할 경우 재시도 횟수만큼 attemptedCount 증가됨")
    @Test
    void retry_shouldIncreaseAttemptedCount() {
        // given
        final BridgeGame bridgeGame = new BridgeGame(List.of(
                MovePosition.U,
                MovePosition.U,
                MovePosition.D
        ));

        // 3 times retried, so totally 4 times attempted
        bridgeGame.retry();
        bridgeGame.retry();
        bridgeGame.retry();

        // when
        final BridgeGameResultDto bridgeGameDto = bridgeGame.toBridgeGameDto();
        final int attemptedCount = bridgeGameDto.attemptedCount();

        // then
        assertThat(attemptedCount).isEqualTo(4);
    }
}