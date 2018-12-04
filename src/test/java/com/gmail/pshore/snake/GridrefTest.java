package com.gmail.pshore.snake;

import static org.junit.Assert.*;
import org.junit.Test;
import com.gmail.pshore.snake.Gridref;

public class GridrefTest {

	@Test
	public void testConstructors() {
		Gridref g;
		
		// test the basic constructor is 0,0
		g = new Gridref();
		assertEquals(0, g.getX());
		assertEquals(0, g.getY());

		// setting the value specifically
		g = new Gridref(0,0);
		assertEquals(0, g.getX());
		assertEquals(0, g.getY());
		
		// test positive values, and they are the correct way round.
		g = new Gridref(1,2);
		assertEquals(1, g.getX());
		assertEquals(2, g.getY());
		
		// test negative values
		g = new Gridref(-1,-2);
		assertEquals(-1, g.getX());
		assertEquals(-2, g.getY());
		
		g = new Gridref(Integer.MAX_VALUE,Integer.MAX_VALUE);
		assertEquals(Integer.MAX_VALUE, g.getX());
		assertEquals(Integer.MAX_VALUE, g.getY());
		
		g = new Gridref(Integer.MIN_VALUE,Integer.MIN_VALUE);
		assertEquals(Integer.MIN_VALUE, g.getX());
		assertEquals(Integer.MIN_VALUE, g.getY());
	}
}
