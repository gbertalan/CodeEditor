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

	public static Color getFrameButtonColor() {
		switch (currentTheme) {
		case LIGHT:
			return Color.BLACK;
		case DARK:
			return new Color(55, 55, 55);
		default:
			return Color.BLACK;
		}
	}

	public static Color getFrameButtonSymbolColor() {
		switch (currentTheme) {
		case LIGHT:
			return Color.BLACK;
		case DARK:
			return Color.WHITE;
		default:
			return Color.BLACK;
		}
	}

	public static Color getDEBUGColor() {
		return Color.RED;
	}

	public static Color getTitleBarColor() {
		switch (currentTheme) {
		case LIGHT:
			return Color.BLACK;
		case DARK:
			return new Color(24, 24, 38, 130);
		default:
			return Color.BLACK;
		}
	}

	public static Color getTitleTextColor() {
		switch (currentTheme) {
		case LIGHT:
			return Color.BLACK;
		case DARK:
			return Color.LIGHT_GRAY;
		default:
			return Color.BLACK;
		}
	}

	public static Color getSidePanelColor() {
		switch (currentTheme) {
		case LIGHT:
			return new Color(200, 200, 200, 100);
		case DARK:
			return new Color(24, 24, 24, 130);
		default:
			return Color.GRAY;
		}
	}
	
	public static Color getOpenedPanelColor() {
		switch (currentTheme) {
		case LIGHT:
			return new Color(200, 200, 200);
		case DARK:
			return new Color(44, 44, 44, 200);
		default:
			return Color.GRAY;
		}
	}
	
	public static Color getPanelHeaderTextColor() {
		switch (currentTheme) {
		case LIGHT:
			return new Color(200, 200, 200, 100);
		case DARK:
			return new Color(255, 255, 255);
		default:
			return Color.GRAY;
		}
	}
	
	public static Color getPanelTextColor() {
		switch (currentTheme) {
		case LIGHT:
			return new Color(200, 200, 200, 100);
		case DARK:
			return new Color(195, 195, 195);
		default:
			return Color.GRAY;
		}
	}
	
	public static Color getPanelButtonColor() {
		switch (currentTheme) {
		case LIGHT:
			return new Color(200, 200, 200, 100);
		case DARK:
			return new Color(15, 15, 15, 150);
		default:
			return Color.GRAY;
		}
	}
	
	public static Color getPanelButtonHoverColor() {
		switch (currentTheme) {
		case LIGHT:
			return new Color(200, 200, 200, 100);
		case DARK:
			return new Color(34, 34, 34, 150);
		default:
			return Color.GRAY;
		}
	}

	public static Color getFilePanelCoverColor() {
		switch (currentTheme) {
		case LIGHT:
			return new Color(200, 200, 200, 100);
		case DARK:
			return new Color(34, 34, 34);
//			return Color.RED;
		default:
			return Color.GRAY;
		}
	}
	
}
