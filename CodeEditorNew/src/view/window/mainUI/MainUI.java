package view.window.mainUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import utils.ANSIText;
import utils.Globals;
import view.window.mainUI.component.CloseButton;
import view.window.mainUI.component.DrawPriorityComparator;
import view.window.mainUI.component.EdgeEast;
import view.window.mainUI.component.EdgeNorth;
import view.window.mainUI.component.EdgeSouth;
import view.window.mainUI.component.EdgeWest;
import view.window.mainUI.component.FileButton;
import view.window.mainUI.component.UIComponent;
import view.window.Window;
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

	private EdgeWest edgeWest;
	private EdgeNorth edgeNorth;
	private EdgeEast edgeEast;
	private EdgeSouth edgeSouth;
	private TitleBar titleBar;
	private CloseButton closeButton;
	private TrayButton trayButton;
	private MaxButton maxButton;
	private SidePanelLeft sidePanelLeft;
	private SidePanelRight sidePanelRight;
	private FileButton fileButton;
	private Footer footer;

	public Map<String, UIComponent> componentMap;
	private List<UIComponent> componentList = new ArrayList<>();

	public MainUI(Window window) {
		System.out.println(ANSIText.red("MainUI constructor is called."));

		this.window = window;

		setBounds(0, 0, window.width, window.height);
		setBackground(new Color(0, 0, 0, 0));
		setOpaque(false);

		this.edgeWest = new EdgeWest(window, 3);
		this.edgeNorth = new EdgeNorth(window, 3);
		this.edgeEast = new EdgeEast(window, 3);
		this.edgeSouth = new EdgeSouth(window, 3);
		this.titleBar = new TitleBar(window, 3);
		this.closeButton = new CloseButton(window, 3);
		this.trayButton = new TrayButton(window, 3);
		this.maxButton = new MaxButton(window, 3);
		this.sidePanelLeft = new SidePanelLeft(window, 3);
		this.sidePanelRight = new SidePanelRight(window, 3);
		this.fileButton = new FileButton(window, 5);
		this.footer = new Footer(window, 3);

		initializeComponentMap();
		initializeComponentList();
	}

	private void initializeComponentMap() {
		componentMap = new HashMap<>();
		componentMap.put("edgeWest", edgeWest);
		componentMap.put("edgeNorth", edgeNorth);
		componentMap.put("edgeEast", edgeEast);
		componentMap.put("edgeSouth", edgeSouth);
		componentMap.put("titleBar", titleBar);
		componentMap.put("closeButton", closeButton);
		componentMap.put("trayButton", trayButton);
		componentMap.put("maxButton", maxButton);
		componentMap.put("sidePanelLeft", sidePanelLeft);
		componentMap.put("sidePanelRight", sidePanelRight);
		componentMap.put("fileButton", fileButton);
		componentMap.put("footer", footer);
	}

	private void initializeComponentList() {
		for (UIComponent component : componentMap.values()) {
			componentList.add(component);
		}
		componentList.sort(new DrawPriorityComparator());
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

		for (UIComponent component : componentList) {
			component.draw(g2d);
		}

//		g2d.dispose();
	}

	public void drawPanel() {

	}

	public void update() {
		System.out.println(ANSIText.red("MainUI update() is called."));
		repaint();
	}

}
