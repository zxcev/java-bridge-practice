package bridge.view;


import bridge.controller.dto.BridgeGameResultDto;
import bridge.controller.dto.BridgeSquaresDto;
import java.util.List;

public final class OutputView {
    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public static void printMap(final BridgeSquaresDto bridgeSquaresDto) {
        System.out.println(renderMoveSquares(bridgeSquaresDto.upsideSquares()));
        System.out.println(renderMoveSquares(bridgeSquaresDto.downsideSquares()));
        System.out.println();
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */

    public static void printResult(final BridgeGameResultDto bridgeGameResultDto) {
        System.out.println("최종 게임 결과");
        printMap(bridgeGameResultDto.squares());
        System.out.printf("게임 성공 여부: %s\n", renderIsClear(bridgeGameResultDto.isClear()));
        System.out.printf("총 시도한 횟수: %d\n", bridgeGameResultDto.attemptedCount());
    }

    private static String renderIsClear(final boolean isClear) {
        if (isClear) {
            return "성공";
        }
        return "실패";
    }

    private static String renderMoveSquares(final List<String> bridgeSquares) {
        return String.format("[ %s ]",
                String.join(
                        " | ",
                        bridgeSquares
                )
        );
    }


}
