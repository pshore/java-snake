package com.gmail.pshore.snake.game;

/** 
 * The moves that a Player can make 
 * 
 * These are the moves that the GameController understands.  Code listening to
 * user input (eg arrow keys pressed in a terminal window) should convert to 
 * a value in this enumeration.
 */
public enum GameStatus {
	NEW,
	STARTED,
	ENDED
}
