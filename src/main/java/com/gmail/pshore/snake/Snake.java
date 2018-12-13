package com.gmail.pshore.snake;

import com.gmail.pshore.snake.clientio.TextPlayerAutomatic;
import com.gmail.pshore.snake.clientio.VtAnsiScreenOutputter;
import com.gmail.pshore.snake.game.GameController;
import com.gmail.pshore.snake.game.GameScreenGrid;

/**
 * An implementation of the classic Snake game in a terminal window.
 * 
 * This is not optimised for memory or speed. It is to practise coding.
 * 
 * 
 * The main logic of the game is in GameController with I/O for the player via PlayerComms.
 * 
 * GameController and PlayerComms are Observables and watch each other for state changes.
 * 
 * PlayerComms receives input from the user, and records their desired move, and notifies
 * GameController who then decides if the move should be actioned.
 * 
 * GameController applies all the sequence of moves, then notifies PlayerComms that the
 * screen as displayed to the user can be updated.  All characters in the game are in
 * GameScreenGrid and this can be used to output to any screen type.
 * 
 * The Player type that joined the game will have a updateDisplay method implemented,
 * which determines how to translated the GameScreenGrid ScreenObjects (eg the Snake)
 * into something the user sees.
 * 
 * 
 * @author Phil Shore pshore2@gmail.com
 */
public class Snake {

	int screenW  = 80;
	int screenH  = 25;
	
	/** Constructor */
	public Snake() {
		
	}

	
	/** 
	 * The main loop of the game 
	 */
	void startGame() throws InterruptedException {
		
		// Setup the main game and thread
		GameScreenGrid mainScreen = GameScreenGrid.createWithSize(screenW, screenH);
		GameController gameController = GameController.makeNewGame(mainScreen);
		Thread controllerThread = new Thread(gameController);
		controllerThread.start();
		
		// add a robot player.  Input is automatic, output is to stdout.
		TextPlayerAutomatic robotPlayer = new TextPlayerAutomatic();
		robotPlayer.setScreenOutputter( new VtAnsiScreenOutputter( System.out ) );
		robotPlayer.joinGame(gameController);
		
		// the game starts.
	}	
	
	/** Main function 
	 * @throws InterruptedException */
	public static void main(String[] args) throws InterruptedException {
		
		System.out.println("Welcome to Snake");
		Snake snakegame = new Snake();
		snakegame.startGame();
	}

}

