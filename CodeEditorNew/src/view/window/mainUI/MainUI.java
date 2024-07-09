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
import view.window.Window;
import view.window.mainUI.component.*;

public class MainUI extends JPanel {
	private static final long serialVersionUID = 1L;

	private Window window;
	private Graphics2D g2d;
	private Map<String, UIComponent> componentMap;
	private List<UIComponent> componentList;

	public MainUI(Window window) {
		System.out.println(ANSIText.blue("MainUI constructor is called."));

		this.window = window;

		initComponents();

		setOpaque(false);
		setBackground(new Color(0, 0, 0, 0));
		setBounds(0, 0, window.width, window.height);
	}

	private void initComponents() {

		UIComponent[] components = { new EdgeWest(window, 3), new EdgeNorth(window, 3), new EdgeEast(window, 3),
				new EdgeSouth(window, 3), new TitleBar(window, 3), new CloseButton(window, 3),
				new TrayButton(window, 3), new MaxButton(window, 3), new SidePanelLeft(window, 3),
				new SidePanelRight(window, 3), new FileButton(window, 5), new Footer(window, 3),
				new Background(window, 0) };

		componentMap = new HashMap<>();
		componentList = new ArrayList<>();

		for (UIComponent component : components) {
			componentMap.put(component.getComponentName(), component);
			componentList.add(component);
		}

		componentList.sort(new DrawPriorityComparator());
	}

	public UIComponent getComponent(String componentName) {
		return componentMap.get(componentName);
	}

	public List<UIComponent> getComponentList() {
		return componentList;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		System.out.println(ANSIText.green("MainUI paintComponent() is called."));

		setBounds(0, 0, window.width, window.height);

		g2d = (Graphics2D) g.create();
		Globals.setRenderingHints(g2d);

		for (UIComponent component : componentList) {
			component.draw(g2d);
		}

		g2d.dispose();
	}

	public void update() {
		System.out.println(ANSIText.yellow("MainUI update() is called."));
		repaint();
	}
}
