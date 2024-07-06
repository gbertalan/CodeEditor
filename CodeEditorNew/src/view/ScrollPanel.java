package view;

import java.awt.Color;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import javax.swing.JPanel;

import utils.Theme;

public class ScrollPanel extends JPanel {

	private static Color BACKGROUND_COLOR = Theme.getBackgroundColor();
	private static int SCROLL_FACTOR = 30;
	public static int BUTTON_HEIGHT = 30;

	private Window window;
	
	private ArrayList<String> list;
	private ArrayList<ScrollButton> buttons;

	private int scrollAmount;
	public static int savedScrollAmount;

	private int height;

	public ScrollPanel(Window window, int x, int y, int width, int height, ArrayList<String> list) {

		this.window = window;
		this.height = height;
		this.list = list;

		buttons = new ArrayList<>();
		setLayout(null);
		setBackground(BACKGROUND_COLOR);
		setBounds(x, y, width, height);

		for (int i = 0; i < list.size(); i++) {
			ScrollButton sb = new ScrollButton(window, list.get(i), this);
			buttons.add(sb);
			add(sb);
		}

		ScrollButton.resetCounter();

		for (ScrollButton sb : buttons) {
			sb.scroll(0);
		}
		savedScrollAmount = 0;

	}

	public ArrayList<ScrollButton> getButtons() {
		return buttons;
	}

	public void mouseWheelMoved(MouseWheelEvent e) {

		scrollAmount = -e.getWheelRotation() * SCROLL_FACTOR;

		for (ScrollButton sb : buttons) {
			sb.scroll(scrollAmount);
		}
		savedScrollAmount += scrollAmount;

		while (savedScrollAmount > 0 || savedScrollAmount + ((buttons.size() + 2) * BUTTON_HEIGHT) < height) {

			for (ScrollButton sb : buttons) {
				sb.scroll(-scrollAmount);
			}
			savedScrollAmount -= scrollAmount;
		}

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
