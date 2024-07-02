package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class FileButton extends Component {

	private static int TOP_MARGIN = 42;
	private static int SIZE = 55;

	private FilePanel filePanel = new FilePanel(window);

	public FileButton(Window window) {
		super(window, 1, TOP_MARGIN + 1, SIZE, SIZE);
		window.addPanel(filePanel);
		filePanel.setVisible(false);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics2D g2d) {

		g2d.setColor(Color.BLUE);
		if (hovered) {
			g2d.setColor(Color.RED);
			if (!filePanel.isVisible()) {
				filePanel.setVisible(true);
				window.refresh();
			}
		} else {
			if (!filePanel.hovered) {
				if (filePanel.isVisible()) {
					filePanel.setVisible(false);
					window.refresh();
				}
			} else {
				g2d.setColor(Color.RED);
			}
		}

		g2d.fillRect(locX, locY, width, height);
	}

	public boolean isHovered(MouseEvent e, int modifier) {
		hovered = e.getX() > locX + modifier && e.getY() > locY + modifier && e.getX() < locX + width - modifier
				&& e.getY() < locY + height - modifier ? true : false;
		return hovered;
	}

}
