package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas {

	/**
	 *  Sets up the window for the game. Use "Ctrl + Shift + o" to add imports
	 */
	private static final long serialVersionUID = -240840600533728354L;

	//Window is created here
	public Window( int width, int height, String title, Game game) {
		//creates a JFrame, which is the window
		JFrame frame = new JFrame(title);
		
		//Setting Dimensions
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		//Allows you to close window using the "X" button in the top right
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Asks if we can resize our window - set to false, or no you cannot
		frame.setResizable(false);
		//Not necessary, but puts screen in the center rather than the default top right
		frame.setLocationRelativeTo(null);
		//Adds the game class into the frame
		frame.add(game);
		//Allows us to see the frame
		frame.setVisible(true);
		//Runs the start method in the game file, starts thread in game file
		game.start();
	}
	
}
