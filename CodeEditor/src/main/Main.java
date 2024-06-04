package main;

import java.awt.Color;

public class Main {

	public static void main(String[] args) {
		Window window = new Window.Builder().setIcon("resources/logo.png").setSize(800, 600).setLocation(100, 100)
				.setDecorations(true).setBackground(new Color(0, 0, 0, 0)).setLayout(null).build();

		window.setController();
		window.display();
	}
}
