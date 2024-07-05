package view;

import java.awt.Color;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import javax.swing.JPanel;

import utils.Theme;

public class ScrollPanel extends JPanel {

	private static Color BACKGROUND_COLOR = Theme.getBackgroundColor();
	private static int SCROLL_FACTOR = 30;
	
	private ArrayList<ScrollButton> buttons;

	private int scrollAmount;
	private static int savedScrollAmount;

	public ScrollPanel(int x, int y, int width, int height, ArrayList<String> list) {
		buttons = new ArrayList<>();
		setLayout(null);
		setBackground(BACKGROUND_COLOR);
		setBounds(x, y, width, height);

		for (int i = 0; i < list.size(); i++) {
			ScrollButton sb = new ScrollButton(list.get(i), this);
			buttons.add(sb);
			add(sb);
		}

		ScrollButton.resetCounter();
		
		for (ScrollButton sb : buttons) {
			sb.scroll(savedScrollAmount);
		}
	}

	public ArrayList<ScrollButton> getButtons() {
		return buttons;
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		scrollAmount = -e.getWheelRotation() * SCROLL_FACTOR;
		for (ScrollButton sb : buttons) {
			sb.scroll(scrollAmount);
		}
		savedScrollAmount+=scrollAmount;
		revalidate();
		repaint();
	}
	
	public void repaintAllButtons() {
		for (ScrollButton sb : buttons) {
			sb.revalidate();
			sb.repaint();
		}
		revalidate();
		repaint();
	}
}
