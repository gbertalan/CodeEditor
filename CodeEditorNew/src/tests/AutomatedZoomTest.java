package tests;

import java.awt.Robot;

import javax.swing.JFrame;

import control.Control;
import model.Model;
import utils.ANSIText;
import view.View;
import view.window.MouseWheelListener;

public class AutomatedZoomTest {

	public static void main(String[] args) throws Exception {
		Model model = new Model();
		View view = new View();
		Control control = new Control(model, view);

//		System.out.println(ANSIText.bold(ANSIText.red("Move the cursor to the window")));
		Thread.sleep(1000);

		// Create the Robot instance
		Robot robot = new Robot();
		robot.setAutoDelay(5);

		System.out.println("Create a box manually");
		Thread.sleep(7000);

		System.out.println("The test is starting.");
		Thread.sleep(3000);

		moveMouseToCenter(robot, view);

		System.out.println("Starting zoom value: " + MouseWheelListener.zoomValue);
		System.out.println("Starting zoom level: " + MouseWheelListener.zoomLevel);

		for (int j = 0; j < 10; j++) {

			for (int i = 0; i < 10; i++) {
				robot.mouseWheel(1);
				Thread.sleep(100);
				moveMouseToCenter(robot, view);
			}

			for (int i = 0; i < 10; i++) {
				robot.mouseWheel(-1);
				Thread.sleep(100);
				moveMouseToCenter(robot, view);
			}

		}

		System.out.println("Test finished.");
	}
	
	private static void moveMouseToCenter(Robot robot, View view) {
		robot.mouseMove(view.getWindow().locX + (view.getWindow().width / 2),
				view.getWindow().locY + (view.getWindow().height / 2));
	}

}
