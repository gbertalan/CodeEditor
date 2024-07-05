package view;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;

import utils.Theme;

public class ScrollPanel extends JPanel {

	private static Color BACKGROUND_COLOR = Theme.getBackgroundColor();

	public ScrollPanel(int x, int y, int width, int height, ArrayList<String> list) {
		
		setLayout(null);
		setBackground(BACKGROUND_COLOR);
		setBounds(x, y, width, height);

		for (int i = 0; i < list.size(); i++) {
			ScrollButton sb = new ScrollButton(list.get(i), width);
			add(sb);
		}

		ScrollButton.resetCounter();
	}
}
