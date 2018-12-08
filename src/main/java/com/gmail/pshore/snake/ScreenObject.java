package com.gmail.pshore.snake;

import java.util.ArrayList;
import java.util.List;

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
	
}
