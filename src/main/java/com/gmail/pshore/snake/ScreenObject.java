package com.gmail.pshore.snake;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
	 * Check the size and positions are equal.
	 * 
	 * @param refs
	 * @return boolean true if the same size and positions.
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
