package com.tutorial.main;

import java.awt.Graphics;
import java.awt.Rectangle;

//This holds all of the game objects
public abstract class GameObject {

	//"protected" means that only the objects that inherit the GameObject can access the variable.
	//Basically means we set up here and create specific objects using the parts we put here
	protected int x, y;
	//Names "ID" file as "id"
	protected ID id;
	protected int velX, velY;
	
	//Constructor needed for GameObject: what we set in here is set to the protected integers above ^^^
	public GameObject(int x, int y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	//Used within our other classes (Player, Enemy, Coins, etc.)
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	//Gets a value then sets the value inside the code
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setID(ID id) {
		this.id = id;
	}
	public ID getId() {
		return id;
	}
	public void setVelX(int velX) {
		this.velX = velX;
	}
	public void setVelY(int velY) {
		this.velY = velY;
	}
	public int getVelX() {
		return velX;
	}
	public int getVelY() {
		return velY;
	}
	
}
