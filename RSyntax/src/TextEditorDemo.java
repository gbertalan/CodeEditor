
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;

public class TextEditorDemo extends JFrame {

    public TextEditorDemo() {

        JPanel cp = new JPanel(new BorderLayout());

        RSyntaxTextArea textArea = new RSyntaxTextArea(20, 60);
        textArea.setSelectionColor(new Color(91, 137, 164));
        textArea.setCurrentLineHighlightColor(new Color(128, 128, 128));
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        textArea.setCodeFoldingEnabled(true);
        RTextScrollPane sp = new RTextScrollPane(textArea);
        cp.add(sp);

        setContentPane(cp);
        setTitle("Text Editor Demo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        
        
        Font font = new Font("Consolas", Font.BOLD, 18);
        textArea.setFont(font);
        textArea.setText("Hello\nHey");
        textArea.setBackground(new Color(24, 24, 24));
        textArea.setForeground(Color.WHITE);
        
        sp.getGutter().setBackground(new Color(24, 24, 24));
        sp.getGutter().setForeground(Color.WHITE);
        sp.getGutter().setBorderColor(Color.GRAY);
        
     // Customize the scrollbar UI
//        JScrollBar verticalScrollBar = sp.getVerticalScrollBar();
//        verticalScrollBar.setUI(new BasicScrollBarUI() {
//            @Override
//            protected void configureScrollBarColors() {
//                this.thumbColor = Color.GRAY; // Change the thumb color
//                this.trackColor = new Color(24, 24, 24); // Change the track color
//                this.thumbDarkShadowColor = new Color(24, 24, 24); // Change the thumb dark shadow color
//                this.thumbHighlightColor = new Color(24, 24, 24); // Change the thumb highlight color
//                this.thumbLightShadowColor = new Color(24, 24, 24); // Change the thumb light shadow color
//                this.scrollBarWidth = 10;
//            }
//        });
        
        JScrollBar verticalScrollBar = sp.getVerticalScrollBar();
        verticalScrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createArrowButton(orientation, Color.RED); // Customize color for decrease button
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createArrowButton(orientation, Color.BLUE); // Customize color for increase button
            }

            private JButton createArrowButton(int orientation, Color color) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(20, 20));
                button.setBackground(color);
                button.setBorder(BorderFactory.createEmptyBorder());
                button.setOpaque(true);

//                button.setIcon(new ArrowIcon(orientation));
                return button;
            }

            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
                // Custom code for painting the thumb
                g.setColor(Color.GREEN); // Thumb color
                g.fillRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height);
            }

            @Override
            protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
                // Custom code for painting the track
                g.setColor(Color.LIGHT_GRAY); // Track color
                g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
            }
        });

    }

    public static void main(String[] args) {
        // Start all Swing applications on the EDT.
        SwingUtilities.invokeLater(() -> new TextEditorDemo().setVisible(true));
    }

}