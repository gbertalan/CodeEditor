package utils;

import java.awt.Color;

import utils.Theme.ThemeType;

public class SyntaxColor {

	public enum LanguageType {
		JAVA, C
	}

	private static LanguageType currentLanguage = LanguageType.JAVA;

	public static void setLanguage(LanguageType language) {
		currentLanguage = language;
	}

	public static Color getLineNumberColor() {
		return new Color(124, 124, 124);
	}
	
	public static Color getDefaultTextColor() {
		return new Color(224, 224, 224);
	}

//	public static Color getLineNumberColor() {
//		switch (currentTheme) {
//		case LIGHT:
//			return new Color(200, 200, 200);
//		case DARK:
//			return new Color(24, 24, 24);
//		default:
//			return Color.GRAY;
//		}
//	}
}
