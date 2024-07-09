package view.window;

import java.awt.event.WindowStateListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import view.window.Window;

public class StateListener implements WindowStateListener {
    private Listener listener;
    private Window window;
    private int previousState = JFrame.NORMAL;

    public StateListener(Listener listener) {
        this.listener = listener;
        this.window = listener.window;
    }

    @Override
    public void windowStateChanged(WindowEvent e) {
        if (e.getNewState() == JFrame.ICONIFIED) {
            previousState = e.getOldState();
        }

        if (e.getOldState() == JFrame.ICONIFIED
                && (e.getNewState() == JFrame.NORMAL || e.getNewState() == JFrame.MAXIMIZED_BOTH)) {
            window.setExtendedState(previousState);
        }
    }
}

