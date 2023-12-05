package bridge.view;

public final class InputValidator {

    public static final String NUMBER_ERR_MSG = "반드시 숫자를 입력해야 합니다.";

    public static void validateNumberInput(final String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(NUMBER_ERR_MSG);
        }
    }

}
