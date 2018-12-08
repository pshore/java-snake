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
public interface ScreenObject {

	/** Gets a List of GridRef positions */
	List<Gridref> getPositions();
	
	/** Sets a List of GridRef positions */
	void setPositions(List<Gridref> positions);
	
	/** Checks if the current ScreenObject has this exact position. */
	boolean contains(Gridref position);

	/** Checks if the current ScreenObject has any of the given positions. */
	boolean containsAny(List<Gridref> positions);

}
