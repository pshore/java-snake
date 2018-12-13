package com.gmail.pshore.snake.game.screen;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * An object that can be placed on a screen. 
 * 
 * This is described by a List of GridRef positions aligned to a screen size.
 * 
 * The shared code for the objects and characters that will be laid out on the combined screen.
 * 
 * @author Phil Shore pshore2@gmail.com
 *
 */
public abstract class ScreenObject {

	/** List of positions. Accessible for modification by extending classes. */
	List<Gridref> positions = new ArrayList<Gridref>();

	/**
	 * @return the positions
	 */
	public List<Gridref> getPositions() {
		return positions;
	}
	
	/**
	 * Sets a List of screen positions for this Snake.
	 * @param positions
	 * @
	 */
	public void setPositions(List<Gridref> positions) {
		this.positions = positions;
	}	
	
	/**
	 * Check the positions are equal.
	 * 
	 * @return boolean true if this object occupies exactly the same locations, and is the same size.
	 */
	@Override
	public boolean equals(Object obj) {	
		if(obj==null)
			return false;
	
		if( obj.getClass()!=this.getClass() ) 
			return false;
	
		// now compare the internal collections of positions.
		ScreenObject screenObj = (ScreenObject) obj ;
		return this.getPositions().equals(screenObj.getPositions());
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getPositions());
	}		
	

	/**
	 * Checks if this ScreenObject contains the given position. 
	 * 
	 * This would indicate a collision for example.
	 * 
	 * @return true when the Snake occupies the same as the given position.
	 */
	public boolean contains(Gridref position) {

		for(Gridref i : getPositions()) {
			if(i.equals(position))
				return true;
		}

		return false;
	}


	/** Checks if this ScreenObject has any of the given positions. */
	public boolean containsAny(List<Gridref> positions) {
		
		for(Gridref position : positions) {
			if( contains(position) )
				return true;
		}
		
		return false;
	}		
	
}
