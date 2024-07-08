package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import utils.ANSIText;
import utils.Globals;

public class InnerCanvas extends JPanel {

	private Window window;
	private static int MARGIN = 2;
	private ArrayList<FileBox> fileBoxCatalog = new ArrayList<>();
	private FileBox activeFileBox;

	public InnerCanvas(Window window) {
		System.out.println(ANSIText.blue("InnerCanvas constructor is called."));
		
		this.window = window;

		setBackground(new Color(0, 0, 255, 0));
		setLayout(null);
		setBounds(MARGIN, MARGIN, window.width - (MARGIN * 2), window.height - (MARGIN * 2));
		setOpaque(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		System.out.println(ANSIText.blue("InnerCanvas paintComponent() is called."));
		
		setBounds(MARGIN, MARGIN, window.width - (MARGIN * 2), window.height - (MARGIN * 2));

		Graphics2D g2d = (Graphics2D) g;
		Globals.setRenderingHints(g2d);

		g2d.setColor(new Color(0, 0, 255, 0));
		g2d.fillRect(0, 0, getWidth(), getHeight());

		for (FileBox fileBox : fileBoxCatalog) {
			fileBox.draw(g2d);
			activeFileBox = fileBox;
//			fileBox.update();
		}

	}

	public void addFileBox(FileBox fileBox) {
		this.fileBoxCatalog.add(fileBox);
		System.out.println("FileBox added to the catalog. Size of fileBoxCatalog: " + fileBoxCatalog.size());
	}
	
	public void mouseDragged() {
//		System.out.println("InnerCanvas: "+"dragging on innercanvas.");
	}
	
	public void mouseReleased() {
//		System.out.println("InnerCanvas: "+"released on innercanvas.");
		if(activeFileBox!=null) {
				activeFileBox.setGrabbed(false);
				repaint();
		}
	}
}
