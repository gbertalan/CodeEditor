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

        for (UIComponent component : components) {
            addComponent(component);
        }
    }

    public void addComponent(UIComponent component) {
        componentMap.put(component.getComponentName(), component);
        componentList.add(component);

        System.out.println(ANSIText
                .cyan("Component added: " + component.toString() + " ComponentList size: " + componentList.size()));
    }

    public void removeComponent(Box box) {
        componentMap.values().remove(box);
        componentList.remove(box);
    }

    public UIComponent getComponent(String componentName) {
        return componentMap.get(componentName);
    }

    public CopyOnWriteArrayList<UIComponent> getComponentList() {
        return componentList;
    }

    public List<Box> getBoxList() {
        List<Box> boxes = new ArrayList<>();
        for (UIComponent component : componentList) {
            if (component instanceof Box) {
                boxes.add((Box) component);
            }
        }
        return boxes;
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
