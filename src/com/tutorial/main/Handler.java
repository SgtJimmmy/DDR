package com.tutorial.main;

import java.awt.Graphics;
import java.util.LinkedList;

//Loops throughout the objects in the game, and individually updates them and renders them to the screen
public class Handler {

	LinkedList<GameObject> object = new LinkedList <GameObject>();

	//Loops through every object
	//Basic necessity
	public void tick() {
		//Goes through the list of objects
		for(int i = 0; i < object.size(); i++ ) {
			//Sets temporary object to the value of "i", or each object in our list
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
	}
	
	//After looping through every object, it renders it
	//Basic necessity
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	//Handle adding objects from our list GameObject
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	//Handle removing objects from our list GameObject
	public void moveObject(GameObject object) {
		this.object.remove(object);
	}
	
}
