package bridge.view;

import java.util.regex.Pattern;

public class InputValidator {

    public static final String NUMBER_ERR_MSG = ""; // TODO: 메세지 채우기
    public static final String PATTERN_NOT_MATCHED_ERR_MSG = ""; // TODO: 메세지 채우기

    private static final String COMMAS_SEPARATED_REGEX = "^[A-zㄱ-힣]+(,[A-zㄱ-힣]+)*$";

    // 자동차 - pobi,woni,jun - ^[A-zㄱ-힣]+(,[A-zㄱ-힣]+)*$
    // 로또 - 1,2,3,4,5,6
    // 자판기 - [콜라,1500,20];[사이다,1000,10] "^\[[A-zㄱ-힣]+,\d+,\d+]$"
    // 크리스마스 - 타파스-1,제로콜라-1
    // 페어매칭(4기 시험) - "프론트엔드, 레벨1, 자동차경주"
    // 메뉴 추천(5기 시험) - 토미,제임스,포코

    // https://regex101.com/
    // ^[a-zA-Z가-힣]+(,[a-zA-Z가-힣]+)*$

    // TODO: 전 기수에 나왔던 패턴 전부 정규표현식 아래에 만들어 놓고, 시험에 나오는거 or 가장 비슷한거 골라서 사용, 나머진 지우고요~
    private static final String XXX_REGEX = "^[a-zA-Z가-힣]+(,[a-zA-Z가-힣]+)*$"; // TODO: 전 기수 문제들에 나왔던 패턴들 싹 써놓기.

    // TODO: 모든 미션 다뒤져서 정규표현식 모든 케이스 다만들고, 그거 REGEX 101 사이트에서 다 검증해보고,
    // 자바 테스트 코드 다 짜고 검증 해놓기.

    // ^\\[[가-힣]:\\d\\]$
    // [잡채:5000],[스파게리:10000]

    // 자바에서 \\는 이스케이프문자라, \ 문자 자체를 나타내려면 \\ 두번 써야함.
    // 그래서 정규 표현식 사이트에서는
    private static final String NUMBER_XXX_REGEX = "^[\\d]+(,[\\d]+)*$"; // TODO: 전 기수 문제들에 나왔던 패턴들 싹 써놓기.
//    private static final String XXX_REGEX = "^[a-zA-Z가-힣]+($DELIMITER$[a-zA-Z가-힣]+)*$"; // TODO: 전 기수 문제들에 나왔던 패턴들 싹 써놓기.

    public static void validateNumberInput(final String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            // TODO: 메세지 이쁘게
            throw new IllegalArgumentException(NUMBER_ERR_MSG);
        }
    }

    public static void validateCommasSeparatedPatternInput(final String input) {
        if (!Pattern.matches(COMMAS_SEPARATED_REGEX, input)) {
            throw new IllegalArgumentException(PATTERN_NOT_MATCHED_ERR_MSG);
        }
    }

    public static void validateXXXPatternInput(final String input) {
        if (!Pattern.matches(XXX_REGEX, input)) {
            // TODO: 메세지 이쁘게
            throw new IllegalArgumentException(PATTERN_NOT_MATCHED_ERR_MSG);
        }
    }
}
