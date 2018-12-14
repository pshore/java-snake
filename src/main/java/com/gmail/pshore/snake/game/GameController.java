package com.gmail.pshore.snake.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import com.gmail.pshore.snake.game.screen.MovableCharacter;
import com.gmail.pshore.snake.game.screen.ScreenObject;
import com.gmail.pshore.snake.game.screen.SnakeCharacter;

/**
 * Represents the master controller of the game.  
 * Players register here to play the game via addPlayer.
 * Players should observe the game and will be notified of updates to the game.
 * 
 * @author Phil
 *
 */
public class GameController extends Observable implements Observer, Runnable {

	public int max_players = 1;
	int timeBetweenFrameUpdatesMs = 500;
	GameStatus gameStatus = GameStatus.NEW;
	
	List<PlayerComms> allPlayerComms = new ArrayList<PlayerComms>();
	
	Map<Player,MovableCharacter> allPlayerCharacters = new HashMap<Player,MovableCharacter>();
	
	GameScreenGrid mainScreen;
	
	


	/** Default constructor makes a 1 player game. */
	private GameController() {
	}
	
	public static GameController makeNewGame(GameScreenGrid mainScreen) {
		GameController gameController = new GameController();
		gameController.setMainScreen(mainScreen);
		return gameController;
	}
	
	public PlayerComms addPlayer(Player player) {
		if( gameIsFull() )
			return null;
		else {
			// setup player comms
			PlayerComms playerComms = new PlayerComms(player, this);
			receiveUpdatesFrom(playerComms); // setup observations
			allPlayerComms.add(playerComms);
						
			// put the character into the ScreenGrid so it can be drawn.
			
			return playerComms;
		}
	}


	/**
	 * The main loop that runs the game.
	 */
	@Override
	public void run() {
		
		while( !gameIsReadyToStart() ) {
			sleep(timeBetweenFrameUpdatesMs);
		}
		
		createCharacters(mainScreen);
		addCharactersToScreen(mainScreen);

		setGameStatus(GameStatus.STARTED);
		
		do {
			switch( getGameStatus() ) {
				case ENDED:
					System.out.println("GameController: game has ended.");
					return;
				case STARTED:
					makeMoves();
					updateScreen();
					break;
				case NEW:
					System.out.println("GameController.run: NEW status detected.");
					break;
				default:
					System.out.println("GameController.run: unknown status detected.");					
					break;			
			}
			
			sleep(timeBetweenFrameUpdatesMs);			
			
		} while ( gameHasNotEnded() );
	}

	
	/** Sleep the current thread for the given number of milliseconds */
	private void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) { e.printStackTrace(); }
	}

	
	/** Add characters to the screen so frames can be drawn. */
	private void addCharactersToScreen(GameScreenGrid screen) {
		for(MovableCharacter character : allPlayerCharacters.values()) {
			screen.addScreenObject( (ScreenObject) character );
		}
	}

	/** Create characters. This needs to happen after a screen grid is created. */
	private void createCharacters(GameScreenGrid screenGrid) {
		for(PlayerComms comms : allPlayerComms) {
			Player player = comms.getPlayer();
			
			// give the player a character. Place it in the middle of the screen.
			int x = screenGrid.getWidth()/2;
			int y = screenGrid.getHeight()/2;
			MovableCharacter character = new SnakeCharacter(x,y);
			allPlayerCharacters.put(player, character);
		}
	}

	/** Notify the PlayerComms observers that the screen can be updated for their Player. */
	private void updateScreen() {
		this.setChanged();
		//System.out.println("Sending update re screen observers x"+this.countObservers());
		notifyObservers(mainScreen);
	}

	/**
	 * Make all the moves in the game for this frame of the game.
	 */
	private void makeMoves() {
		for( PlayerComms playerComms : allPlayerComms ) {
			Player player = playerComms.getPlayer();
			
			MovableCharacter character = allPlayerCharacters.get(player);

			Move direction = playerComms.getLastMove( Move.LEFT );			
			character.move( direction );
			
		}
	}

	/** 
	 * Is the game ready to run or is still in play. 
	 * 
	 * This is used for the main game loop.
	 */
	private boolean gameHasNotEnded() {
		if( gameStatus!=GameStatus.ENDED )
			return true;
		else
			return false;
	}
	
	/** 
	 * Attempt a move.
	 * 
	 * @return true if the move was accepted, false otherwise.
	 */
	public boolean makeMove(Player player, Move move) {
		return false;
	}

	/** Check if the game has enough properties to start */
	public boolean gameIsReadyToStart() {
		return ( hasEnoughPlayers() && hasScreen() );
	}

	/** Is there a game grid screen for use in the game */
	private boolean hasScreen() {
		return (mainScreen!=null) ;
	}

	/** Are there enough players for a game? */
	private boolean hasEnoughPlayers() {
		return (allPlayerComms!=null && allPlayerComms.size()>0) ;
	}

	
	
	/** Determines if no more players can join. */
	public boolean gameIsFull() {
		return (allPlayerComms.size() < max_players) ? false : true;
	}


	/** Setup Observations on this PlayerComms. Comms are received via the update method. */
	private void receiveUpdatesFrom(PlayerComms playerComms) {
		if(playerComms instanceof Observable) {
			Observable observableComms = (Observable) playerComms;
			observableComms.addObserver(this);
		}
	}	
	
	/** 
	 * Receive updates on Player events we are watching out for.
	 */
	@Override
	public void update(Observable observable, Object notifyArg) {
		
		if( playerIsMoving(observable,notifyArg) ) {
//			Player movingPlayer = (Player) observable;
//			Move playersMove = (Move) notifyArg;
		}

		
	}
	
	/** Has a player has notified us they are moving? */
	private boolean playerIsMoving(Observable observable, Object notifyArg) {
		return (observable instanceof PlayerComms && notifyArg instanceof Move) ? true : false;
	}

	public GameStatus getGameStatus() {
		return gameStatus;
	}

	private void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
		this.notifyObservers(gameStatus);
	}	
	
	public GameScreenGrid getMainScreen() {
		return mainScreen;
	}

	public void setMainScreen(GameScreenGrid mainScreen) {
		this.mainScreen = mainScreen;
	}
	

	
	
}
