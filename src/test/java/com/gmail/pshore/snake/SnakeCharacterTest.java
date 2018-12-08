package com.gmail.pshore.snake;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import com.gmail.pshore.snake.SnakeCharacter;

public class SnakeCharacterTest {

	@Test
	public void testConstructors() {
		SnakeCharacter c;
		
		// constructors that should initialise positions, but not add a head.
		c = new SnakeCharacter();
		assertEquals(0, c.getPositions().size());

		c = new SnakeCharacter();
		assertEquals(0, c.getPositions().size());

		
		// constructor that initialises and adds a head at gridref 0,0.
		c = new SnakeCharacter(0,0);
		assertEquals(1, c.getPositions().size());
		Gridref g = c.getPositions().get(0);
		assertEquals(0, g.getX());
		assertEquals(0, g.getY());
		
		// test creation from int arrays
		c = new SnakeCharacter( new int[][] { {0,0},{1,0},{2,0} } );
		assertEquals(3, c.positions.size());
		g = c.positions.get(0); assertEquals(0,g.getX()); assertEquals(0,g.getY());
		g = c.positions.get(1); assertEquals(1,g.getX()); assertEquals(0,g.getY());
		g = c.positions.get(2); assertEquals(2,g.getX()); assertEquals(0,g.getY());
	}
	
	@Test
	public void testFollowSizeOne() {
		SnakeCharacter c;
		Gridref g0;

		// check you cannot follow an empty snake
		c = new SnakeCharacter();
		try {
			c.follow( new Gridref(-1,0) ); // move left
			fail("Moving an empty snake should fail");
		} catch( IndexOutOfBoundsException e ) {
			// good
		}
		
		// test basic movement with just a head.		
		
		c = new SnakeCharacter(0,0);
		c.follow( new Gridref(-1,0) ); // move left
		g0 = c.getPositions().get(0);
		assertTrue( g0.equals(-1, 0) );
		
		c = new SnakeCharacter(0,0);
		c.follow( new Gridref(1,0)); // move right
		g0 = c.getPositions().get(0);
		assertTrue( g0.equals(1, 0) );		
		
		c = new SnakeCharacter(0,0);
		c.follow( new Gridref(0,-1)); // move up
		g0 = c.getPositions().get(0);
		assertTrue( g0.equals(0,-1) );
		
		c = new SnakeCharacter(0,0);
		c.follow( new Gridref(0,1)); // move down
		g0 = c.getPositions().get(0);
		assertTrue( g0.equals(0,1) );
		
		c = new SnakeCharacter(0,0);
		c.follow( new Gridref(1,1)); // move down-right
		g0 = c.getPositions().get(0);
		assertTrue( g0.equals(1,1) );
		
		c = new SnakeCharacter(0,0);
		c.follow( new Gridref(-1,0)); // move left
		c.follow( new Gridref(-1,0)); // move left
		g0 = c.getPositions().get(0);
		assertTrue( g0.equals(-2, 0) );	// check double move
	}
	
	@Test
	public void testFollowSizeThree() {
		SnakeCharacter c;    // the character to be moved.

		// initial starting position.
		c = new SnakeCharacter( new int[][] { { 0, 0},{ 1, 0},{ 2, 0} } );  
		// ensure 'equals' functions as expected
		assertTrue( c.equals( new SnakeCharacter( new int[][] { { 0, 0},{ 1, 0},{ 2, 0} } ) ) );
		
		// left 1. Starts with head on the left, pointing left.
		c.followLeft();
		assertTrue( c.equals( new SnakeCharacter( new int[][] { {-1, 0},{ 0, 0},{ 1, 0} } ) ) );
		
		// right 1. Starts with head on the right, pointing right.
		c = new SnakeCharacter( new int[][] { { 0, 0},{-1, 0},{-2, 0} } ); 
		c.followRight();
		assertTrue( c.equals( new SnakeCharacter( new int[][] { { 1, 0},{ 0, 0},{-1, 0} } ) ) );
		
		// Circle anti-clockwise down. Starts with head on the left, pointing left.
		// The head goes through this shape. 0 thru 5 then back to 0.
		// 054
		// 123
		c = new SnakeCharacter( new int[][] { { 0, 0},{ 1, 0},{ 2, 0} } );  
		c.followDown();
		assertTrue( c.equals( new SnakeCharacter( new int[][] { { 0, 1},{ 0, 0},{ 1, 0} } ) ) );
		c.followRight();
		assertTrue( c.equals( new SnakeCharacter( new int[][] { { 1, 1},{ 0, 1},{ 0, 0} } ) ) );
		c.followRight();
		assertTrue( c.equals( new SnakeCharacter( new int[][] { { 2, 1},{ 1, 1},{ 0, 1} } ) ) );		
		c.followUp();
		assertTrue( c.equals( new SnakeCharacter( new int[][] { { 2, 0},{ 2, 1},{ 1, 1} } ) ) );
		c.followLeft();
		assertTrue( c.equals( new SnakeCharacter( new int[][] { { 1, 0},{ 2, 0},{ 2, 1} } ) ) );		
		c.followLeft(); // now back where we started.
		assertTrue( c.equals( new SnakeCharacter( new int[][] { { 0, 0},{ 1, 0},{ 2, 0} } ) ) );

		// Circle clockwise left. Starts with head on the left, pointing left.
		// The head goes through this shape. 0 thru 5 then back to 0.
		// 345
		// 210..
		c = new SnakeCharacter( new int[][] { { 0, 0},{ 1, 0},{ 2, 0} } );  
		c.followLeft();
		assertTrue( c.equals( new SnakeCharacter( new int[][] { {-1, 0},{ 0, 0},{ 1, 0} } ) ) );
		c.followLeft();
		assertTrue( c.equals( new SnakeCharacter( new int[][] { {-2, 0},{-1, 0},{ 0, 0} } ) ) );
		c.followUp();
		assertTrue( c.equals( new SnakeCharacter( new int[][] { {-2,-1},{-2, 0},{-1, 0} } ) ) );		
		c.followRight();
		assertTrue( c.equals( new SnakeCharacter( new int[][] { {-1,-1},{-2,-1},{-2, 0} } ) ) );
		c.followRight();
		assertTrue( c.equals( new SnakeCharacter( new int[][] { { 0,-1},{-1,-1},{-2,-1} } ) ) );		
		c.followDown(); // now back where we started.
		assertTrue( c.equals( new SnakeCharacter( new int[][] { { 0, 0},{ 0,-1},{-1,-1} } ) ) );
	}	
	
}
