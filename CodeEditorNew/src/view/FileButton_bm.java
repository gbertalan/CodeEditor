package view;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import utils.Theme;

public class FileButton_bm extends Component {

	private static int TOP_MARGIN = 42;
	private static int SIZE = 55;

	private FilePanel filePanel = new FilePanel(window);

	public FileButton_bm(Window window) {
		super(window, 0, TOP_MARGIN, SIZE, SIZE);
		window.addPanel(filePanel);
		filePanel.setVisible(false);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics2D g2d) {

		g2d.setColor(Theme.getPanelButtonColor());
		if (hovered) {
			g2d.setColor(Theme.getPanelButtonHoverColor());
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

