package view.window.mainUI.component.box.syntax;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class CppLanguageDefinition implements LanguageDefinition {

    private static final Set<String> CPP_TYPES = new HashSet<>(Arrays.asList(
        "bool", "char", "double", "float", "int", "long", "short", "signed", "unsigned", "void", "wchar_t"));

    private static final Set<String> CPP_KEYWORDS = new HashSet<>(Arrays.asList("alignas", "alignof", "and", "and_eq", 
        "asm", "auto", "bitand", "bitor", "break", "case", "catch", "class", "compl", "const", "const_cast", "continue", 
        "decltype", "default", "delete", "do", "dynamic_cast", "else", "enum", "explicit", "export", "extern", "false", 
        "for", "friend", "goto", "if", "inline", "mutable", "namespace", "new", "noexcept", "not", "not_eq", "nullptr", 
        "operator", "or", "or_eq", "private", "protected", "public", "reinterpret_cast", "return", "sizeof", "static", 
        "static_assert", "static_cast", "struct", "switch", "template", "this", "throw", "true", "try", "typedef", 
        "typeid", "typename", "union", "using", "virtual", "void", "volatile", "while", "xor", "xor_eq"));

    @Override
    public Set<String> getKeywords() {
        return CPP_KEYWORDS;
    }

    @Override
    public Set<String> getTypes() {
        return CPP_TYPES;
    }

    @Override
    public boolean isComment(String text) {
        return text.startsWith("//") || text.startsWith("/*") || text.startsWith("*") || text.startsWith("*/");
    }

    @Override
    public boolean isAnnotation(String text) {
        return false; // C++ doesn't use annotations like Java
    }

    @Override
    public boolean isImport(String text) {
        return text.startsWith("#include");
    }
}
