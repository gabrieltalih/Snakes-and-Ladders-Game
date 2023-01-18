/**
 * Written by: Gabriel Talih 40181253
 * Assignment 1 Extra Object Class
 * COMP 249 Winter 2021
 * Due February 8 2021
 * 
 * Object class thats sets, stores and returns information about the states of a specific position
 * object that will be used in an array representing a board in the LadderAndSnake class.
 */
public class BoardPosition {
	
	/**
	 * Private boolean field representing of what type of position a BoardPosition is, based on its name,
	 * if it is the start or end of a snake or ladder.
	 */
	private boolean ladderStart, ladderEnd, snakeStart, snakeEnd;
	
	/**
	 * Private integer field representing the position where the snake or ladder came from or went to.
	 */
	private int toFromPosition;
	
	/**
	 * Public default constructor that initializes a BoardPosition object with all of its parameters set to their default values.
	 */
	public BoardPosition() {
		
		ladderStart = false;
		ladderEnd = false;
		snakeStart = false;
		snakeEnd = false;
		toFromPosition = 0;
	}
	
	/**
	 * Public parameterized mutator that sets true the field corresponding to the start of a ladder, and sets the
	 * toFromPosition to the parameter used, representing the position of the end of the ladder.
	 * @param end integer position of the end of the ladder
	 */
	public void setLadderStart(int end) {
		
		ladderStart = true;
		toFromPosition = end;
	}
	
	/**
	 * Public parameterized mutator that sets true the field corresponding to the end of a ladder, and sets the
	 * toFromPosition to the parameter used, representing the position of the start of the ladder.
	 * @param start integer position of the start of the ladder
	 */
	public void setLadderEnd(int start) {
		
		ladderEnd = true;
		toFromPosition = start;
	}
	
	/**
	 * Public parameterized mutator that sets true the field corresponding to the start of a snake, and sets the
	 * toFromPosition to the parameter used, representing the position of the end of the snake.
	 * @param end integer position of the end of the snake
	 */
	public void setSnakeStart(int end) {
		
		snakeStart = true;
		toFromPosition = end;
	}
	
	/**
	 * Public parameterized mutator that sets true the field corresponding to the end of a snake, and sets the
	 * toFromPosition to the parameter used, representing the position of the start of the snake.
	 * @param start integer position of the start of the snake
	 */
	public void setSnakeEnd(int start) {
		
		snakeEnd = true;
		toFromPosition = start;
	}
	
	/**
	 * Public accessor that returns the field corresponding to the position being the start of ladder or not.
	 * @return if position is a ladderStart
	 */
	public boolean isLadderStart() {return ladderStart;}
	
	/**
	 * Public accessor that returns the field corresponding to the position being the end of ladder or not.
	 * @return if position is a ladderEnd
	 */
	public boolean isLadderEnd() {return ladderEnd;}
	
	/**
	 * Public accessor that returns the field corresponding to the position being the start of snake or not.
	 * @return if position is a snakeStart
	 */
	public boolean isSnakeStart() {return snakeStart;}
	
	/**
	 * Public accessor that returns the field corresponding to the position being the end of snake or not.
	 * @return if position is a snakeEnd
	 */
	public boolean isSnakeEnd() {return snakeEnd;}
	
	/**
	 * Public accessor that checks if the toFromPosition field has been changed from zero,
	 * meaning it has been set by one of the mutators to a start or end of a snake or ladder.
	 * @return if toFromPosition was changed from initialized value
	 */
	public boolean isOccupied() {return toFromPosition != 0;}
	
	/**
	 * Public accessor that returns the integer toFromPosition field.
	 * @return toFromPosition
	 */
	public int getToFromPosition() {return toFromPosition;}
}