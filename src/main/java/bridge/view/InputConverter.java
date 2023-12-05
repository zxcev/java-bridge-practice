package bridge.view;

import java.util.Arrays;
import java.util.List;

public class InputConverter {

    private static final String COMMA_DELIMETER = ",";

    public static int convertToInt(final String input) {
        InputValidator.validateNumberInput(input);
        return Integer.parseInt(input);
    }

    // "abc,def,ere,grg"
    // -> List["abc","def","ere","grg"]
    public static List<String> separateToStringList(final String input) {
        InputValidator.validateNumberInput(input);

        return Arrays.stream(input.split(COMMA_DELIMETER))
                .toList();
    }

    // "1,2,3,4,5" -> [1,2,3,4,5]
    public static List<Integer> separateToIntList(final String input) {
        // TODO -> 숫자로 이루어진 패턴인지 정규표현식으로 확인
        // 예를 들어 1,2,3,4 가아니라 1,x,e,3 등의 문자가 섞일 수도 있음.
        InputValidator.validateXXXPatternInput(input);

        return Arrays.stream(input.split(COMMA_DELIMETER))
                .map(Integer::parseInt)
                .toList();
    }

    // "ac-1,bc-2,cd-3" -> Pair{ "ac", 1 }

    // "[가,1500,20];[나,3000,20] -> Tuple{ "가', 1500, 20 }
}
