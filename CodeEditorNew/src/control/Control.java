package control;

import model.Model;
import view.View;

public class Control {

	private Model model;
    private View view;

    public Control(Model model, View view) {
        this.model = model;
        this.view = view;

        // Add action listener to the button
//        this.view.getIncrementButton().addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                model.increment();
//                view.getCountLabel().setText(String.valueOf(model.getCount()));
//            }
//        });
        Listener mouseListener = new Listener(view.getWindow());
    }
    
}
