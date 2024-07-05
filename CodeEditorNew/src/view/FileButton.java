package view;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import utils.Theme;

public class FileButton extends Component {

	private static int TOP_MARGIN = 42;
	private static int SIZE = 55;

	FilePanel filePanel = new FilePanel(window);

	private boolean done; // to make sure code is called only ones when button is hovered

	public FileButton(Window window) {
		super(window, 0, TOP_MARGIN, SIZE, SIZE);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics2D g2d) {

		g2d.setColor(Theme.getPanelButtonColor());
		if (hovered) {
			if (!done) {
				g2d.setColor(Theme.getPanelButtonHoverColor());

				if (filePanel.scrollPanel != null)
					filePanel.recreateScrollPanel(window.getHeight());
				window.addPanel(filePanel);
				window.refresh();

				done = true;
			}
		} else {
			done = false;
			if (!filePanel.hovered) {
				window.getContentPane().remove(filePanel);
				window.refresh();

			} else {
				g2d.setColor(Theme.getPanelButtonHoverColor());
			}
		}

		g2d.fillRect(locX, locY, width, height);

		g2d.setColor(Theme.getSeparatorLineColor());
		g2d.drawRect(locX, locY, width, height);
	}

	public boolean isHovered(MouseEvent e, int modifier) {
		hovered = e.getX() > locX + modifier && e.getY() > locY + modifier && e.getX() < locX + width - modifier
				&& e.getY() < locY + height - modifier ? true : false;
		return hovered;
	}

}
