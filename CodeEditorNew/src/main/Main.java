package main;
import control.Control;
import model.Model;
import view.View;

public class Main {
	public static void main(String[] args) {
		Model model = new Model();
		View view = new View();
		Control control = new Control(model, view);
	}
}
