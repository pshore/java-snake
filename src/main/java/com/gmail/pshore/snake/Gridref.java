package com.gmail.pshore.snake;

/**
 * Represents a location on the screen.
 * 
 * Top left is 0,0.
 * One down is 0,1.
 * One Right is 1,0.
 * 
 * Positions can be negative so that relative positions are allowed.
 * 
 * @author Phil Shore pshore2@gmail.com
 *
 */
public class Gridref implements Cloneable {
	
	private int x;
	private int y;
	
	/** Construct a 0,0 Gridref. */
	public Gridref() {
		this.x = 0;
		this.y = 0;
	}
	
	/** Construct a Gridref at a chosen location. */
	public Gridref(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the x position. 
	 * Zero is the leftmost position. Positive numbers are right of 0.
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y position. 
	 * Zero is the uppermost position. Positive numbers are below 0.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Create a new Gridref with the same properties as this one.
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Gridref clone() throws CloneNotSupportedException {
		// this is sufficient while only primitives are used.
		return (Gridref) super.clone();
	}
	
	/** Test if the current grid reference is the same */
	public boolean equals(int x, int y) {
		return (this.x==x && this.y==y);
	}
	
	/**
	 * Return true if x and y values are the same.
	 */
	@Override
	public boolean equals(Object obj) {
		if( obj==null ) return false;
		if( !(obj instanceof Gridref) ) return false; 

		Gridref g = (Gridref) obj;
		return (this.x==g.getX() && this.y==g.getY());
	}
	
}
