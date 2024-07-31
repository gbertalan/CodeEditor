package control;

import java.util.ArrayList;

import model.Model;
import utils.ANSIText;
import utils.Globals;
import view.View;
import view.window.Listener;
import view.window.mainUI.component.box.Box;

public class Control {

	private Model model;
	private View view;
	private BoxController boxController;

	public Control(Model model, View view) {
		this.model = model;
		this.view = view;
		boxController = new BoxController(model, view, this);
		view.getWindow().getListener().setBoxController(boxController);

		String path = "C:\\Users\\Garry Bertalan\\Desktop\\Git_Cloned_Repositories\\CodeEditor\\CodeEditorNew\\src";

		int locX = 160;
		int locY = 100;
//		boxController.createBox(fileName, path, locX, locY);
		ArrayList<String> fileNameList = new ArrayList<>();
		fileNameList.add("Box.java");
		fileNameList.add("BoxContent.java");

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

}
