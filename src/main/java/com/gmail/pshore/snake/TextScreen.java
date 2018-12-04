package com.gmail.pshore.snake;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a complete screenfull of characters in two dimensions.
 * 
 * The data structure makes drawing on the screen simplistic, i.e. each row, top to bottom,
 * Higher performance will be possible with alternative approaches.  
 * 
 * @author Phil Shore pshore2@gmail.com
 */
public abstract class TextScreen {
	
	static int DEFAULT_WIDTH=80;
	static int DEFAULT_HEIGHT=25;
	
	int width;
	int height;
	
	List<StringBuffer> rows = null;
	
	/** Default constructor.  Initialises the data to the default screen size. */
	@SuppressWarnings("unused")
	private TextScreen() {
		initData();
	}
	
	/** Alternative constructor for a specified width and height. */
	public TextScreen(int width, int height) {
		this.width=width;
		this.height=height;	
		initData();
	}
	
	/** Initialise a two dimensional data structure representing the screen characters. */
	private void initData() {
		rows = new ArrayList<StringBuffer>(this.height);
		for(int i=0; i<this.height; i++) {
			StringBuffer sb = new StringBuffer(this.width);
			for(int w=0; w<this.width; w++)
				sb.append(' ');
			rows.add(sb);
		}
	}
	
	/** 
	 * Construct a new frame of text ready to be drawn to the screen.
	 */
	abstract void updateFrame();
	
	/** 
	 * Reset the screen to its initial state prior to anything being drawn on it.
	 * 
	 * Call this once at the start of each frame, before drawing the animated object. 
	 */
	public void reset() {
		initData();
	}
	
	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}	

	/** 
	 * A utility function to output the contents of the screen 
	 * Useful for debugging.
	 */
	public static void outputScreen(TextScreen screen) {
		for(StringBuffer row : screen.rows) {
			System.out.println(row.toString());
		}
	}
	
	
}
