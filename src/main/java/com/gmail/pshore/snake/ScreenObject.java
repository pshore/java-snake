package com.gmail.pshore.snake;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The shared code for the objects and characters that will be laid out on the combined screen.
 * 
 * @author Phil Shore pshore2@gmail.com
 *
 */
public abstract class ScreenObject implements IScreenObject {

	/** List of positions. Accessible for modification by extending classes. */
	List<Gridref> positions = new ArrayList<Gridref>();

	/**
	 * @return the positions
	 */
	@Override
	public List<Gridref> getPositions() {
		return positions;
	}
	
	/**
	 * Sets a List of screen positions for this Snake.
	 * @param positions
	 * @
	 */
	@Override
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
	
	
}
