package com.gmail.pshore.snake.clientio;

import java.util.Observable;

import com.gmail.pshore.snake.game.GameController;
import com.gmail.pshore.snake.game.GameScreenGrid;
import com.gmail.pshore.snake.game.Player;
import com.gmail.pshore.snake.game.PlayerComms;

/**
 * Handles the user input and output. This includes
 * Listening for user input and actioning the request with the game controller. 
 * Handling screen updates and actioning updates to the users screen. 
 * 
 * @author Phil Shore pshore2@gmail.com
 *
 */
public class TextPlayer extends Observable implements Player {
	
	private PlayerComms playerComms;
	private ScreenOutputter screenOutputter;
	
	@Override
	public PlayerComms joinGame(GameController gameController) {
		playerComms = gameController.addPlayer(this);

		return playerComms;
	}


	@Override
	public void updateDisplay(GameScreenGrid screenGrid) {
		screenOutputter.outputScreen(screenGrid);
	}		
	
	
	public ScreenOutputter getScreenOutputter() {
		return screenOutputter;
	}

	public void setScreenOutputter(ScreenOutputter screenOutputter) {
		this.screenOutputter = screenOutputter;
	}	
	
}
