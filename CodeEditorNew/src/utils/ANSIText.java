package utils;

public class ANSIText {

    private static final String RESET = "\u001B[0m";
    private static final String BLACK = "\u001B[30m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";
    private static final String WHITE = "\u001B[37m";

    private static final String BOLD = "\u001B[1m";
    private static final String UNDERLINE = "\u001B[4m";

    public static String colorText(String text, String colorCode) {
        return colorCode + text + RESET;
    }

    public static String black(String text) {
        return colorText(text, BLACK);
    }

    public static String red(String text) {
        return colorText(text, RED);
    }

    public static String green(String text) {
        return colorText(text, GREEN);
    }

    public static String yellow(String text) {
        return colorText(text, YELLOW);
    }

    public static String blue(String text) {
        return colorText(text, BLUE);
    }

    public static String purple(String text) {
        return colorText(text, PURPLE);
    }

    public static String cyan(String text) {
        return colorText(text, CYAN);
    }

    public static String white(String text) {
        return colorText(text, WHITE);
    }

    public static String bold(String text) {
        return BOLD + text + RESET;
    }

    public static String underline(String text) {
        return UNDERLINE + text + RESET;
    }

    public static String boldColor(String text, String colorCode) {
        return BOLD + colorCode + text + RESET;
    }

    public static String underlineColor(String text, String colorCode) {
        return UNDERLINE + colorCode + text + RESET;
    }

    public static String styledText(String text, boolean isBold, boolean isUnderline, String colorCode) {
        String styledText = text;
        if (isBold) {
            styledText = BOLD + styledText;
        }
        if (isUnderline) {
            styledText = UNDERLINE + styledText;
        }
        styledText = colorCode + styledText + RESET;
        return styledText;
    }
}
