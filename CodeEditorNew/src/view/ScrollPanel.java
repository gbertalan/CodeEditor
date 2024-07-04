package view;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;

public class ScrollPanel extends JPanel {

	private int x, y, width, height;
	
	public ScrollPanel(int x, int y, int width, int height, ArrayList<String> list) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		

		setLayout(null);
		setBackground(Color.GRAY);
		setBounds(x, y, width, height);
		
//		ScrollButton sb0 = new ScrollButton(list.get(0), width);
//		add(sb0);
//		ScrollButton sb1 = new ScrollButton(list.get(1), width);
//		add(sb1);
//		ScrollButton sb2 = new ScrollButton(list.get(2), width);
//		add(sb2);
		
		for (int i = 0; i < list.size(); i++) {
			ScrollButton sb = new ScrollButton(list.get(i), width);
			add(sb);
		}

	}
}
