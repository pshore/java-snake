package com.gmail.pshore.snake.clientio;

import java.util.Observable;

import com.gmail.pshore.snake.game.GameController;
import com.gmail.pshore.snake.game.GameScreenGrid;
import com.gmail.pshore.snake.game.GameStatus;
import com.gmail.pshore.snake.game.Move;
import com.gmail.pshore.snake.game.Player;
import com.gmail.pshore.snake.game.PlayerComms;

/**
 * Automatically moves a characters in a pre-planned sequence of moves. 
 * This is useful for testing. 
 * 
 * @author Phil Shore pshore2@gmail.com
 *
 */
public class TextPlayerAutomatic extends Observable implements Player, Runnable {
	
	private PlayerComms playerComms;
	private Thread makeMovesThread;
	private ScreenOutputter screenOutputter;
	

	// some automated moves - in a circle
	Move[] moveSequence = new Move[] { Move.LEFT, Move.LEFT, Move.LEFT, Move.DOWN, Move.DOWN, Move.DOWN, Move.RIGHT, Move.RIGHT, Move.RIGHT, Move.UP, Move.UP, Move.UP };
	int moveIndex = -1;
	long timeBetweenMovesMs = 500;


	/**
	 * Gets this Automatic Player into the game.
	 * 
	 * A thread is started that will make automated moves while the game is running.
	 */
	@Override
	public PlayerComms joinGame(GameController gameController) {
		playerComms = gameController.addPlayer(this);

		createAndStartAutoPlayerThread();
		
		return playerComms;
	}

	
	private void createAndStartAutoPlayerThread() {
		this.makeMovesThread = new Thread(this);
		makeMovesThread.start();
	}

	/**
	 * The behaviour for the automatic player is coded here.
	 */
	@Override
	public void run() {
		

		do {
			
			GameStatus gameStatus = playerComms.getGameController().getGameStatus();
			
			switch( gameStatus ) {
				case NEW: 
					/* do nothing until the game starts */
					break;
				case STARTED:
					makeNextMove();
					break;
				case ENDED:
					return;
			}
			
			try {
				Thread.sleep(timeBetweenMovesMs);
			} catch (InterruptedException e) {
				if(Thread.interrupted()) System.err.println("TextPlayerAutomatic Thread interrupted");
			}
				
		} while(true);
	}

	/** Picks out the next move to make */
	private void makeNextMove() {

		if( moveIndex < 0  ||  moveIndex >= moveSequence.length )
			moveIndex=0;
		
		playerComms.makeMove( moveSequence[moveIndex++] );
	}	

	
	/** Update the display using a (Text) ScreenOutputter */
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
