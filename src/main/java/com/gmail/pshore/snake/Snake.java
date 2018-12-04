package com.gmail.pshore.snake;

/**
 * An implementation of the classic Snake game in a terminal window.
 * 
 * This is not optimised for memory or speed. It is to practise coding.
 * 
 * @author Phil Shore pshore2@gmail.com
 */
public class Snake {

	int screenW  = TextScreen.DEFAULT_WIDTH;
	int screenH = TextScreen.DEFAULT_HEIGHT;
	
	/** Constructor */
	public Snake() {
		
	}
	
	
	/** The main loop of the game */
	void startGame() throws InterruptedException {
		
		// a screen to draw the snake on.
		SnakeTextScreen snakeScreen = new SnakeTextScreen(screenW, screenH);

		// create the Snake with head in the middle of the screen.
		SnakeCharacter snake = new SnakeCharacter(true, screenW/2, screenH/2);
		snakeScreen.setSnake(snake); // put it on the screen.

		// some automated moves before we write the user control feature.
		int[][] moves = new int[][] { {-1,0},{-1,0},{-1,0},{0,1},{0,1},{0,1},{1,0},{1,0},{1,0},{0,-1},{0,-1},{0,-1} };
		
		// Test drawing the screen 
		for(int i=0; i<moves.length; i++) {
			snakeScreen.updateFrame();
			TextScreen.outputScreen(snakeScreen);// print to stdout
			Thread.sleep(500);
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

