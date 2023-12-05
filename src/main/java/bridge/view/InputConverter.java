package bridge.view;

public final class InputConverter {

    public static int convertToInt(final String input) {
        InputValidator.validateNumberInput(input);
        return Integer.parseInt(input);
    }


}
