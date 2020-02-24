package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {
	
	/**
	 * Setting up Game 
	 */
	private static final long serialVersionUID = 1550691097823471818L;
	
	//Sets window values
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	//Creates thread - the entire game is run within the thread
	//This game will be built with one thread, but it is not recommended to use only one
	private Thread thread;
	//Is the thread running or not
	private boolean running = false;
	
	public Game() { // "this" refers to the game parameter
		new Window(WIDTH, HEIGHT, "It's Gaming Time", this);
	}

	public synchronized void start() { // "this" refers to this file, this "Game" class
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		//Try is like an if statement, it will do something but if it doesn't work then it will run the catch
		try {
			//kills thread, stops it
			thread.join();
			running = false;	
		}catch(Exception e) {
			//Runs an error bug in console
			e.printStackTrace();
		}
	}
	
	//Game Loop - Necessary for the game to run, without it it cannot update itself
	//Every game has a version of the game loop
	public void run() {
		//Popular game loop - recommended
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running) 
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		//sets color of screen to black
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Game();
	}
	/**
	 * Setting up Game Ends
	 */
	
	
}
