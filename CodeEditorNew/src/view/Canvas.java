package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import utils.Globals;

public class Canvas extends JPanel {
	private static final long serialVersionUID = 1L;

	private Graphics2D g2d;

	private Window window;
	private Background background;
	private TitleBar titleBar;
	private CloseButton closeButton;
	private TrayButton trayButton;
	private MaxButton maxButton;
	private SidePanelLeft sidePanelLeft;
	private SidePanelRight sidePanelRight;
	private FileButton fileButton;
	private SettingsButton settingsButton;
	private Footer footer;

	public Canvas(Window window) {
		this.window = window;

		setBounds(0, 0, window.width, window.height);
		setBackground(new Color(0, 0, 0, 0));
		setOpaque(false);

		this.background = new Background(window);
		this.titleBar = new TitleBar(window);
		this.closeButton = new CloseButton(window);
		this.trayButton = new TrayButton(window);
		this.maxButton = new MaxButton(window);
		this.sidePanelLeft = new SidePanelLeft(window);
		this.sidePanelRight= new SidePanelRight(window);
		this.fileButton = new FileButton(window);
		this.settingsButton = new SettingsButton(window);
		this.footer = new Footer(window);

		/*
		 * // Set up Timer to call actionPerformed every 16 ms (~60 FPS) Timer timer =
		 * new Timer(16, new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) { // This method will be
		 * called every 16 milliseconds updateAnimation(); // Update any animations or
		 * states repaint(); // Request a repaint } }); timer.start(); // Start the
		 * timer
		 */
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		System.out.println("Canvas paint");
		
		setBounds(0, 0, window.width, window.height);

		g2d = (Graphics2D) g;
		Globals.setRenderingHints(g2d);

//		background.draw(g2d);
		titleBar.draw(g2d);
		closeButton.draw(g2d);
		trayButton.draw(g2d);
		maxButton.draw(g2d);
		sidePanelLeft.draw(g2d);
		sidePanelRight.draw(g2d);
		fileButton.draw(g2d);
		settingsButton.draw(g2d);
		footer.draw(g2d);

//		g2d.dispose();
	}

	public void drawPanel() {

	}

	public void update() {
		repaint();
	}

	private void updateAnimation() {
		// Update the state of the animation or any other dynamic content
		// For example, move objects, change colors, etc.
	}

	public Background getCanvasBackground() {
		return background;
	}

	public TitleBar getTitleBar() {
		return titleBar;
	}

	public CloseButton getCloseButton() {
		return closeButton;
	}

	public TrayButton getTrayButton() {
		return trayButton;
	}

	public MaxButton getMaxButton() {
		return maxButton;
	}

	public SidePanelLeft getSidePanelLeft() {
		return sidePanelLeft;
	}
	
	public SidePanelRight getSidePanelRight() {
		return sidePanelRight;
	}

	public FileButton getFileButton() {
		return fileButton;
	}
	
	public SettingsButton getSettingsButton() {
		return settingsButton;
	}
	
	public Footer getFooter() {
		return footer;
	}
}
