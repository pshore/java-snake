package com.gmail.pshore.snake;

import java.util.ArrayList;
import java.util.List;

public class SnakeCharacter {

	List<Gridref> positions = new ArrayList<Gridref>();

	/** Initialise the Snake with no initial head. */
	public SnakeCharacter() {		
	}
	
	
	/** 
	 * Initialise the Snake, optionally with a single head at the given position.
	 * @param createHead pass true to create the head of the snake. 
	 * @param x the X coordinate on the screen. 0 is leftmost.
	 * @param y the Y coordinate on the screen. 0 is topmost.
	 */
	public SnakeCharacter(boolean createHead, int x, int y) {
		if(createHead) {
			positions.add(new Gridref(x,y));
		}
	}
	
	
	/** 
	 * Construct a Snake from a two dimensional array.
	 * Each element in the outer array must contain two integers with x at index 0, and y at index 1.
	 * 
	 * @param refs
	 */
	public SnakeCharacter( int[][] refs) {
		add(refs);
	}
	
	/**
	 * @return the positions
	 */
	public List<Gridref> getPositions() {
		return positions;
	}	

	
	/** Adds positions to the end of the snake */
	public boolean add( int[][] refs ) {
		if(refs==null) return false; // leave the snake empty.
		if(refs.length==0) return false;
		
		for(int i=0; i<refs.length; i++) {
			int[] ref = refs[i];
			this.add(ref[0], ref[1]);
		}
		
		return true;
	}
	
	/** Adds a position to the end of the snake */
	public boolean add(int x, int y) {
		return positions.add( new Gridref(x,y) );
	}

	/** Adds a position to the end of the snake */
	public boolean add( Gridref ref) {
		return positions.add( ref );
	}
	
	
	public void followLeft()  { follow( new Gridref(-1, 0) ); }
	public void followRight() { follow( new Gridref(+1, 0) ); }
	public void followUp()    { follow( new Gridref( 0,-1) ); }
	public void followDown()  { follow( new Gridref( 0,+1) ); }
	
	/**
	 * Move the Snake head in the direction indicated.
	 * 
	 * To move...
	 * Left:  pass -1,0.
	 * Right: pass +1,0.
	 * Up:    pass 0,-1.
	 * Down:  pass 0,+1.
	 * 
	 * This function moves the head in the direction, and the rest of the body will
	 * follow the trail of the body.
	 * 
	 * @param relative A relative grid reference. Accepted values are -1, 0, or +1. 
	 *  
	 * 
	 * Diagonal moves can be supported.
	 * Moving more than one 
	 * @return 
	 */
	public Gridref follow(Gridref relative) throws IndexOutOfBoundsException {
		// check the input
		if(relative==null) 
			throw new NullPointerException("Cannot follow a 'null' relative reference");
		if(positions.isEmpty())
			throw new IndexOutOfBoundsException("An empty snake cannot follow a direction");
		// check the movement is within range
		if(relative.getX()<-1 || relative.getX()>1)
			throw new IndexOutOfBoundsException("X is out of range");
		if(relative.getY()<-1 || relative.getY()>1)
			throw new IndexOutOfBoundsException("Y is out of range");

		// now follow the direction
		int headX = positions.get(0).getX();
		int headY = positions.get(0).getY();
		
		positions.remove( positions.size()-1 ); // remove the tail
		Gridref newHead = new Gridref( headX+relative.getX(), headY+relative.getY() );		
		positions.add(0, newHead); // add the new head at the front.
		
		return positions.get(0);
	}

	/**
	 * Check the size and positions are equal.
	 * 
	 * @param refs
	 * @return boolean true if the same size and positions.
	 */
	public boolean sameGridrefs( int[][] refs) {
		SnakeCharacter c = new SnakeCharacter( refs );

		if( this.positions.isEmpty() && c.positions.isEmpty() ) return true;
		if( this.positions.isEmpty() != c.positions.isEmpty() ) return false;
		if( this.positions.size()    != c.positions.size()    ) return false;
		
		for(int i=0; i<this.positions.size(); i++) {
			if( ! this.positions.get(i).equals( c.getPositions().get(i) ) )
				return false;
		}
		
		return true;
	}
	
	
	
}
