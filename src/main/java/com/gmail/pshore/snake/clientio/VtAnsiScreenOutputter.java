package com.gmail.pshore.snake.clientio;

import java.io.PrintStream;
import java.util.List;

import com.gmail.pshore.snake.game.GameScreenGrid;
import com.gmail.pshore.snake.game.screen.Gridref;
import com.gmail.pshore.snake.game.screen.ScreenObject;
import com.gmail.pshore.snake.game.screen.SnakeCharacter;

public class VtAnsiScreenOutputter implements ScreenOutputter {

	PrintStream out;

	/** Contructs the ScreenOutputter with the PrintStream where the game will be viewed. */
	public VtAnsiScreenOutputter(PrintStream out) {
		this.out = out;
	}
	
	
	/** Outputs one frame of the game action. */
	@Override
	public void outputScreen(GameScreenGrid screenGrid) {
				
		startScreenOutput();
		
		for(ScreenObject screenObject : screenGrid.getLayeredScreenObjects() )
			outputScreenObject(screenObject);
		
		endScreenOutput();
		
	}

	/** Clears the screen and prepares the terminal for outputting each ScreenObject. */
	private void startScreenOutput() {
		out.print( VtAnsi.attribute(VtAnsi.ATTR_HIDDEN, VtAnsi.ATTR_FG_RED) );	// hide the cursor
		out.print( VtAnsi.eraseAndHome() ); 				// clear the entire screen
	}

	/** Do any final tasks to the terminal to leave a good looking screen. */
	private void endScreenOutput() {
		out.print( VtAnsi.cursorTo(0, 0) );
		out.print( VtAnsi.attribute(VtAnsi.ATTR_HIDDEN) );	// hide the cursor
	}
	
	/** 
	 * Write a single ScreenObject (eg a Snake) to the terminal.
	 * 
	 * @param screenObject
	 */
	private void outputScreenObject(ScreenObject screenObject) 
	{
		List<Gridref> allGridrefs = screenObject.getPositions();
		for( int i=0 ; i< allGridrefs.size() ; i++ ) {
			
			Gridref gridref = allGridrefs.get(i);
			int column = gridref.getX();
			int line   = gridref.getY();

			out.print( VtAnsi.cursorTo(line, column) );
			
			// later we'll push this into separate classes
			if(screenObject instanceof SnakeCharacter) {
				if( i==0 )
					out.print('%'); // the head char
				else
					out.print('O'); // the body char
			}
		}
	}


	public PrintStream getOut() {
		return out;
	}
	
	public void setOut(PrintStream out) {
		this.out = out;
	}
	
}
