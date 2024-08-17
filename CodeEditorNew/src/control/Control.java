package control;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import model.Model;
import utils.ANSIText;
import utils.Globals;
import utils.ReadWrite;
import view.View;
import view.window.Listener;
import view.window.mainUI.component.box.Box;

public class Control {

	private Model model;
	private View view;
	private BoxController boxController;
	private String sourceFolder = "C:\\Users\\Garry Bertalan\\Desktop\\Git_Cloned_Repositories\\CodeEditor\\CodeEditorNew\\src";

	public Control(Model model, View view) {
		this.model = model;
		this.view = view;

		view.getWindow().getListener().setControl(this);

		boxController = new BoxController(model, view, this);
		view.getWindow().getListener().setBoxController(boxController);

		String path = sourceFolder;

		int locX = 160;
		int locY = 100;
//		boxController.createBox(fileName, path, locX, locY);
		
		/*
		ArrayList<String> fileNameList = new ArrayList<>();
		fileNameList.add("Main.java");

		fileNameList.add("Model.java");
		fileNameList.add("View.java");
		fileNameList.add("Control.java");
		
		fileNameList.add("BoxController.java");
		
		fileNameList.add("BoxModel.java");
		
		fileNameList.add("ANSIText.java");
		fileNameList.add("Globals.java");
		fileNameList.add("ReadWrite.java");
		fileNameList.add("Theme.java");
		
		fileNameList.add("Listener.java");
		fileNameList.add("MouseListener.java");
		fileNameList.add("MouseMotionListener.java");
		fileNameList.add("MouseWheelListener.java");
		fileNameList.add("StateListener.java");
		fileNameList.add("Window.java");
		
		fileNameList.add("MainUI.java");
		
		fileNameList.add("Background.java");
		fileNameList.add("CloseButton.java");
		fileNameList.add("DrawPriorityComparator.java");
		fileNameList.add("EdgeEast.java");
		fileNameList.add("EdgeNorth.java");
		fileNameList.add("EdgeSouth.java");
		fileNameList.add("EdgeWest.java");
		fileNameList.add("FileButton.java");
		fileNameList.add("Footer.java");
		fileNameList.add("MaxButton.java");
		fileNameList.add("SidePanelLeft.java");
		fileNameList.add("SidePanelRight.java");
		fileNameList.add("TitleBar.java");
		fileNameList.add("TrayButton.java");
		fileNameList.add("UIComponent.java");
		fileNameList.add("VisualComponent.java");
		
		fileNameList.add("Box.java");
		fileNameList.add("BoxComponent.java");
		fileNameList.add("BoxContent.java");
		fileNameList.add("BoxHeader.java");
		fileNameList.add("DisplayedLine.java");
		fileNameList.add("LineNumberContainer.java");
		fileNameList.add("LineTextContainer.java");
		fileNameList.add("ScrollerHorizontal.java");
		fileNameList.add("ScrollerVertical.java");

		openBox(fileNameList, path, locX, locY);
		*/
		

//		fileNameList.clear();
//		locY += 700;
//		fileNameList.add("Box.java");

//		openBox(fileNameList, path, locX, locY);
		
		
		load("save.txt");
	}

	/**
	 * Opens a series of boxes for each file in the provided list by creating a
	 * graphical box at the specified starting location and updating the
	 * x-coordinate for each subsequent box. The method attempts to find the
	 * accurate file path for each file name in the given path. If the file is not
	 * found, it prints an error message indicating the file was not found. A new
	 * box is then created using the `boxController` for each file, and the
	 * x-coordinate is shifted for the next box.
	 *
	 * @param fileNameList a list of file names for which to create boxes.
	 * @param path         the directory path in which to search for the files.
	 * @param startingLocX the starting x-coordinate for the first box.
	 * @param startingLocY the y-coordinate for all boxes.
	 */

	private void openBox(ArrayList<String> fileNameList, String path, int startingLocX, int startingLocY) {
		int shiftX = 600;
		int locX = startingLocX;
		for (String fileName : fileNameList) {
			String accuratePath = Globals.findFilePath(fileName, path);
			System.out.println("accuratePath: " + accuratePath);
			if (accuratePath == null) {
				System.out.println(ANSIText.red("File not found: " + fileName));
			}
			boxController.createBox(fileName, accuratePath, locX, startingLocY);
			locX += shiftX;
		}
	}

	public BoxController getBoxController() {
		return boxController;
	}

	public String getSourceFolder() {
		return sourceFolder;
	}

	public void closeApp() {
		view.getWindow().getMouseWheelListener().resetZoom();

		if (save("save.txt")) {
			Toolkit.getDefaultToolkit().getSystemEventQueue()
					.postEvent(new WindowEvent(view.getWindow(), WindowEvent.WINDOW_CLOSING));
		} else {
			System.out.println("Failed to save the file.");
		}
	}

	private boolean save(String saveFilename) {

		String stringToSave = "";
		boolean success = true;

		stringToSave += getSourceFolder() + "\n";

		for (Box box : boxController.getBoxMap().values()) {
			System.out.println(box.toString());
			stringToSave += box.getBoxHeader().getHeaderText() + " " + box.getLocX() + " " + box.getLocY() + "\n";

		}

		if (!ReadWrite.writeFileInResources(saveFilename, stringToSave)) {
			success = false;
		}

		return success;

	}
	
	private void load(String saveFilename) {
		ArrayList<String> lines = ReadWrite.readFileInResourcesAsArrayList(saveFilename);

		String generalPath = lines.get(0);
		for (int i = 1; i < lines.size(); i++) {
			
			String fileName = lines.get(i).split(" ")[0];
			int locX = Integer.parseInt(lines.get(i).split(" ")[1]);
			int locY = Integer.parseInt(lines.get(i).split(" ")[2]);
			
			String accuratePath = Globals.findFilePath(fileName, generalPath);
			if (accuratePath == null) {
				System.out.println(ANSIText.red("File not found: " + fileName));
				
			} else {
				boxController.createBox(fileName, accuratePath, locX, locY);
			}
		}
	}

}
