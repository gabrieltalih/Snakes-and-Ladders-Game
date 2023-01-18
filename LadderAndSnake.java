import java.util.Scanner;

/**
 * Written by: Gabriel Talih 40181253
 * Assignment 1 Part I
 * COMP 249 Winter 2021
 * Due February 8 2021 
 * 
 * Main object class for playing the ladder and snake game, including methods for setting the board,
 * flipping the dice and playing the game. Also uses arrays of type BoardPosition and Player.
 * 
 * Scanner package is imported so user input can be read.
 */
public class LadderAndSnake {
	
	/**
	 * Private BoardPosition array field used to represent the ladder and snake board.
	 */
	private BoardPosition [] Board;
	
	/**
	 * Private Player array field used to represent the players for the ladder and snake game.
	 */
	private Player [] Players;
	
	/**
	 * Private static integer buffer used to store and save dice value in the play() method.
	 */
	private static int dice;
	
	/**
	 * Private static integer counter used to keep track of number of players on a position in the showBoard() method.
	 */
	private static int playerCount;
	
	/**
	 * Private static integer counter used to keep track of how many ties happened for calculations in the play() method,
	 * then repurposed later in the play() method to keep track of which player is playing.
	 */
	private static int iteration;
	
	/**
	 * Private static integer counter used in for loops in various methods.
	 */
	private static int a, b, c, d;
	
	/**
	 * Private static boolean check used in play() method to see if the tie breaker code should be run and repeated, then
	 * repurposed later in the play() method as a check to see if the next turn should be played.
	 */
	private static boolean startGame;
	
	/**
	 * Private static Player buffer used when sorting the player array in the play() method.
	 */
	private static Player largest;
	
	/**
	 * Public parameterized constructor for the ladderAndSnake class that sets and initializes an array of BoardPosition objects of length 101 to
	 * represent the board being used and another array of Player objects of length of the numberOfPlayers parameter + 1 to to form the list of
	 * players and store information about them. Also runs the setBoard() method, which sets the snakes and ladders on the board array.
	 * @param numberOfPlayers integer value for number of players playing
	 */
	public LadderAndSnake(int numberOfPlayers) {
		
		Board = new BoardPosition[101];
		Players = new Player[numberOfPlayers + 1];
		
		for (a = 1; a < 101; a++)
			Board[a] = new BoardPosition();
		
		for (a = 1; a < numberOfPlayers + 1; a++) 
			Players[a] = new Player(a);
		
		setBoard();
	}
	
	/**
	 * Private parameterizd mutator that takes the integer value of the position for the start and end of a ladder that you want to set, then runs the
	 * setLadderStart() method on the index of start on the board array using end as a parameter, and runs the setLadderEnd() method on the index of end
	 * on the board array using start as a parameter to save coding time in the setBoard() method.
	 * @param start integer value for the position of the start of the ladder
	 * @param end integer value for the position of the end of the ladder
	 */
	private void setLadder(int start, int end) {
		
		Board[start].setLadderStart(end);
		Board[end].setLadderEnd(start);
	}
	
	/**
	 * Private parameterizd mutator that takes the integer value of the position for the start and end of a snake that you want to set, then runs the
	 * setSnakeStart() method on the index of start on the board array using end as a parameter, and runs the setSnakeEnd() method on the index of end
	 * on the board array using start as a parameter to save coding time in the setBoard() method.
	 * @param start integer value for the position of the start of the snake
	 * @param end integer value for the position of the end of the snake
	 */
	private void setSnake(int start, int end) {
		
		Board[start].setSnakeStart(end);
		Board[end].setSnakeEnd(start);
	}
	
	/**
	 * Private mutator used to place all the predetermined ladders and snakes on the board.
	 */
	private void setBoard() {
		
		setLadder(1,38);
		setLadder(4,14);
		setLadder(9,31);
		setLadder(21,42);
		setLadder(28,84);
		setLadder(36,44);
		setLadder(51,67);
		setLadder(71,91);
		setLadder(80,100);
		
		setSnake(16,6);
		setSnake(48,30);
		setSnake(64,60);
		setSnake(79,19);
		setSnake(93,68);
		setSnake(95,24);
		setSnake(97,76);
		setSnake(98,78);
	}
	
	/**
	 * Private static mutator that takes in the value of the dice and prints out an ASCII interpertation for a dice of its value.
	 * @param dice integer value of dice
	 */
	private static void showDice(int dice) {
		
		System.out.println();
		
		switch (dice) {
			
			case 1:
				System.out.println("            |     |\n"+
								   "            |  o  |\n"+
								   "            |     |\n");
				break;
			case 2:	
				System.out.println("            |    o|\n"+
								   "            |     |\n"+
								   "            |o    |\n");
				break;
			case 3:	
				System.out.println("            |    o|\n"+
								   "            |  o  |\n"+
								   "            |o    |\n");
				break;
			case 4:	
				System.out.println("            |o   o|\n"+
								   "            |     |\n"+
								   "            |o   o|\n");
				break;
			case 5:	
				System.out.println("            |o   o|\n"+
								   "            |  o  |\n"+
								   "            |o   o|\n");
				break;
			case 6:	
				System.out.println("            |o   o|\n"+
								   "            |o   o|\n"+
								   "            |o   o|\n");
				break;
		}
	}
	
	/**
	 * Private mutator that represents the board and everything on it in an ASCII format,
	 * including the players, the ladders and the snakes.
	 */
	private void viewBoard() {
		
		for (a = 1; a <= 10; a++)
			System.out.print(" _______");
		
		System.out.println(" ");
		
		for (a = 4; a >= 0; a--) {
			
			for(b = 1; b >= -1; b = b - 2) {
				
				//Nested for loops used to snake through all the numbers on each row at the right order, where each cell of the row prints
				//its position on the board and when it is occupied, prints the letter matching to the start or end of a ladder or snake and
				//the position corresponding to the other side of this snake or ladder.
				for(c = (a*40 + b*19 + 21)/2; c != (a*40 - b + 21)/2; c = c - b) {
					
					System.out.print("|"+c);
					
					//For loop used to help format the line based on the size of the position.
					for (d = 0; d < 2 - (int) Math.log10(c); d++)
						System.out.print(" ");
					
					//Check if position is occupied by a snake or ladder.
					if(Board[c].isOccupied()) {
						
						//For loop used to help format the line based on the size of the other end position.
						for (d = 0; d < 2 - (int) Math.log10(Board[c].getToFromPosition()); d++)
							System.out.print(" ");
						
						if(Board[c].isLadderStart()) 
							System.out.print("L");
						else if(Board[c].isLadderEnd()) 
							System.out.print("l");
						else if(Board[c].isSnakeStart()) 
							System.out.print("S");
						else if(Board[c].isSnakeEnd()) 
							System.out.print("s");
						System.out.print(Board[c].getToFromPosition());
					}
					else 
						System.out.print("    ");
				}
				
				System.out.println("|");
				
				for(c = 0; c <10; c++)
					System.out.print("|       ");
				
				System.out.println("|");
				
				//Nested for loop used to check and print which positions players occupy, and displaying them with their symbol.
				for(c = (a*40 + b*19 + 21)/2; c != (a*40 - b + 21)/2; c = c - b) {
					
					playerCount = 0;
					System.out.print("|");
					
					for(d = 1; d < Players.length; d++) {
						
						if (Players[d].getPlayerPosition() == c) {
							
							//Player counter incremented for each player at a position
							playerCount++;
							System.out.print(Players[d].getPlayerModel() + "_");
						}
					}
					
					//For loop used to help format the line based on the amount of players.
					for(d = 0; d < 7 - 2*playerCount; d++)
						System.out.print("_");
				}
				System.out.println("|");
			}
		}
		System.out.println();
	}
	
	/**
	 * Public mutator that makes each player roll a dice, breaks any ties that happen after, decides the 
	 * player order, then runs the main logic for the ladder and snake game.
	 */
	public void play() {
		
		Scanner input = new Scanner(System.in);
		System.out.println("Get ready to play a "+ (Players.length - 1) + " player game of Ladder And Snake!\n\n" +
						   "Highest number rolled will decide what order the game will played:\n");
		
		//Iteration counter set to 0, as no tie breaks happened yet.
		iteration = 0;
	
		for (a = 1 ; a < Players.length - 1; a++) {
			
			do {
				
				startGame = true;
				
				//For loop with a nested if statement to check if two players' order values are equal, their reRoll values are set true
				//and the startGame check is set to false so the code prompting the users to roll dice is ran.
				for (b = a + 1; b < Players.length; b++) {
					
					if (Players[a].getPlayerOrderValue() == Players[b].getPlayerOrderValue()) {
						
						Players[b].setReRoll(true);
						startGame = false;
					}
				}
				
				if (!startGame) {
					
					Players[a].setReRoll(true);
					
					//Code stating that there was a tie, only happens if the iteration is greater than 0, as there is alays the intial rolling of dice
					//of the values before any ties happen, making printing this code redundant then.
					if (iteration > 0 ) {
						
						System.out.print("A tie was achieved between ");
						
						for (b = 1; b < Players.length; b++) {
							
							if (Players[b].isReRoll())
								System.out.print("Player " + Players[b].getPlayerNumber() +", ");
						}
						System.out.println("attempting to break the tie.");
					}
					
					//Prompts all tied players with a true reRoll to roll dice, displays its value and calculates the new order value of that player,
					//then resets the reRoll to false.
					for (b = 1; b < Players.length; b++ ) {
						
						if (Players[b].isReRoll()) {
							
							System.out.print("Player " + Players[b].getPlayerNumber() + ", press enter to roll dice (number rolled will be displayed):");
							input.nextLine();
							dice = flipDice();
							showDice(dice);
							Players[b].addPlayerOrderValue(dice, iteration);
							Players[b].setReRoll(false);
						}
					}
					
					//Insertion sort used to order the Players array by order value.
					for (b = 1; b < Players.length - 1; b++ ) {
						
						largest = Players[b];
						
						for(c = b + 1; c < Players.length; c++ ) {
							
							if (Players[c].getPlayerOrderValue() > largest.getPlayerOrderValue()) {
								
								largest = Players[c];
								Players[c] = Players[b];
								Players[b] = largest;
							}
						}
					}
					//Increases iteration to repsent that a tie has happened.
					iteration++;
				}
			//While loop ran again when ever the tie breaker code is run, to ensure any further ties will be broken.
			} while (!startGame);
		}
		
		System.out.print("The order of who plays first is: Player " + Players[1].getPlayerNumber());
		
		for (a = 2; a < Players.length-1; a++)
			System.out.print(", Player " + Players[a].getPlayerNumber());
		
		System.out.println(" then Player " + Players[Players.length-1].getPlayerNumber() + ".\n\n"
						 + "The board will be displayed after every turn, with each square showing what players are on it, and squares\n"
						 + "will have on the top right L or l representing the beginning or end of a ladder, while S or s being the same\n"
						 + "for a snake, with the number beside telling you where it will take you to or bring you from.\n");
		
		//Itteration will be incremented from 0 to 1 by the if else statement in the following do while loop to represent
		//the player of index 1 starting.
		iteration = 0;
		
		do {
			
			//If else statement used to run through all players as the while loop runs.
			if (iteration == Players.length - 1) {
				
				iteration = 1;
				System.out.print("The round may over, but the game isn't. ");
			}
			else
				iteration++;
			
			//Prompts player to roll dice and displays its value.
			System.out.print("Player " + Players[iteration].getPlayerNumber() +" (" + Players[iteration].getPlayerModel() + "), press enter to roll dice (number rolled will be displayed):");
			input.nextLine();
			dice = flipDice();
			showDice(dice);
			Players[iteration].movePlayerByDice(dice);
			
			//Moves player to new position with dice, if player passes 100, moves them forward and the remaining places back.
			if (Players[iteration].getPlayerPosition() < 101 )
				System.out.print("Player " + Players[iteration].getPlayerNumber() + " moved " + dice + " pace(s) forward");
			else {
				System.out.print("Player " + Players[iteration].getPlayerNumber() + " moved " + (dice - (Players[iteration].getPlayerPosition() - 100)) +
								   " pace(s) forward, " +(Players[iteration].getPlayerPosition() - 100) + " pace(s) back ");
				Players[iteration].movePlayerToSpecificPosition(200 - Players[iteration].getPlayerPosition() );
			}
			
			//Checks if player lands on a ladder or snake start, changes player position to the end of that ladder or snake.
			if (Board[Players[iteration].getPlayerPosition()].isLadderStart()) {
				System.out.println(", Player has reached a ladder, Player climbs up to position " + Board[Players[iteration].getPlayerPosition()].getToFromPosition()+".");
				Players[iteration].movePlayerToSpecificPosition(Board[Players[iteration].getPlayerPosition()].getToFromPosition());
			}
			else if (Board[Players[iteration].getPlayerPosition()].isSnakeStart()) {
				System.out.println(", Player has reached a snake, Player slides down to position " + Board[Players[iteration].getPlayerPosition()].getToFromPosition()+".");
				Players[iteration].movePlayerToSpecificPosition(Board[Players[iteration].getPlayerPosition()].getToFromPosition());
			}
			else
				System.out.println(" to position " + Players[iteration].getPlayerPosition() + ".");
			
			//Prints board after a turn.
			viewBoard();
			
			//Check if any player has reached 100, if the previous is true, startGame is set false and the game is over.
			if (Players[iteration].getPlayerPosition() == 100) 
				startGame = false;
			
		} while (startGame);
		
		input.close();
		System.out.print("Game is over, Player " + Players[iteration].getPlayerPosition() + " has reached position 100.\n"
				   	   + "Thank you for playing, have a great day :)");
	}
	
	/**
	 * Private static accessor that returns a random number from 1 to 6 inclusive to represent a dice.
	 * @return an integer from 1 to 6 inclusive
	 */
	private static int flipDice() {return (int)(Math.random()*6 + 1);}
	
	/**
	 * Public accesor used for the driver class to get a certain player object from the Players array using its player number as the index.
	 * @param playerNumber integer number of player
	 * @return Player object at index playerNumber from the Players array
	 */
	public Player getPlayer(int playerNumber) {return Players[playerNumber];}
}