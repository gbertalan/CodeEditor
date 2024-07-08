package view.window.mainUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import utils.ANSIText;
import utils.Globals;
import view.window.mainUI.component.CloseButton;
import view.window.mainUI.component.UIComponent;
import view.window.Window;
import view.window.Listener.MouseListener;
import view.window.Listener.MouseMotionListener;
import view.window.Listener.StateListener;
import view.window.mainUI.component.FileButton;
import view.window.mainUI.component.Footer;
import view.window.mainUI.component.MaxButton;
import view.window.mainUI.component.SidePanelLeft;
import view.window.mainUI.component.SidePanelRight;
import view.window.mainUI.component.TitleBar;
import view.window.mainUI.component.TrayButton;

public class MainUI extends JPanel {
	private static final long serialVersionUID = 1L;

	private Graphics2D g2d;

	private Window window;
	
	private TitleBar titleBar;
	private CloseButton closeButton;
	private TrayButton trayButton;
	private MaxButton maxButton;
	private SidePanelLeft sidePanelLeft;
	private SidePanelRight sidePanelRight;
	private FileButton fileButton;
	private Footer footer;
	
//	private EmptySpace emptySpace;
	
	public Map<String, UIComponent> componentMap;

	public MainUI(Window window) {
		System.out.println(ANSIText.red("MainUI constructor is called."));
		
		this.window = window;

		setBounds(0, 0, window.width, window.height);
		setBackground(new Color(255, 0, 0, 0));
		setOpaque(false);

		this.titleBar = new TitleBar(window);
		this.closeButton = new CloseButton(window);
		this.trayButton = new TrayButton(window);
		this.maxButton = new MaxButton(window);
		this.sidePanelLeft = new SidePanelLeft(window);
		this.sidePanelRight= new SidePanelRight(window);
		this.fileButton = new FileButton(window);
		this.footer = new Footer(window);
		
//		this.emptySpace = new EmptySpace(window);

		initializeComponentMap();
		
//		MainUIListener listener = new MainUIListener(window);
        
//        addMouseListener(listener.new MouseListener());
//        addMouseMotionListener(listener.new MouseMotionListener());
//        addWindowStateListener(listener.new StateListener());
	}
	
	private void initializeComponentMap() {
        componentMap = new HashMap<>();
        componentMap.put("titleBar", titleBar);
        componentMap.put("closeButton", closeButton);
        componentMap.put("trayButton", trayButton);
        componentMap.put("maxButton", maxButton);
        componentMap.put("sidePanelLeft", sidePanelLeft);
        componentMap.put("sidePanelRight", sidePanelRight);
        componentMap.put("fileButton", fileButton);
        componentMap.put("footer", footer);
        
//        componentMap.put("emptySpace", emptySpace);
    }
	
	public UIComponent getComponent(String componentName) {
		return componentMap.get(componentName);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		System.out.println(ANSIText.red("MainUI paintComponent() is called."));
		
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
		footer.draw(g2d);

//		g2d.dispose();
	}

	public void drawPanel() {

	}
	

	public void update() {
		System.out.println(ANSIText.red("MainUI update() is called."));
		Rectangle rect = new Rectangle(0, 0, 100, 100);
        repaint();
	}

	private void updateAnimation() {
		// Update the state of the animation or any other dynamic content
		// For example, move objects, change colors, etc.
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
	
	public Footer getFooter() {
		return footer;
	}
}
