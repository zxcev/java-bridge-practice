package bridge.view;


import bridge.controller.dto.BridgeGameResultDto;
import bridge.controller.dto.BridgeSquaresDto;
import java.util.List;

public final class OutputView {

    public static void printResult(final BridgeGameResultDto bridgeGameResultDto) {
        System.out.println("최종 게임 결과");
        printSquares(bridgeGameResultDto.squares());
        System.out.printf("게임 성공 여부: %s\n", renderIsClear(bridgeGameResultDto.isClear()));
        System.out.printf("총 시도한 횟수: %d\n", bridgeGameResultDto.attemptedCount());
    }

    public static void printSquares(final BridgeSquaresDto bridgeSquaresDto) {
        System.out.println(renderMoveSquares(bridgeSquaresDto.upsideSquares()));
        System.out.println(renderMoveSquares(bridgeSquaresDto.downsideSquares()));
        System.out.println();
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
