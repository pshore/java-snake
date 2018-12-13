package com.gmail.pshore.snake.game;

import java.util.ArrayList;
import java.util.List;

import com.gmail.pshore.snake.game.screen.ScreenObject;

/**
 * Contains all the characters in the game grid, each having positions on the grid.
 * 
 * The clients will determine how to translate the positions of each object into
 * a visible screen for their Player.
 * 
 * @author Phil Shore pshore2@gmail.com
 *
 */
public class GameScreenGrid {

	int width;
	int height;	

	/** 
	 * An ordered list of objects placed on the screen. 
	 * The order represents the drawing order. 
	 * The first item added will be at the bottom with others drawn over the top.
	 */
	private List<ScreenObject> layeredScreenObjects = new ArrayList<ScreenObject>();
	
	public List<ScreenObject> getLayeredScreenObjects() {
		return layeredScreenObjects;
	}

	/** Add the given screen object on top of the others in this grid. */
	public boolean addScreenObject(ScreenObject screenObject) {
		return layeredScreenObjects.add(screenObject);
	}
	
	/** Remove the screen object from this grid. */
	public boolean removeScreenObject(ScreenObject screenObject) {
		return layeredScreenObjects.remove(screenObject);
	}
	
	/** 
	 * Default constructor. Set the width and height next.
	 * Or use createWithSize instead. */
	public GameScreenGrid() {
	}	

	
	public static GameScreenGrid createWithSize(int width, int height) {
		GameScreenGrid screenGrid = new GameScreenGrid();
		screenGrid.setWidth(width);
		screenGrid.setHeight(height);
		
		return screenGrid;
	}
	
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}


	/** Gets the height of the grid */
	public int getHeight() {
		return height;
	}

	/** Sets the height of the grid */
	public void setHeight(int height) {
		this.height = height;
	}


}
