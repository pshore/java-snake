package com.gmail.pshore.snake.game;

/** 
 * A player deals with the input and output to the person playing 
 */
public interface Player {
	
	/** 
	 * Join the game controlled by the given GameController. 
	 * The request may be denied if the game is full. 
	 * Joining will result in waiting for the next game to start.
	 */ 
	PlayerComms joinGame(GameController gameController);

	/** Instruct the Player to update its display to the end user. */
	void updateDisplay(GameScreenGrid screenGrid);
}
