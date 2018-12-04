package com.gmail.pshore.snake;

import java.util.ArrayList;
import java.util.List;

public abstract class TextScreen {

	static int DEFAULT_WIDTH=80;
	static int DEFAULT_HEIGHT=25;
	
	int width=DEFAULT_WIDTH;
	int height=DEFAULT_HEIGHT;
	
	List<StringBuffer> rows = null;
	
	/** Default constuctor */
	public TextScreen() {
		initData();
	}
	
	/** Alternative constructor */
	public TextScreen(int width, int height) {
		this.width=width;
		this.height=height;	
		initData();
	}
	
	/** Initialise a two dimensional data structure representing the screen characters. */
	private void initData() {
		rows = new ArrayList<StringBuffer>(this.height);
		for(int i=0; i<this.height; i++)
			rows.add(new StringBuffer(this.width));
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
}
