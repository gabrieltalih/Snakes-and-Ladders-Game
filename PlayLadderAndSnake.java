import java.util.Scanner;

/**
 * Written by: Gabriel Talih 40181253
 * Assignment 1 Part II
 * COMP 249 Winter 2021
 * Due February 8 2021 
 * 
 * Driver class for the ladder and snake game, includes code that prompts the user
 * to input the amount of players playing, for each player to choose their player model
 * and runs the play() method of the LadderAndSnake class.
 * 
 * Scanner package is imported so user input can be read.
 */
public class PlayLadderAndSnake {
	
	/**
	 * Main method for the LadderAndSnake game that asks the user to input how many players are playing, and if inputted right within four attempts, a LadderAndSnake
	 * object with the user input as a parameter is created, each player is asked to choose a character, then the play() method is ran.
	 */
	public static void main(String[] args) {
		
		//Integer counter for how many time the user inputs the number of players wrong.
		int attempts;
		
		//Integer counter used in for loop.
		int i;
		
		//String buffer used to keep track of user input for number of players.
		String read;
		
		System.out.print("Hello, welcome to Ladder and Snake, a java game coded by Gabriel :)\n\n"+
						 "Please enter the number of people playing (between 2 and 4): ");
		Scanner in = new Scanner(System.in);
		read = in.nextLine();
		
		attempts = 1;
		
		//While loop checking if the user input is not valid and there is still enough attempts, then prompts the
		//user to try inputting the number of players again.
		while (!read.equals("2") && !read.equals("3") && !read.equals("4") && attempts < 4) {
			
			System.out.print("\nBad attempt #"+attempts+", please input a valid number: ");
			read = in.nextLine();
			attempts++;
		}
		
		//Check if user's input is valid, otherwise prints end message.
		if (read.equals("2") || read.equals("3") || read.equals("4")) {
			
			LadderAndSnake Game = new LadderAndSnake(Integer.parseInt(read));
			
			//For loop prompting each player to input player character.
			for (i = 1; i <= Integer.parseInt(read); i++) {
				
				System.out.print("\nPlayer " + i + ", please choose any letter or character from the keyboard for your player model, excluding\n"
								 + "numbers and the special characters \"L\", \"l\", \"S\", \"s\", \"_\" and \"|\": ");
				Game.getPlayer(i).choosePlayerModel(in.next());
				
				//While loop to check if user input is valid and unique, if false, user is prompted to try inputting another player character
				while (!Game.getPlayer(i).isPlayerModelValid() || !Game.getPlayer(i).isPlayerModelUnique() ) {
					
					if (!Game.getPlayer(i).isPlayerModelValid())
						System.out.print("Player model is not valid, please input a correct character from the keyboard: ");
					else
						System.out.print("Player model is not unique, please input a different character from the keyboard: ");
					
					Game.getPlayer(i).choosePlayerModel(in.next());
				}
				
				//Adds used player model to usedCharacters string for checks on other players.
				Game.getPlayer(i).addUsedPlayerModel();
			}
			//Runs Play() method and main game.
			System.out.println();
			Game.play();
		}
		else 
			System.out.print("\nYou have tried and failed to input a number 4 times, this means\n"+
							 "you are not competent enough to play our game, please leave >:(");
		
		in.close();
	}
}	