package view.window.mainUI.component.box;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.Set;

import java.util.HashSet;
import java.util.Arrays;

import utils.SyntaxColor;

public class LineTextContainer {

	private static final double HEIGHT_DIVISOR = 36;

	private Box box;
	private LineNumberContainer lineNumberContainer;
	private String lineText;
	private int locX;
	private int locY;
	private int width;
	private int height;

	private Font font;
	private FontMetrics metrics;

	private boolean lastingHighlight;

	private int scrollHorizontal;

	private int scrollVertical;

	// Colors based on VSCode's default theme
	private static final Color DEFAULT_COLOR = new Color(214, 214, 214);
	private static final Color KEYWORD_COLOR = new Color(86, 156, 214); // Blue
	private static final Color OPERATOR_COLOR = new Color(12, 212, 212); //
	private static final Color PUNCTUATION_COLOR = new Color(212, 212, 12); //
	private static final Color COMMENT_COLOR = new Color(106, 153, 85); // Green
	private static final Color ANNOTATION_COLOR = new Color(255, 198, 109); // Yellow
	private static final Color IMPORT_COLOR = new Color(209, 154, 102); // Orange

	private static final Set<String> JAVA_KEYWORDS = new HashSet<>(Arrays.asList("abstract", "assert", "boolean",
			"break", "byte", "case", "catch", "char", "class", "const", "continue", "default", "do", "double", "else",
			"enum", "extends", "final", "finally", "float", "for", "goto", "if", "implements", "import", "instanceof",
			"int", "interface", "long", "native", "new", "null", "package", "private", "protected", "public", "return",
			"short", "static", "strictfp", "super", "switch", "synchronized", "this", "throw", "throws", "transient",
			"try", "void", "volatile", "while"));

	public LineTextContainer(Box box, String lineText, LineNumberContainer lineNumberContainer) {
		this.box = box;
		this.lineText = lineText.replace("\t", "\u00A0\u00A0\u00A0\u00A0");
		this.lineNumberContainer = lineNumberContainer;

		updateLocationAndSize();
		this.font = new Font("Consolas", Font.BOLD, (int) Math.round(box.getHeight() / HEIGHT_DIVISOR));
	}

	private void updateLocationAndSize() {
		locX = lineNumberContainer.getLocX() + lineNumberContainer.getWidth() + scrollHorizontal;
		locY = lineNumberContainer.getLocY() + scrollVertical;
		width = box.getWidth() - lineNumberContainer.getWidth();
		height = box.getHeight();
	}

	private boolean isKeyword(String word) {
		return JAVA_KEYWORDS.contains(word.trim());
	}

	private boolean isOperator(char c) {
		return "+-*/%=&|^<>!~".indexOf(c) > -1;
	}

	private boolean isPunctuation(char c) {
		return ",;:.(){}[]".indexOf(c) > -1;
	}

	private boolean isComment(String text) {
		return text.startsWith("//") || text.startsWith("/*") || text.startsWith("*") || text.startsWith("*/");
	}

	private boolean isAnnotation(String text) {
		return text.startsWith("@");
	}

	private boolean isImport(String text) {
		return text.startsWith("import");
	}

	public void draw(Graphics2D g2d) {
		updateLocationAndSize();
		g2d.setColor(SyntaxColor.getDefaultTextColor());
		g2d.setFont(font);

		if (metrics == null) {
			metrics = g2d.getFontMetrics(font);
		}

		int textHeight = locY + metrics.getAscent();
		int drawX = locX;
		for (char c : lineText.toCharArray()) {

			highlightChar(g2d, c);

			g2d.drawString(String.valueOf(c), drawX, textHeight);
			drawX += metrics.charWidth(c);

			if (!lastingHighlight)
				g2d.setColor(DEFAULT_COLOR);
		}

	}

	private void highlightChar(Graphics2D g2d, char c) {
		if (isOperator(c)) {
			g2d.setColor(OPERATOR_COLOR);
			lastingHighlight = false;
		} else if(isPunctuation(c)) {
			g2d.setColor(PUNCTUATION_COLOR);
			lastingHighlight = false;
		}
	}
	
	private void highlightWord(Graphics2D g2d, String word) {
		
	}
	
	public void setScrollHorizontal(int value) {
		this.scrollHorizontal = value;
	}
	
	public void setScrollVertical(int value) {
		this.scrollVertical = value;
	}
}
