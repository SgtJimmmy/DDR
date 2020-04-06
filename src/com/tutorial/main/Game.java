package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

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
	
	private Random r;
	private Handler handler;
	private HUD hud;
	
	public Game() { // "this" refers to the game parameter
		
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		
		new Window(WIDTH, HEIGHT, "It's Gaming Time", this);
		
		hud = new HUD();
		
		r = new Random();
		
		//give Player x, y, and id - each one is an individual object in the game
		
			handler.addObject(new Player(WIDTH/2 - 32, HEIGHT/2 - 32, ID.Player, handler));
			for(int i = 0; i < 3; i++) {
				handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy, handler));
			}
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
		this.requestFocus();
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
		handler.tick();
		hud.tick();
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
		
		handler.render(g);
		
		hud.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public static int clamp(int var, int min, int max) {
		if(var >= max) {
			return var = max;
		}
		else if(var <= min) {
			return var = min;
		}
		else return var;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Game();
	}
	/**
	 * Setting up Game Ends
	 */
	
	
}
