package com.gmail.pshore.snake;

import java.util.List;

/** 
 * The TextScreen upon which the Snake will be drawn.
 * 
 * @author Phil Shore pshore2@gmail.com
 */
public class SnakeTextFrame extends TextFrame {
	
	char HEAD_CHAR='@';
	char BODY_CHAR='o';
	
	SnakeCharacter snake;

	
	public SnakeTextFrame(int width, int height) {
		super(width, height); // setup the rows.
	}

	/* (non-Javadoc)
	 * @see com.gmail.pshore.snake.TextScreen#drawFrame()
	 */
	@Override
	void updateFrame() {
		reset();
		drawSnake();
	}

	/**
	 * Draw the Snake onto this frame.
	 * 
	 * Assumes that the Snake has not gone off screen.
	 */
	void drawSnake() {
		
		List<Gridref> gridrefs = snake.getPositions();
		for(int g=0; g<gridrefs.size(); g++) {
			Gridref gridref = gridrefs.get(g);
			
			// set the character at the gridref position
			rows.get(gridref.getY())
				.setCharAt(gridref.getX(), (g==0 ? HEAD_CHAR : BODY_CHAR) );
		}
		
	}

	/**
	 * @param snake The snake to set
	 */
	public void setSnake(SnakeCharacter snake) {
		this.snake = snake;
	}
}
