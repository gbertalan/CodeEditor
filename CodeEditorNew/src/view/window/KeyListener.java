package view.window;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;

import view.window.mainUI.MainUI;
import view.window.mainUI.component.UIComponent;
import view.window.mainUI.component.box.Box;

/**
 * - keyPressed()
 * - keyReleased()
 * - keyTyped()
 * 
 * @author Gergely Bertalan
 *
 */
public class KeyListener extends KeyAdapter {

	private Listener listener;
	private Window window;
	private MainUI mainUI;
	private HashSet<UIComponent> hoveredComponents;

	public KeyListener(Listener listener) {
		this.listener = listener;
		this.window = listener.window;
		this.mainUI = listener.mainUI;
		this.hoveredComponents = listener.hoveredComponents;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
			for (Box box : listener.boxController.getBoxMap().values()) {
				box.disableBoxContent();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
			for (Box box : listener.boxController.getBoxMap().values()) {
				box.enableBoxContent();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// No action needed for keyTyped
	}

}
