package com.gmail.pshore.snake;

/**
 * An implementation of the classic Snake game in a terminal window.
 * 
 * This is not optimised for memory or speed. It is to practise coding.
 * 
 * @author Phil Shore pshore2@gmail.com
 */
public class Snake {

	int screenW  = TextFrame.DEFAULT_WIDTH;
	int screenH = TextFrame.DEFAULT_HEIGHT;
	
	/** Constructor */
	public Snake() {
		
	}
	
	
	/** The main loop of the game */
	void startGame() throws InterruptedException {
		
		// a screen to draw the snake on.
		SnakeTextFrame snakeScreen = new SnakeTextFrame(screenW, screenH);

		// create the Snake with head in the middle of the screen.
		SnakeCharacter snake = new SnakeCharacter(screenW/2, screenH/2);
		snakeScreen.setSnake(snake); // put it on the screen.

		
		/* Demo the moves. There is no user control yet. */
		
		// some automated moves before we write the user control feature.
		int[][] moves = new int[][] { {-1,0},{-1,0},{-1,0},{0,1},{0,1},{0,1},{1,0},{1,0},{1,0},{0,-1},{0,-1},{0,-1} };
		
		// Test drawing the screen 
		for(int i=0; i<moves.length; i++) {
			snakeScreen.updateFrame();
			TextFrame.outputFrame(snakeScreen);// print to stdout
			Thread.sleep(250);
			snake.follow( new Gridref(moves[i][0], moves[i][1]) );
		}
		
	}
		
	/** Main function 
	 * @throws InterruptedException */
	public static void main(String[] args) throws InterruptedException {
		
		System.out.println("Welcome to Snake");
		Snake snakegame = new Snake();
		snakegame.startGame();
	}

}

