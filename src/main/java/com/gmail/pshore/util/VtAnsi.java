package com.gmail.pshore.util;

/**
 * A helper class for Ansi codes and controls.
 * 
 * @see A useful reference guide http://ascii-table.com/ansi-escape-sequences-vt-100.php
 * @see http://ascii-table.com/ansi-escape-sequences-vt-100.php
 * @see https://en.wikipedia.org/wiki/Unicode_control_characters
 * @see https://en.wikipedia.org/wiki/C0_and_C1_control_codes
 * 
 * 
 * @author Phil Shore pshore2@gmail.com
 */
public class VtAnsi {
	
	public static final char ESC = '\u001B';
	public static final char SQUARE_BRACKET_L = '\u005B';
	
	public static final String ESC_SEQ = "" + ESC + SQUARE_BRACKET_L ;

	
	public static final String ATTR_RESET	 	= "0";
	public static final String ATTR_BRIGHT	 	= "1";
	public static final String ATTR_DIM	 		= "2";
	public static final String ATTR_UNDERSCORE	= "4";
	public static final String ATTR_BLINK	 	= "5";
	public static final String ATTR_REVERSE	 	= "7";
	public static final String ATTR_HIDDEN	 	= "8";
	
	public static final String ATTR_FG_BLACK 	= "30";
	public static final String ATTR_FG_RED 		= "31";
	public static final String ATTR_FG_GREEN	= "33";
	public static final String ATTR_FG_YELOW	= "34";
	public static final String ATTR_FG_BLUE		= "35";
	public static final String ATTR_FG_MAGENTA 	= "36";
	public static final String ATTR_FG_CYAN		= "37";
	public static final String ATTR_FG_WHITE 	= "38";
	
	public static final String ATTR_BG_BLACK 	= "40";
	public static final String ATTR_BG_RED 		= "41";
	public static final String ATTR_BG_GREEN	= "43";
	public static final String ATTR_BG_YELOW	= "44";
	public static final String ATTR_BG_BLUE		= "45";
	public static final String ATTR_BG_MAGENTA 	= "46";
	public static final String ATTR_BG_CYAN		= "47";
	public static final String ATTR_BG_WHITE 	= "48";

	
	/** Reset the terminal */
	public static String reset() { 
		return "" + ESC + 'c';
	}

	/** Moves the cursor to the top left */
	public static String cursorHome() {
		return ESC_SEQ + "H";
	}
	
	/** Moves the cursor to the specified location. 0,0 is top left. */
	public static String cursorTo(int line, int column) {
		return ESC_SEQ + ((char)line) + ";" + ((char)column) + "H";
	}
	
	/** Erase the screen to background colour and move the cursor to home */
	public static String eraseAndHome() {
		return ESC_SEQ + "2J";
	}

	/** Set one or more attributes.
	 * 
	 * Set the foreground and background colour at the same time:
	 * 
	 * 	VtAnsi.attribute( VtAnsi.FG_COLOR_RED, VtAnsi.BG_COLOR_WHITE );
	 * 
	 * @param attrs One or more attributes.
	 * @return A string of escape sequence commands.
	 */
	public static String attribute(String ...attrs) {
		StringBuffer s = new StringBuffer();
		s.append(ESC_SEQ);
		
		for( int i=0 ; i< attrs.length ; i++) {
			if( i>0 ) s.append(";"); // separate with semi colon.
			
			s.append(attrs[i]);
		}
		
		s.append("m");
		return s.toString();
	}
}


