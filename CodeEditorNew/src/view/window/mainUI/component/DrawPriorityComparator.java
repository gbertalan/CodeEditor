package view.window.mainUI.component;

import java.util.Comparator;

public class DrawPriorityComparator implements Comparator<UIComponent> {
	@Override
	public int compare(UIComponent c1, UIComponent c2) {
		return Integer.compare(c1.drawPriority, c2.drawPriority);
	}
}
