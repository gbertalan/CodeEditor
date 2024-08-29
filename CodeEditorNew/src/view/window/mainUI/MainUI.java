package view.window.mainUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import utils.Globals;
import view.window.Window;
import view.window.mainUI.component.*;
import view.window.mainUI.component.box.Box;

/**
 * The MainUI class is a panel that draws all the graphical components inside
 * the Window.
 * 
 * It also holds a map of all components.
 * 
 * @author Gergely Bertalan
 *
 */
public class MainUI extends JPanel {
	private static final long serialVersionUID = 1L;

	private Window window;
	private Graphics2D g2d;
	private Map<String, UIComponent> componentMap;

	public MainUI(Window window) {
		this.window = window;

		initComponents();

		setOpaque(false);
		setBackground(new Color(0, 0, 0, 0));
		setBounds(0, 0, window.width, window.height);
	}

	private void initComponents() {
		UIComponent[] components = { new EdgeWest(window, 10), new EdgeNorth(window, 10), new EdgeEast(window, 10),
				new EdgeSouth(window, 10), new TitleBar(window, 15), new CloseButton(window, 16),
				new TrayButton(window, 16), new MaxButton(window, 16), new SidePanelLeft(window, 10),
				new SidePanelRight(window, 10), new FileButton(window, 11), new Footer(window, 10),
				new Background(window, 0), new ProjectPanel(window, 11), new ConsolePanel(window, 11), new EditorPanel(window, 11) };

		componentMap = new HashMap<>();

		for (UIComponent component : components) {
			addComponent(component);
		}
	}

	public void addComponent(UIComponent component) {
		if (component instanceof Box)
			componentMap.put(component.getComponentName() + ((Box) component).getId(), component);
		else
			componentMap.put(component.getComponentName(), component);
	}

	public void removeComponent(Box box) {
		componentMap.values().remove(box);
	}

	public UIComponent getComponent(String componentName) {
		return componentMap.get(componentName);
	}

	public Map<String, UIComponent> getComponentMap() {
		return componentMap;
	}

	public List<Box> getBoxList() {
		List<Box> boxes = new ArrayList<>();
		for (UIComponent component : componentMap.values()) {
			if (component instanceof Box) {
				boxes.add((Box) component);
			}
		}
		return boxes;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		setBounds(0, 0, window.width, window.height);

		g2d = (Graphics2D) g.create();
		Globals.setRenderingHints(g2d);
		
		ArrayList<UIComponent> componentList = new ArrayList<>(componentMap.values());

		componentList.sort(new DrawPriorityComparator());

		for (UIComponent component : componentList) {
			component.draw(g2d);
		}

		g2d.dispose();
	}

}
