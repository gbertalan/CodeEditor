package view.window.mainUI.component.box.syntax;

import java.util.Set;

interface LanguageDefinition {
    Set<String> getKeywords();
    Set<String> getTypes();
    boolean isComment(String text);
    boolean isAnnotation(String text);
    boolean isImport(String text);
}


