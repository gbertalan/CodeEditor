package view.window.mainUI.component.box.syntax;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class JavaLanguageDefinition implements LanguageDefinition {

    private static final Set<String> JAVA_TYPES = new HashSet<>(
        Arrays.asList("boolean", "byte", "char", "double", "enum", "float", "int", "long", "short", "void"));

    private static final Set<String> JAVA_KEYWORDS = new HashSet<>(Arrays.asList("abstract", "assert", "break", "case",
        "catch", "class", "const", "continue", "default", "do", "else", "extends", "final", "finally", "for",
        "goto", "if", "implements", "import", "instanceof", "interface", "native", "new", "null", "package",
        "private", "protected", "public", "return", "static", "strictfp", "super", "switch", "synchronized", "this",
        "throw", "throws", "transient", "try", "volatile", "while"));

    @Override
    public Set<String> getKeywords() {
        return JAVA_KEYWORDS;
    }

    @Override
    public Set<String> getTypes() {
        return JAVA_TYPES;
    }

    @Override
    public boolean isComment(String text) {
        return text.startsWith("//") || text.startsWith("/*") || text.startsWith("*") || text.startsWith("*/");
    }

    @Override
    public boolean isAnnotation(String text) {
        return text.startsWith("@");
    }

    @Override
    public boolean isImport(String text) {
        return text.startsWith("import");
    }
}
