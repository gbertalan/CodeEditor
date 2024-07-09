package view.window;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;

import utils.ANSIText;
import view.window.Listener.MouseListener;
import view.window.Listener.MouseMotionListener;
import view.window.Listener.StateListener;
import view.window.background.MainBackgroundPanel;
import view.window.mainUI.MainUI;
import view.window.workspace.Workspace;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;

	private MainUI mainUI;
	private MainBackgroundPanel mainBackgroundPanel;
	private Workspace innerCanvas;

	public int width = 400;
	public int height = 300;

	public int locX = 900;
	public int locY = 100;

	public static JFrame jFrame;

	public int frameThichness = 6;

	private boolean refreshedAlready = false;

	public Window() {
		System.out.println(ANSIText.purple("Window constructor is called."));
		
		setTitle("Code Editor by Gergely Bertalan");
		try {
			Image icon = Toolkit.getDefaultToolkit().getImage("resources/logo.png");
			setIconImage(icon);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in loading icon image");
		}
		setSize(width, height);
		setLocation(locX, locY);
		setUndecorated(true);
		setBackground(new Color(0, 0, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(0, 0, 0, 0));
		getContentPane().setLayout(null);
		setVisible(true);
		
		
	}
	
	public void attachPanels() {
		MainBackgroundPanel mainBackgroundPanel = new MainBackgroundPanel(this);
		Workspace innerCanvas = new Workspace(this);
		MainUI mainUI = new MainUI(this);

		addMainBackgroundPanel(mainBackgroundPanel);
		addInnerCanvas(innerCanvas);
		addMainUI(mainUI);

		getContentPane().setComponentZOrder(mainBackgroundPanel, 2);
		getContentPane().setComponentZOrder(innerCanvas, 1);
		getContentPane().setComponentZOrder(mainUI, 0);
		
		revalidate();
		repaint();
	}
	
	public void attachListeners() {
		Listener listener = new Listener(this);
		addMouseListener(listener.new MouseListener());
		addMouseMotionListener(listener.new MouseMotionListener());
		addWindowStateListener(listener.new StateListener());
	}

	private void addMainUI(MainUI mainUI) {
		this.mainUI = mainUI;
		getContentPane().add(mainUI);
	}

	public MainUI getMainUI() {
		return mainUI;
	}

	private void addMainBackgroundPanel(MainBackgroundPanel mainBackgroundPanel) {
		this.mainBackgroundPanel = mainBackgroundPanel;
		getContentPane().add(mainBackgroundPanel);
	}

	public MainBackgroundPanel getMainBackgroundPanel() {
		return mainBackgroundPanel;
	}

	private void addInnerCanvas(Workspace innerCanvas) {
		this.innerCanvas = innerCanvas;
		getContentPane().add(innerCanvas);
	}

	public Workspace getInnerCanvas() {
		return innerCanvas;
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
		if(maximized)
			setExtendedState(getExtendedState() | Window.MAXIMIZED_BOTH);
		else
			setExtendedState(getExtendedState() & ~Window.MAXIMIZED_BOTH);
	}

}
