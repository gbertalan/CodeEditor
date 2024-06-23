package tests;

import javax.swing.*;

import control.Control;
import model.Model;
import view.View;

import java.awt.*;
import java.awt.event.InputEvent;

public class RobotTest {

	public static void main(String[] args) throws Exception {
		Model model = new Model();
		View view = new View();
		Control control = new Control(model, view);

		JFrame frame = view.getWindow();
		
		int titleBarHeight = 42;

		// Give the GUI time to render
		Thread.sleep(1000);

		// Create the Robot instance
		Robot robot = new Robot();
		robot.setAutoDelay(5);

//		dragByTitleBar(frame, robot);

		// Resize the window by dragging the edge
		
		dragTopLeftCorner(frame, robot);
		dragTopRightCorner(frame, robot);
		dragBottomLeftCorner(frame, robot);
        dragBottomRightCorner(frame, robot);
        dragLeftEdge(frame, robot);
        dragRightEdge(frame, robot);
        dragTopEdge(frame, robot);
        dragBottomEdge(frame, robot);
        
        doubleClickTitleBar(frame, robot);
        
        maximizeAndDragWindow(frame, robot);
        
        
        dragTopEdgeBUG(frame, robot);

		System.exit(0);
	}
	
	private static void dragByTitleBar(JFrame frame, Robot robot) throws InterruptedException {
		// Helper variables
				int titleBarHeight = 42;

				// Move the window by dragging the title bar
				int x = 300;
				int y = 110;
				robot.mouseMove(x, y);
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				for (int i = 0; i < 20; i++) {
					x += 5;
					y += 5;
					robot.mouseMove(x, y);
				}
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				Thread.sleep(500);
				Point newPosition = frame.getLocation();
				System.out.println("Moved Position: " + newPosition);
	}

	private static void dragEdge(JFrame frame, Robot robot, int xModifier, int yModifier) throws InterruptedException {
	    int x = frame.getX() + xModifier;
	    int y = frame.getY() + yModifier;
	    robot.mouseMove(x, y);
	    Thread.sleep(500);
	    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
	    for (int i = 0; i < 20; i++) {
	        x += xModifier > 0 ? 5 : -5;
	        y += yModifier > 0 ? 5 : -5;
	        robot.mouseMove(x, y);
	    }

	    for (int i = 0; i < 20; i++) {
	        x -= xModifier > 0 ? 5 : -5;
	        y -= yModifier > 0 ? 5 : -5;
	        robot.mouseMove(x, y);
	    }

	    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	    Thread.sleep(500);
	    Dimension newSize = frame.getSize();
	    System.out.println("Resized Dimensions: " + newSize);
	}

	private static void dragCorner(JFrame frame, Robot robot, int xModifier, int yModifier) throws InterruptedException {
	    int x = frame.getX() + xModifier;
	    int y = frame.getY() + yModifier;
	    robot.mouseMove(x, y);
	    Thread.sleep(500);
	    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
	    for (int i = 0; i < 20; i++) {
	        x += xModifier > 0 ? 5 : -5;
	        y += yModifier > 0 ? 5 : -5;
	        robot.mouseMove(x, y);
	    }

	    for (int i = 0; i < 20; i++) {
	        x -= xModifier > 0 ? 5 : -5;
	        y -= yModifier > 0 ? 5 : -5;
	        robot.mouseMove(x, y);
	    }

	    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	    Thread.sleep(500);
	    Dimension newSize = frame.getSize();
	    System.out.println("Resized Dimensions: " + newSize);
	}

	private static void dragTopLeftCorner(JFrame frame, Robot robot) throws InterruptedException {
	    dragCorner(frame, robot, 5, 5);
	}

	private static void dragTopRightCorner(JFrame frame, Robot robot) throws InterruptedException {
	    dragCorner(frame, robot, -5 + frame.getWidth(), 5);
	}

	private static void dragBottomLeftCorner(JFrame frame, Robot robot) throws InterruptedException {
	    dragCorner(frame, robot, 5, -5 + frame.getHeight());
	}

	private static void dragBottomRightCorner(JFrame frame, Robot robot) throws InterruptedException {
	    dragCorner(frame, robot, -5 + frame.getWidth(), -5 + frame.getHeight());
	}

	private static void dragLeftEdge(JFrame frame, Robot robot) throws InterruptedException {
	    dragEdge(frame, robot, 5, frame.getHeight() / 2);
	}

	private static void dragRightEdge(JFrame frame, Robot robot) throws InterruptedException {
	    dragEdge(frame, robot, -5 + frame.getWidth(), frame.getHeight() / 2);
	}

	private static void dragTopEdge(JFrame frame, Robot robot) throws InterruptedException {
	    dragEdge(frame, robot, frame.getWidth() / 2, 5);
	}

	private static void dragBottomEdge(JFrame frame, Robot robot) throws InterruptedException {
	    dragEdge(frame, robot, frame.getWidth() / 2, -5 + frame.getHeight());
	}

    
    private static void doubleClickTitleBar(JFrame frame, Robot robot) throws InterruptedException {
        int x = frame.getX() + frame.getWidth() / 2;
        int y = frame.getY() + 21; // Half of 42 pixels to be in the middle of the title bar

        // Move to the title bar and double-click to maximize
        robot.mouseMove(x, y);
        Thread.sleep(500);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(100);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(1000); // Wait for the window to maximize

        // Move to near the top of the screen and double-click to restore
        robot.mouseMove(x, 10); // Move mouse near the top of the screen
        Thread.sleep(500);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(100);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(1000); // Wait for the window to restore
    }
    
    private static void maximizeAndDragWindow(JFrame frame, Robot robot) throws InterruptedException {
        int x = frame.getX() + frame.getWidth() / 2;
        int y = frame.getY() + 21; // Half of 42 pixels to be in the middle of the title bar

        // Move to the title bar and double-click to maximize
        robot.mouseMove(x, y);
        Thread.sleep(500);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(100);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(1000); // Wait for the window to maximize

        // Move to near the top of the screen
        y = 10;
        robot.mouseMove(x, y);
        Thread.sleep(500);

        // Press to start dragging
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);

        // Drag the window by moving the mouse
        
        for (int i = 0; i < 20; i++) {
            x += 5;
            y += 5;
            robot.mouseMove(x, y);
            Thread.sleep(50);
        }

        // Release the mouse button to drop the window
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(500);
    }
    
    private static void dragTopEdgeBUG(JFrame frame, Robot robot) throws InterruptedException {
	    dragEdge(frame, robot, frame.getWidth() / 2, 5);
	}
    
}
