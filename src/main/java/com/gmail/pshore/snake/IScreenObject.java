package com.gmail.pshore.snake;

import java.util.List;
import com.gmail.pshore.snake.Gridref;

/** 
 * An object that can be placed on a screen. 
 * 
 * This is described by a List of GridRef positions aligned to a screen size.
 * 
 * @author Phil Shore pshore2@gmail.com
 */
public interface IScreenObject {

	/** Gets a List of GridRef positions */
	List<Gridref> getPositions();
	
	/** Sets a List of GridRef positions */
	void setPositions(List<Gridref> positions);
	
	/**
	 * Checks if this ScreenObject contains the given position. 
	 * 
	 * This would indicate a collision for example.
	 * 
	 * @return true when the Snake occupies the same as the given position.
	 */
	public default boolean contains(Gridref position) {

		for(Gridref i : getPositions()) {
			if(i.equals(position))
				return true;
		}

		return false;
	}


	/** Checks if this ScreenObject has any of the given positions. */
	public default boolean containsAny(List<Gridref> positions) {
		
		for(Gridref position : positions) {
			if( contains(position) )
				return true;
		}
		
		return false;
	}	
	
}
