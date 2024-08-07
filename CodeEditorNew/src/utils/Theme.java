package utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

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
	
	public static Color getFrameButtonSymbolColorUnhovered() {
		switch (currentTheme) {
		case LIGHT:
			return Color.BLACK;
		case DARK:
			return Color.LIGHT_GRAY;
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
			return new Color(24, 24, 24, 100);
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
			return new Color(44, 44, 44);
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
		default:
			return Color.GRAY;
		}
	}
	
	public static Color getBoxHeaderBackgroundColor() {
		switch (currentTheme) {
		case LIGHT:
			return new Color(200, 200, 200, 100);
		case DARK:
			return new Color(34, 34, 54);
		default:
			return Color.GRAY;
		}
	}
	
	public static Color getBoxInnerBorderColor() {
		switch (currentTheme) {
		case LIGHT:
			return new Color(200, 200, 200, 100);
		case DARK:
			return new Color(134, 134, 134);
		default:
			return Color.GRAY;
		}
	}
	
	public static Color getBoxArrowBorderColor() {
		switch (currentTheme) {
		case LIGHT:
			return new Color(200, 200, 200, 100);
		case DARK:
			return new Color(84, 84, 84);
		default:
			return Color.GRAY;
		}
	}
	
	public static Color getBoxHeaderTextColor() {
		switch (currentTheme) {
		case LIGHT:
			return new Color(200, 200, 200, 100);
		case DARK:
			return new Color(180, 180, 180);
		default:
			return Color.GRAY;
		}
	}
	
	public static Font getBoxHeaderFont() {
		return new Font("Consolas", Font.BOLD, 18);
	}
	
	public static Color getBoxBackgroundColor() {
		switch (currentTheme) {
		case LIGHT:
			return new Color(200, 200, 200, 100);
		case DARK:
			return new Color(21, 21, 21, 100);
		default:
			return Color.GRAY;
		}
	}
	
	public static Color getLineNumberBackgroundColor() {
		switch (currentTheme) {
		case LIGHT:
			return new Color(200, 200, 200);
		case DARK:
			return new Color(21, 21, 21);
		default:
			return Color.GRAY;
		}
	}
	
	public static Color getFrameCloseButtonColor() {
		switch (currentTheme) {
		case LIGHT:
			return new Color(200, 200, 200, 100);
		case DARK:
			return new Color(232, 17, 35);
		default:
			return Color.GRAY;
		}
	}
	
	public static Color getBoxCloseButtonColor() {
		switch (currentTheme) {
		case LIGHT:
			return new Color(200, 200, 200, 100);
		case DARK:
			return new Color(232, 232, 35);
		default:
			return Color.GRAY;
		}
	}
	
	public static Color getCloseButtonSymbolColor() {
		switch (currentTheme) {
		case LIGHT:
			return Color.BLACK;
		case DARK:
			return Color.LIGHT_GRAY;
		default:
			return Color.BLACK;
		}
	}
	
	public static Color getCloseButtonSymbolColorUnhovered() {
		switch (currentTheme) {
		case LIGHT:
			return Color.WHITE;
		case DARK:
			return Color.BLACK;
		default:
			return Color.WHITE;
		}
	}
	
	public static Color getScrollBarColorBig() {
		switch (currentTheme) {
		case LIGHT:
			return Color.WHITE;
		case DARK:
			return new Color(21, 21, 21, 120);
		default:
			return Color.WHITE;
		}
	}
	
	public static Color getScrollBarColorSmall() {
		switch (currentTheme) {
		case LIGHT:
			return Color.WHITE;
		case DARK:
			return new Color(121, 121, 121, 110);
		default:
			return Color.WHITE;
		}
	}
	
	
}
