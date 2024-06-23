package control;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import view.Window;

/**
 * InnerListener is responsible for handling events related to the inner
 * components of the window that users interact with. These components can
 * include buttons, text fields, sliders, and other UI elements within the main
 * content area of the window.
 */

public class InnerListener {

	private Window window;

	public InnerListener(Window window) {
		this.window = window;

		window.addMouseListener(new MouseListener());
		window.addMouseMotionListener(new MouseMotionListener());
	}

	private class MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {

		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}
	}

	private class MouseMotionListener extends MouseMotionAdapter {

		@Override
		public void mouseDragged(MouseEvent e) {

		}

		@Override
		public void mouseMoved(MouseEvent e) {

		}
	}

}
