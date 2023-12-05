package bridge.view;

import bridge.common.RetryHandler;
import bridge.domain.bridge.BridgeSize;
import bridge.domain.bridge.NextStatus;
import bridge.domain.move.MovePosition;
import camp.nextstep.edu.missionutils.Console;

public final class InputView {

    private static final String START_MSG = "다리 건너기 게임을 시작합니다.";
    private static final String INPUT_BRIDGE_SIZE_MSG = "\n다리의 길이를 입력해주세요.";
    private static final String INPUT_NEXT_MOVE_POSITION_MSG = "\n이동할 칸을 선택해주세요. (위: U, 아래: D)"; // TODO
    private static final String INPUT_NEXT_GAME_STATUS_MSG = "\n게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)";

    public static BridgeSize readBridgeSize() {
        return RetryHandler.handleRetry(InputView::_inputBridgeSize);
    }

    public static String readMoving() {
        return RetryHandler.handleRetry(InputView::_inputNextMovePosition);
    }

    public static NextStatus readGameCommand() {
        return RetryHandler.handleRetry(InputView::_inputNextGameStatus);
    }

    private static String _inputNextMovePosition() {
        System.out.println(INPUT_NEXT_MOVE_POSITION_MSG);
        final String input = readLine();
        return MovePosition.from(input);
    }

    private static NextStatus _inputNextGameStatus() {
        System.out.println(INPUT_NEXT_GAME_STATUS_MSG);
        final String input = readLine();
        return NextStatus.from(input);
    }


    private static BridgeSize _inputBridgeSize() {
        System.out.println(START_MSG);
        System.out.println(INPUT_BRIDGE_SIZE_MSG);
        final String input = readLine();
        final int bridgeSize = InputConverter.convertToInt(input);
        return new BridgeSize(bridgeSize);
    }

    private static String readLine() {
        return Console.readLine().trim();
    }

}
