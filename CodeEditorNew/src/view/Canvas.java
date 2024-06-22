package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import utils.Globals;

public class Canvas extends JPanel {

	private Window window;
	private Background background;
	private TitleBar titleBar;
	private CloseButton closeButton;
	private TrayButton trayButton;
	private MaxButton maxButton;

	public Canvas(Window window) {
		this.window = window;

		setBounds(0, 0, window.width, window.height);
		setBackground(new Color(0, 0, 0, 0));
		setOpaque(false);

		this.background = new Background(window);
		this.titleBar = new TitleBar(window);
		this.closeButton = new CloseButton(window);
		this.trayButton = new TrayButton(window);
		this.maxButton = new MaxButton(window);
		
		// Set up Timer to call actionPerformed every 16 ms (~60 FPS)
        Timer timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // This method will be called every 16 milliseconds
                updateAnimation(); // Update any animations or states
                repaint(); // Request a repaint
            }
        });
        timer.start(); // Start the timer
        
	}
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        setBounds(0, 0, window.width, window.height);

        Graphics2D g2d = (Graphics2D) g;
        Globals.setRenderingHints(g2d);

        background.draw(g2d);
        titleBar.draw(g2d);
        closeButton.draw(g2d);
        trayButton.draw(g2d);
        maxButton.draw(g2d);

        g2d.dispose();
    }

    private void updateAnimation() {
        // Update the state of the animation or any other dynamic content
        // For example, move objects, change colors, etc.
    }
    
    public Background getCanvasBackground() {
    	return background;
    }
    
    public TitleBar getTitleBar() {
    	return titleBar;
    }
    
    public CloseButton getCloseButton() {
    	return closeButton;
    }
    
    public TrayButton getTrayButton() {
    	return trayButton;
    }
    
    public MaxButton getMaxButton() {
    	return maxButton;
    }
}
