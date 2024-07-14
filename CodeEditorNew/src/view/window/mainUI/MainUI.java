package view.window.mainUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JPanel;

import utils.ANSIText;
import utils.Globals;
import view.window.Window;
import view.window.mainUI.component.*;
import view.window.mainUI.component.box.Box;

public class MainUI extends JPanel {
	private static final long serialVersionUID = 1L;

	private Window window;
	private Graphics2D g2d;
	private Map<String, UIComponent> componentMap;
	private CopyOnWriteArrayList<UIComponent> componentList;
	private CopyOnWriteArrayList<Box> boxList;

	public MainUI(Window window) {
		System.out.println(ANSIText.blue("MainUI constructor is called."));

		this.window = window;

		initComponents();

		setOpaque(false);
		setBackground(new Color(0, 0, 0, 0));
		setBounds(0, 0, window.width, window.height);
	}

	private void initComponents() {

		UIComponent[] components = { new EdgeWest(window, 10), new EdgeNorth(window, 10), new EdgeEast(window, 10),
				new EdgeSouth(window, 10), new TitleBar(window, 10), new CloseButton(window, 11),
				new TrayButton(window, 11), new MaxButton(window, 11), new SidePanelLeft(window, 10),
				new SidePanelRight(window, 10), new FileButton(window, 11), new Footer(window, 10),
				new Background(window, 0) };

		componentMap = new HashMap<>();
		componentList = new CopyOnWriteArrayList<>();
		boxList = new CopyOnWriteArrayList<>();

		for (UIComponent component : components) {
			addComponent(component);
		}

	}

	public void addComponent(UIComponent component) {
		String nameExtension;
		if (component.getComponentName().equals("Box") || component.getComponentName().equals("BoxHeader")
				|| component.getComponentName().equals("BoxInput") || component.getComponentName().equals("BoxArrow")
				|| component.getComponentName().equals("BoxContent")
				|| component.getComponentName().equals("BoxConsole")
				|| component.getComponentName().equals("BoxOutput")) {
			
			nameExtension = Integer.toString(Box.boxCounter);
			if (component.getComponentName().equals("Box")) {
				boxList.add((Box)component);
				++Box.boxCounter;
			}
		} else {
			nameExtension = "";
		}
		componentMap.put(component.getComponentName() + nameExtension, component);
		componentList.add(component);

//		System.out.println(ANSIText
//				.cyan("Component added: " + component.toString() + " ComponentList size: " + componentList.size()));
	}

	public UIComponent getComponent(String componentName) {
		return componentMap.get(componentName);
	}

	public CopyOnWriteArrayList<UIComponent> getComponentList() {
		return componentList;
	}
	
	public CopyOnWriteArrayList<Box> getBoxList() {
		return boxList;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		System.out.println(ANSIText.green("MainUI paintComponent() is called."));

		setBounds(0, 0, window.width, window.height);

		g2d = (Graphics2D) g.create();
		Globals.setRenderingHints(g2d);

		componentList.sort(new DrawPriorityComparator());

		for (UIComponent component : componentList) {
			component.draw(g2d);
		}

		g2d.dispose();
	}

	public void update() {
		System.out.println(ANSIText.yellow("MainUI update() is called."));
		repaint();
	}

	public void update(Rectangle rect) {
		System.out.println(ANSIText.yellow("MainUI update(rect) is called."));
		repaint(rect);
	}
}
