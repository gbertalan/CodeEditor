package view.window;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import utils.ANSIText;
import view.window.mainUI.MainUI;

/**
 * The Window class is the window frame. It has the MainUI panel and the
 * userinput listeners attached to it.
 * 
 * @author Gergely Bertalan
 *
 */
public class Window extends JFrame {
	private static final long serialVersionUID = 1L;

	private MainUI mainUI;

	public int width = 400;
	public int height = 300;

	public int locX = 900;
	public int locY = 100;

	public static JFrame jFrame;

	public int frameThichness = 6;

	private boolean refreshedAlready = false;

	private Listener listener;

	public Window() {

		try {
			Image icon = Toolkit.getDefaultToolkit().getImage("resources/logo.png");
			setIconImage(icon);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in loading icon image");
		}
		setTitle("Code Editor by Gergely Bertalan");
		setSize(width, height);
		setLocation(locX, locY);
		setUndecorated(true);
		setBackground(new Color(0, 0, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(0, 0, 0, 0));
		getContentPane().setLayout(null);

		addMainUIPanel();
		addListeners();

		setVisible(true);
	}

	public void addMainUIPanel() {
		this.mainUI = new MainUI(this);
		getContentPane().add(mainUI);
	}

	public void addListeners() {
		listener = new Listener(this);
		addMouseListener(new MouseListener(listener));
		addMouseMotionListener(new MouseMotionListener(listener));
		addWindowStateListener(new StateListener(this));
		addMouseWheelListener(new MouseWheelListener(listener));
		addKeyListener(new KeyListener(listener));
	}

	public MouseWheelListener getMouseWheelListener() {
		return (MouseWheelListener) getMouseWheelListeners()[0];
	}

	public MainUI getMainUI() {
		return mainUI;
	}

	public void refresh() {
		System.out.println(ANSIText.purple("Window refresh() is called."));
		if (!refreshedAlready) {
			revalidate();
			repaint();
			refreshedAlready = true;
		}
	}

	public void addPanel(JPanel panel) {
		getContentPane().add(panel, 0);
	}

	public void removePanel(int index) {
		getContentPane().remove(index);
	}

	public boolean isMaximized() {
		int state = this.getExtendedState();
		int maximized = Window.MAXIMIZED_BOTH;
		if ((state & maximized) == maximized) {
			return true;
		} else {
			return false;
		}
	}

	public void setMaximized(boolean maximized) {
		if (maximized)
			setExtendedState(getExtendedState() | Window.MAXIMIZED_BOTH);
		else
			setExtendedState(getExtendedState() & ~Window.MAXIMIZED_BOTH);
	}

	public Listener getListener() {
		return this.listener;
	}

}
