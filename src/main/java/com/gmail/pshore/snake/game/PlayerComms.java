package com.gmail.pshore.snake.game;

import java.util.Observable;
import java.util.Observer;

/** 
 * A communications bridge between a Player and the GameController. 
 * 
 * This is created by the GameController after joining a game.
 * The Player will use this to make moves.
 */
public class PlayerComms extends Observable implements Observer {

	private Player player;
	private GameController gameController;

	private Move lastMove;
	
	/** Private constructor */
	@SuppressWarnings("unused")
	private PlayerComms() {}

	public PlayerComms( Player player, GameController gameController) {
		this.player = player;
		this.gameController = gameController;
		receiveUpdatesFrom(gameController);
	}

	/** Setup Observations on the GameController. Updates are received via the update method. */
	private void receiveUpdatesFrom(GameController gameController) {
		if(gameController instanceof Observable) {
			Observable observableGame = (Observable) gameController;
			observableGame.addObserver(this);
		}
	}		
	
	/** 
	 * Move a move for this player.
	 * In Snake, only the last direction requested matters, so overwrite it.
	 * 
	 * @param move The direction for the snake to move in.
	 * @return
	 */
	public void makeMove(Move move) {
		lastMove = move;
		notifyObservers(move);
	}
	
	/** Gets the last direction requested by the player */
	Move getLastMove() {
		return lastMove;
	}

	/** 
	 * Gets the last move or the default one provided.
	 * 
	 * The gameController may force a move upon us via this method. 
	 * 
	 * @param defaultMove
	 * @return a Move, never null.
	 */
	public Move getLastMove( Move defaultMove ) {
		if(lastMove==null)
			lastMove = defaultMove;
		
		return lastMove;
	}	
	
	/** 
	 * Receive updates on GameController events we are watching out for.
	 */
	public void update(Observable observable, Object notifyArg) {

		if( gameScreenNeedsUpdating(observable,notifyArg) ) {
			GameScreenGrid mainScreen = (GameScreenGrid) notifyArg;
			player.updateDisplay(mainScreen);
		}
		
	}	
	
	private boolean gameScreenNeedsUpdating(Observable observable, Object notifyArg)
	{
		return (observable instanceof GameController && notifyArg instanceof GameScreenGrid) ? true : false;
	}
	
	public Player getPlayer() {
		return player;
	}

	public GameController getGameController() {
		return gameController;
	}
	
}
