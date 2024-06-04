package utils;
import java.awt.Color;

public class Theme {
    public enum ThemeType {
        LIGHT, DARK
    }

    private static ThemeType currentTheme = ThemeType.DARK;

    public static void setTheme(ThemeType theme) {
        currentTheme = theme;
    }

    public static Color getBackgroundColor() {
        switch (currentTheme) {
            case LIGHT:
                return new Color(200, 200, 200);
            case DARK:
                return new Color(24, 24, 24);
            default:
                return Color.GRAY;
        }
    }

    public static Color getButtonBorderColor() {
        switch (currentTheme) {
            case LIGHT:
                return new Color(150, 150, 150);
            case DARK:
                return new Color(69, 69, 69);
            default:
                return Color.GRAY;
        }
    }

    public static Color getSeparatorLineColor() {
        switch (currentTheme) {
            case LIGHT:
                return new Color(150, 150, 150);
            case DARK:
                return new Color(69, 69, 69);
            default:
                return Color.GRAY;
        }
    }

    public static Color getTitleColor() {
        switch (currentTheme) {
            case LIGHT:
                return Color.BLACK;
            case DARK:
                return Color.WHITE;
            default:
                return Color.BLACK;
        }
    }
}
