/**
 * Written by: Gabriel Talih 40181253
 * Assignment 1 Extra Object Class
 * COMP 249 Winter 2021
 * Due February 8 2021
 * 
 * Object class thats sets, stores and returns information about the states of a specific player object
 * that will be used in an array to keep track of and organize the players in the LadderAndSnake class.
 */
public class Player {
	
	/**
	 * Private integer field holding the value of the player's number.
	 */
	private int playerNumber;
	
	/**
	 * Private integer field holding the value of the player's current position.
	 */
	private int playerPosition;
	
	/**
	 * Private character field that holds the value of the player model.
	 */
	private char playerModel;
	
	/**
	 * Private long field holding the value of the order of the player. Using long not integer
	 * so lower likelihood of underflowing using the addPlayerOrderValue() method later.
	 */
	private long playerOrderValue;
	
	/**
	 * Private boolean field check if the player should roll again.
	 */
	private boolean reRoll;
	
	/**
	 * Private final static string keeping track of the players models the user can not choose.
	 */
	private final static String forbiddenPlayerModels = " 1234567890LlSs_|";
	
	/**
	 * Private static string keeping track of which player models have been already used.
	 */
	private static String usedPlayerModels = "";
	
	/**
	 * Public parameterized constructor that initializes a Player object with its playerNumber field set
	 * to the number paremeter and the rest of the fields set to their default values.
	 * @param number integer value for playerNumber
	 */
	public Player(int number) {
		
		playerNumber = number;
		playerPosition = 0;
		playerModel = ' ';
		playerOrderValue = 0;
		reRoll = false;
	}
	
	/**
	 * Public parameterized mutator increases player position by adding the value of the parameter to the playerPosition field.
	 * @param dice rolled integer value got from rolling the dice
	 */
	public void movePlayerByDice(int dice) {playerPosition += dice;}
	
	/**
	 * Public parameterized mutator which changes the playerPosition field to the parameter.
	 * @param newPosition integer value got from landing on a snake or ladder or going past 100
	 */
	public void movePlayerToSpecificPosition(int newPosition) {playerPosition = newPosition;}
	
	/**
	 * Public parameterized mutator that sets the playerModel to the parameter inputed if it is of length one,
	 * otherwise the playerModel will be set to the default value.
	 * @param character string value of user input
	 */
	public void choosePlayerModel(String character) {
		
		if (character.length() == 1) 
			playerModel = character.charAt(0);
		else
			playerModel = ' ';
	}
	
	/**
	 * Public mutator that adds a used playerModel to the static usedPlayerModels string.
	 */
	public void addUsedPlayerModel() {usedPlayerModels = usedPlayerModels + playerModel;}
	
	/**
	 * Public parameterized mutator that adds a certain calculated value to player order value. It is calculated by multiplying a certain
	 * power of 8 by the value of the dice and adding it to the order value. At every new tie, the iteration parameter increases, so the power of 8
	 * and the subsequent value added decreases, symbolizing the fact that later rolls have lower precedence in determining the order. In
	 * addition, a power of 8 not 6 is used since when changing from the double power to long, no loss of significant figures occurs.
	 * @param dice integer value for the number that the dice rolled
	 * @param iteration integer value for how many ties already happened before
	 */
	public void addPlayerOrderValue(int dice, int iteration) {playerOrderValue += dice * (long) Math.pow(8, 20 - iteration);}
	
	/**
	 * Public mutator that sets the reRoll value of a player.
	 * @param roll boolean value for setting reRoll of a player
	 */
	public void setReRoll(boolean roll) {reRoll = roll;}
	
	/**
	 * Public accessor that returns the integer playerNumber field.
	 * @return playerNumber
	 */
	public int getPlayerNumber() {return playerNumber;}

	/**
	 * Public accessor that returns the integer playerPosition field.
	 * @return playerPosition
	 */
	public int getPlayerPosition() {return playerPosition;}

	/**
	 * Public accessor that returns the character playerModel field.
	 * @return playerModel
	 */
	public char getPlayerModel() {return playerModel;}

	/**
	 * Public accessor that checks if the playerModel field is valid and not in the forbiddenPlayerModels string.
	 * @return if playerModel is valid and not a forbidden player model
	 */
	public boolean isPlayerModelValid() {return forbiddenPlayerModels.indexOf(playerModel) == -1;}

	/**
	 * Public accessor that checks if the playerModel field is unique and not in the usedPlayerModels string.
	 * @return if playerModel is unique and not a repeated player model
	 */
	public boolean isPlayerModelUnique() {return usedPlayerModels.indexOf(playerModel) == -1;}

	/**
	 * Public accessor that returns the long playerOrderValue field.
	 * @return playerOrderValue
	 */
	public long getPlayerOrderValue() {return playerOrderValue;}

	/**
	 * Public accessor that returns the boolean reRoll field.
	 * @return if player should reRoll or not
	 */
	public boolean isReRoll() {return reRoll;}
}