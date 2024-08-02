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
		ArrayList<String> fileNameList = new ArrayList<>();
		fileNameList.add("Box.java");
		fileNameList.add("BoxContent.java");
		fileNameList.add("DisplayedLine.java");
		fileNameList.add("LineNumberContainer.java");
		fileNameList.add("LineTextContainer.java");
		fileNameList.add("ScrollerHorizontal.java");
		fileNameList.add("ScrollerVertical.java");

		openBox(fileNameList, path, locX, locY);

		fileNameList.clear();
		locY += 700;
		fileNameList.add("Box.java");

		openBox(fileNameList, path, locX, locY);
	}

	private void openBox(ArrayList<String> fileNameList, String path, int startingLocX, int startingLocY) {
		int shiftX = 600;
		int locX = startingLocX;
		for (String fileName : fileNameList) {
			String accuratePath = Globals.findFilePath(fileName, path);
			System.out.println("accuratePath: " + accuratePath);
			if (accuratePath != null) {
				System.out.println("Found in directory: " + accuratePath);
			} else {
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
		view.getWindow().getListener().getMouseWheelListener().resetZoom();

		if (save()) {
			Toolkit.getDefaultToolkit().getSystemEventQueue()
					.postEvent(new WindowEvent(view.getWindow(), WindowEvent.WINDOW_CLOSING));
		} else {
			System.out.println("Failed to save the file.");
		}
	}

	private boolean save() {

		String saveFilename = "save.txt";
		String stringToSave = "";
		boolean success = true;

		stringToSave += getSourceFolder();

		for (Box box : boxController.getBoxMap().values()) {
			System.out.println(box.toString());
		}

		if (!ReadWrite.writeFileInResources(saveFilename, stringToSave)) {
			success = false;
		}

		return success;

	}

}
