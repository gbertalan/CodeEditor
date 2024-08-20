package view.window.mainUI.component.box;

import java.awt.Color;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

public class SyntaxHighlight {

    private String text;
    private ArrayList<String> tokenizedText;
    private List<Entry<String, Color>> coloredText;
    
	// Colors based on VSCode's default theme
	private static final Color DEFAULT_COLOR = new Color(214, 214, 214);
	private static final Color KEYWORD_COLOR = new Color(86, 156, 214); // Blue
	private static final Color OPERATOR_COLOR = new Color(12, 212, 212); //
	private static final Color PUNCTUATION_COLOR = new Color(212, 212, 12); //
	private static final Color COMMENT_COLOR = new Color(106, 153, 85); // Green
	private static final Color ANNOTATION_COLOR = new Color(255, 198, 109); // Yellow
	private static final Color IMPORT_COLOR = new Color(209, 154, 102); // Orange
	private static final Color STRING_COLOR = new Color(109, 154, 102);

    public SyntaxHighlight(String text) {
        this.text = text;
        tokenizedText = tokenize(text);
        coloredText = colorize(tokenizedText);
    }

    private ArrayList<String> tokenize(String text) {
        ArrayList<String> tokens = new ArrayList<>();
        StringBuilder currentToken = new StringBuilder();

        int length = text.length();
        for (int i = 0; i < length; i++) {
            char c = text.charAt(i);

            if (isWhiteSpace(c)) {
                if (currentToken.length() > 0) {
                    tokens.add(currentToken.toString());
                    currentToken.setLength(0);
                }
                StringBuilder whitespace = new StringBuilder();
                while (i < length && isWhiteSpace(text.charAt(i))) {
                    whitespace.append(text.charAt(i));
                    i++;
                }
                i--; 
                tokens.add(whitespace.toString());
            } else if (isOperator(c) || isPunctuation(c)) {
                if (currentToken.length() > 0) {
                    tokens.add(currentToken.toString());
                    currentToken.setLength(0);
                }
                tokens.add(String.valueOf(c));
            } else if (c == '"' || c == '\'') {
                if (currentToken.length() > 0) {
                    tokens.add(currentToken.toString());
                    currentToken.setLength(0);
                }
                currentToken.append(c);
                i++;
                while (i < length && text.charAt(i) != c) {
                    currentToken.append(text.charAt(i));
                    i++;
                }
                if (i < length) {
                    currentToken.append(c);
                }
                tokens.add(currentToken.toString());
                currentToken.setLength(0);
            } else {
                currentToken.append(c);
            }
        }

        if (currentToken.length() > 0) {
            tokens.add(currentToken.toString());
        }

        return tokens;
    }

    private List<Entry<String, Color>> colorize(ArrayList<String> tokenizedText) {
        List<Entry<String, Color>> coloredText = new ArrayList<>();

        for (String token : tokenizedText) {
            Color color;

            if (isKeyword(token)) {
                color = KEYWORD_COLOR;
            } else if (isOperator(token.charAt(0))) {
                color = OPERATOR_COLOR;
            } else if (isPunctuation(token.charAt(0))) {
                color = PUNCTUATION_COLOR;
            } else if (isComment(token)) {
                color = COMMENT_COLOR;
            } else if (isAnnotation(token)) {
                color = ANNOTATION_COLOR;
            } else if (isImport(token)) {
                color = IMPORT_COLOR;
            } else if (token.trim().isEmpty()) {
                color = null; 
            } else if (token.startsWith("\"") || token.startsWith("'")) {
                color = STRING_COLOR;
            } else {
                color = DEFAULT_COLOR;
            }

            coloredText.add(new SimpleEntry<>(token, color));
        }

        return coloredText;
    }

    public List<Entry<String, Color>> getColoredText() {
        return coloredText;
    }

    private static final Set<String> JAVA_KEYWORDS = new HashSet<>(Arrays.asList("abstract", "assert", "boolean",
            "break", "byte", "case", "catch", "char", "class", "const", "continue", "default", "do", "double", "else",
            "enum", "extends", "final", "finally", "float", "for", "goto", "if", "implements", "import", "instanceof",
            "int", "interface", "long", "native", "new", "null", "package", "private", "protected", "public", "return",
            "short", "static", "strictfp", "super", "switch", "synchronized", "this", "throw", "throws", "transient",
            "try", "void", "volatile", "while"));

    private boolean isWhiteSpace(char c) {
        return Character.isWhitespace(c) || c == '\u00A0' || c == '\t';
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
}
