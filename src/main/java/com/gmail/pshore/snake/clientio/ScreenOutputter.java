package com.gmail.pshore.snake.clientio;

import com.gmail.pshore.snake.game.GameScreenGrid;

/**
 * For classes that show a textual view of the Game.
 * 
 * @author Phil Shore pshore2@gmail.com
 */
public interface ScreenOutputter {

	/**
	 * Trigger an output of one frame of the game.
	 * 
	 * @param screenGrid The current state of the characters in the game.
	 */
	void outputScreen(GameScreenGrid screenGrid);
	
}
