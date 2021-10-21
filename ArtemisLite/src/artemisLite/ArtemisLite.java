/**
 * 
 */
package artemisLite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Group 3
 * @author Conor Bradley 40108536
 * @author Michelle Oakes
 * @author Sancha O'Neill
 * @author Laura Gaffey
 *
 */
public class ArtemisLite {

	private static List<Block> board;
	private static List<Player> playerList;
	private static Scanner scanner = new Scanner(System.in);
	private static RollDice dice1 = new RollDice();
	private static RollDice dice2 = new RollDice();
	private static int roll1, roll2, rollTot;
	private static StringBuilder sb;
	private static boolean endGame = false;
	private static int totMajDevs;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int userMainInput;

		// Welcome message
		System.out.println("Welcome to ArtemisLite\n");

		do {

			System.out.println("1: Start new game");
			System.out.println("2: Game manual/rules");
			System.out.println("3: Game credits");
			System.out.println("4: Exit");
			System.out.println("Please enter your desired option 1 - 4:");

			// Check for incorrect input type
			while (!scanner.hasNextInt()) {
				System.out.println("Incorrect input type... Please enter a NUMBER from 1 - 4.");
				scanner.next();
			}
			userMainInput = scanner.nextInt();

			switch (userMainInput) {
			case 1:
				System.out.println("Setting up new game.");
				// play new game
				endGame = false;
				gameSetUp();
				playGame();
				break;

			case 2:
				// display the game manual
				displayGameManual();
				break;

			case 3:
				// display credits.
				displayCredits();
				break;

			case 4:
				// exit
				scanner.close();
				System.out.println("Exiting Game... See you next time.");
				break;

			default:
				System.out.println("Incorrect input... Please enter numbers 1 - 4 for desired option.");
			}

		} while (userMainInput != 4);

	} // end of main

	/**
	 * This method will display the rules of the game when called
	 */
	public static void displayGameManual() {

		// series of system.out.println(); statements holding the rules of the game
		System.out.println("Artemis Lite Game manual \n\n");
		System.out.println("The game can have up to four players \n");
		System.out.println(
				"Each player enters their name (this can be an organisation), then each player rolls 2 dice and the player with the highest score goes first \n");
		System.out.println(
				"If players roll the same number, the player who entered their name first will take their turn before the other player with the same dice number");
		System.out.println(
				"The player with the highest score rolls the 2 dice again. The total of the two dice determines the number of blocks that player can move forward e.g. if a player rolls a 3 and a 2, they move to block 6 on the board \n");

		System.out.println(
				"They are the first player (so the block will not be owned by anyone yet) and block 6 is an action block (note all blocks are action blocks except for blocks 1 and 7) so the player can choose to: \n");

		System.out.println(
				"	-Invest in this block to assert ownership on that system (the Orion system, in this case) \n");

		System.out.println(
				"	-Do nothing, and the block will be available to the other players if they wish to invest in it \n");

		System.out.println(
				"If the player chooses to invest, they will be deducted 100 hours from their total resources (the cost of investing) but they will now own the system the block resides in (i.e. the Orion system)  \n");

		System.out.println("That means:  \n");

		System.out.println("	-No other player can invest in the blocks in this system  \n");

		System.out.println("	-No other player can develop the blocks in this system \n");

		System.out.println(
				"	-If a player lands on any of the blocks in your system (blocks 5 and 6), you may charge them a service fee which will be deduced from that player’s hours. This service fee will increase depending on how developed the blocks/ system is \n");

		System.out.println("Therefore, only the player that owns the system can develop the blocks in that system \n");

		System.out.println("The next player will then take their turn and the game will repeat the above steps \n\n");

		System.out.println("To win the game:  \n");

		System.out.println(
				"In order to win the game, each block in all 4 systems must be fully developed. Once all blocks in the four systems are developed; Artemis is ready, launch can occur, and space exploration can begin! \n");

		System.out.println("Player notes:\n");

		System.out.println(
				"Any number of developments can be made on a player’s turn and the player does not have to be placed on the block/ system they wish to develop \n");

		System.out.println("One block can be developed fully before the other blocks in the system\n");

		System.out.println(
				"Each block must be developed 3 times before a major development can occur (note a major development will cost more resources than a normal development) \n");

		System.out.println("The Orion system is the most labour intensive system to invest in and develop \n");

		System.out.println("The Space suit system is the least labour intensive system to invest in and develop \n");

		System.out.println("The game is over once any player runs out of their hours\n");

		System.out.println("Any player can end the game for everyone if they decide to leave the game \n");

		System.out.println(
				"A player has hours but they also have score points. This will be calculated and kept for each player by the program. A player can achieve points, for example, by developing a block and committing acts of charity e.g. not charging someone a service fee\n");

		System.out.println(
				"If the game ends and the systems are not developed, the winner will be the person with the highest points\n");

	} // end of method

	/**
	 * Method to display game credits
	 */
	public static void displayCredits() {

		System.out.println("Developers....\n");
		System.out.println("\t Conor Braldey");
		System.out.println("\t Michelle Oakes");
		System.out.println("\t Sancha O'Neill");
		System.out.println("\t Laura Gaffey\n");

	} // end of displayCredits method

	/**
	 * Set up game and game resources
	 */
	public static void gameSetUp() {
		int userSetUpInput;
		String playerName;

		boolean validInput;
		System.out.println("Please enter number of players (1 - 4): ");

		do {
			validInput = true;
			while (!scanner.hasNextInt()) {
				System.out.println("Incorrect input type... Please enter a NUMBER from 1 - 4.");
				scanner.next();
			}
			userSetUpInput = scanner.nextInt();

			if (userSetUpInput < 1 || userSetUpInput > 4) {
				System.out.println("Incorrect input. Please enter a number between 1 - 4:");
				validInput = false;
			}

		} while (!validInput);

		playerList = new ArrayList<Player>();
		board = new ArrayList<Block>();
		totMajDevs = 0;

		// Create player objects and put into playerList ArrayList
		for (int loop = 1; loop <= userSetUpInput; loop++) {

			do {
				validInput = true;
				System.out.println("Enter Player " + loop + "'s name: ");
				scanner.useDelimiter(System.lineSeparator());
				playerName = scanner.next();
				for (Player p : playerList) {
					if (playerName.equals((p.getPlayerName()))) {
						validInput = false;
						System.out.println("Name already taken. Please enter a different Name.");
						break;
					}
				}
			} while (!validInput);

			Player newPlayer = new Player(playerName, loop, 100, 1);
			playerList.add(newPlayer);

		}

		/*
		 * Creating Block Objects and add to board ArrayList. Creating 12 blocks here
		 * 
		 */

		// Starting block
		Block b1 = new DoNothingBlock("Mission Control", "Starting block: Collect 50 hours as you pass", 1, "Yellow");

		/*
		 * System 1: SLS system set up each block with initial investment of 10,
		 * development of 20, major development 30 and service charge 5
		 */
		Block b2 = new ActionBlock("RS-25 Engines",
				"4 RS-25 Engines will ensure the astronauts make it the whole way to the moon", 2, "Orange",
				SystemNames.SLS, 30, 0, 20, 50, 20);

		Block b3 = new ActionBlock("Solid Rocket Boosters", "2 SRBs will be needed for a successful flight", 3,
				"Orange", SystemNames.SLS, 30, 0, 20, 50, 20);

		Block b4 = new ActionBlock("Orion adapter",
				"The Orion adapter helps with launching, both to space and back home", 4, "Orange", SystemNames.SLS, 30, 0, 20, 50, 20);

		/*
		 * System 2: Orion system 2 blocks (most expensive to acquire and resource)
		 * Raised initial investment, service charge, development and major development
		 */
		Block b5 = new ActionBlock("Service Module",
				"The Orion service module provides water, oxygen, nitrogen and other vital support functions to the  to support the crew module",
				5, "Pink", SystemNames.ORION, 100, 0, 200, 300, 50);

		Block b6 = new ActionBlock("Crew Module",
				"The Orion Crew Module will house the crew on their voyage the the moon.", 6, "Pink", SystemNames.ORION,
				100, 0, 200, 300, 50);

		// Do nothing block
		Block b7 = new DoNothingBlock("Goverment Shutdown",
				"Funding Freeze! This is a blank block. It is not part of any system. You can develop elements of other systems as it is your turn but only if you own the system that the elements reside.",
				7, "black");

		/*
		 * System 3: Space Suit Design 2 blocks (least expensive to acquire and
		 * resource) Lower initial investment, service charge, development and major
		 * development
		 */
		Block b8 = new ActionBlock("PLSS",
				"Portable Life Support System (PLSS) houses everything astronauts need while they explore space!", 8,
				"Blue", SystemNames.SPACESUIT, 10, 0, 10, 20, 10);

		Block b9 = new ActionBlock("Cooling Garment",
				"Special water tubes keep astronuats cool during long spacewalks.", 9, "Blue", SystemNames.SPACESUIT,
				10, 0, 10, 20, 10);

		// System 4: Gateway & Lunar landers
		Block b10 = new ActionBlock("Gateway",
				"The Gateway allows astronauts to conduct research and take trips down to the surface", 10, "Green",
				SystemNames.GATEWAYANDLUNARLANDERS, 30, 0, 20, 50, 20);

		Block b11 = new ActionBlock("Lunar landers", "Lunar landers can search the surface", 11, "Green",
				SystemNames.GATEWAYANDLUNARLANDERS, 30, 0, 20, 50, 20);

		Block b12 = new ActionBlock("Lunar landers Deployment", "Deploy lunar landers from the Gateway", 12, "Green",
				SystemNames.GATEWAYANDLUNARLANDERS, 30, 0, 20, 50, 20);

		// Add blocks to board arrayList.
		board.add(b1);
		board.add(b2);
		board.add(b3);
		board.add(b4);
		board.add(b5);
		board.add(b6);
		board.add(b7);
		board.add(b8);
		board.add(b9);
		board.add(b10);
		board.add(b11);
		board.add(b12);

	} // end of game set up

	/**
	 * Initiates Game, calls players turn and performs end sequence.
	 */
	public static void playGame() {

		System.out.println(
				"Welcome to Artemis Lite, the game where you help further space exploration and make history by landing the next man and first woman on the moon by 2024!");
		System.out.println(
				"If you need a reminder of the rules, please read the game manual which can be found at opinion 2 of the game menu or option 6 of the player turn menu.");
		System.out.println("Let’s go!");

		// Using a comparator to correctly order the players in the arrayList for who
		// goes first.
		System.out.println(
				"Which player goes first? Lets roll the dice for everyone and who ever gets the highest sum of two rolls goes first.");

		for (int loop = 0; loop < playerList.size(); loop++) {
			roll1 = dice1.rollDie();
			roll2 = dice2.rollDie();
			rollTot = roll1 + roll2;
			playerList.get(loop).setStartDiceRoll(rollTot);

			System.out.println("Rolling...");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(playerList.get(loop).getPlayerName() + " rolls a " + roll1 + " and a " + roll2
					+ " for a total roll of " + rollTot);
		}

		Collections.sort(playerList, new CompareStartDiceRoll());

		// Reseting playerID to match new indexes
		for (int loop = 0; loop < playerList.size(); loop++) {
			playerList.get(loop).setPlayerID(loop + 1);
		}

		System.out.println(playerList.get(0).getPlayerName() + " goes first!\n");

		System.out.println("First turn.");

		// Set to start at first player.
		int playerIndex = 0;
		int roundCount = 0;
		int turnCount = 0;

		while (!endGame && totMajDevs < 10) {
			playerTurn(playerIndex);

			playerIndex++;
			turnCount++;

			if (playerIndex == playerList.size()) {
				playerIndex = 0;
			}

			turnCount++;
			System.out.println("\n Next Turn : Turn " + turnCount + " of the game.\n");

		}

		// Start game over sequence
		gameOver();

	} // end of method

	/**
	 * 
	 */
	public static void gameOver() {

		int blocksOwned, totDevs, majDevs;
		System.out.println("\n \n");
		System.out.println("#################################################");
		System.out.println("################## Game over ####################");
		System.out.println("#################################################\n\n");

		if (totMajDevs == 10) {
			System.out.println("         MOON REACHED : TEAM VICTORY         \n");
			System.out.println("All components fully developed, moon landing successful.");
			System.out.println("      _  _     ____________.--.\r\n"
					+ "                  |\\|_|//_.-\"\" .'    \\   /|  |\r\n"
					+ "                  |.-\"\"\"-.|   /       \\_/ |  |\r\n"
					+ "                  \\  ||  /| __\\_____________ |\r\n"
					+ "                  _\\_||_/_| .-\"\"            \"\"-.  __\r\n"
					+ "                .' '.    \\//                    \".\\/\r\n"
					+ "                ||   '. >()_                     |()<\r\n"
					+ "                ||__.-' |/\\ \\                    |/\\\r\n"
					+ "                   |   / \"|  \\__________________/.\"\"\r\n"
					+ "                  /   //  | / \\ \"-.__________/  /\\\r\n"
					+ "               ___|__/_|__|/___\\___\".______//__/__\\\r\n"
					+ "              /|\\     [____________] \\__/         |\\\r\n"
					+ "             //\\ \\     |  |=====| |   /\\\\         |\\\\\r\n"
					+ "            // |\\ \\    |  |=====| |   | \\\\        | \\\\        ____...____....----\r\n"
					+ "          .//__| \\ \\   |  |=====| |   | |\\\\       |--\\\\---\"\"\"\"     .            ..\r\n"
					+ "_____....-//___|  \\_\\  |  |=====| |   |_|_\\\\      |___\\\\    .                 ...'\r\n"
					+ " .      .//-.__|_______|__|_____|_|_____[__\\\\_____|__.-\\\\      .     .    ...::\r\n"
					+ "        //        //        /          \\ `-_\\\\/         \\\\          .....:::\r\n"
					+ "  -... //     .  / /       /____________\\    \\\\       .  \\ \\     .            .\r\n"
					+ "      //   .. .-/_/-.                 .       \\\\        .-\\_\\-.                 .\r\n"
					+ "     / /      '-----'           .             \\ \\      '._____.'         .\r\n"
					+ "  .-/_/-.         .                          .-\\_\\-.                          ...\r\n"
					+ " '._____.'                            .     '._____.'                       .....\r\n"
					+ "        .                                                             ...... ..\r\n"
					+ "    .            .                  .                        .\r\n"
					+ "   ...                    .                      .                       .      .\r\n"
					+ "        ....     .                       .                    ....\r\n"
					+ "          ......           . ..                       ......'\r\n"
					+ "             .......             '...              ....\r\n"
					+ "                                   ''''''      .              .");
			System.out.println(
					"...Your achievement will be told in the chapters of history, and inspire generations to come...");

		}

		System.out.println("Players end of game summary:\n");

		for (Player player : playerList) {

			blocksOwned = 0;
			totDevs = 0;
			majDevs = 0;

			System.out.println(player.getPlayerName() + ":");
			System.out.printf("%-20s %-15s %-15s %-15s %15s \n", " ", "Block No.", "Block Title", "Development to:",
					"System");
			for (Block block : board) {

				if (block instanceof ActionBlock) {
					ActionBlock systemBlock = (ActionBlock) block;
					if (systemBlock.getOwnerID() == player.getPlayerID()) {
						blocksOwned++;
						if (systemBlock.getDevelopmentTier() == 4) {
							totDevs += 3;
							majDevs++;
						} else {
							totDevs += systemBlock.getDevelopmentTier();
						}
						System.out.printf("%-20s %-15s %-15s %-15s %15s \n", " ", systemBlock.getBlockID(),
								systemBlock.getBlockTitle(), systemBlock.getDevelopmentTier(),
								systemBlock.getSystemNames().toString());
					}
				}
			}
			System.out.printf("%-20s %-15s \n", " ", "-------------------------------------------------------------");
			System.out.printf("%-20s %-15s %-15d x100 = %-5d points\n", " ", "Total Blocks Owned:        ", blocksOwned,
					blocksOwned * 100);
			System.out.printf("%-20s %-15s %-15d x50  = %-5d points\n", " ", "Total Developments Made:   ", totDevs,
					totDevs * 50);
			System.out.printf("%-20s %-15s %-15d x200 = %-5d points\n", " ", "Major Developments Made:   ", majDevs,
					majDevs * 200);
			System.out.printf("%-20s %-15s %-15d x75  = %-5d points\n", " ", "Service charges forgiven:  ",
					player.getCharity(), player.getCharity() * 75);
			int playerScore = (blocksOwned * 100) + (totDevs * 50) + (majDevs * 200) + (player.getCharity() * 75);
			System.out.printf("%-20s %-15s %-15s        %15d   Total points\n", " ", " ", " ", playerScore);
			player.setHighScore(playerScore);

		}

		Collections.sort(playerList, new CompareHighScore());
		System.out.println("\nFinal Leader Board:\n");
		System.out.printf("%-5s %-10s %-15s\n", "Rank", "Name", "Final Score");
		for (int loop = 0; loop < playerList.size(); loop++) {
			System.out.printf("%-5d %-10s %-15d\n", loop + 1, playerList.get(loop).getPlayerName(),
					playerList.get(loop).getHighScore());
		}

		if (totMajDevs != 10) {
			System.out.println("\n\n " + playerList.get(0).getPlayerName() + " is the winner!");
		}

		System.out.println("-------------------------------------------------------------------\n");
		System.out.println("Thank you for playing ArtemisLite....\n\n\n");
		displayCredits();

	} // end of gameOver method

	/**
	 * Plays turn for inputed player at the parameter index of the playerList
	 * arrayList.
	 */
	public static void playerTurn(int playerIndex) {

		System.out.println("--------------------------------------------------------------");
		System.out.println(playerList.get(playerIndex).getPlayerName() + "'s Turn");
		System.out.println("--------------------------------------------------------------\n");

		System.out.println("Rolling dice....");

		System.out.println("Rolling...");

		roll1 = dice1.rollDie();
		System.out.print("..." + roll1 + "....");

		roll2 = dice2.rollDie();
		System.out.println(roll2);
		rollTot = roll1 + roll2;
		System.out.println(playerList.get(playerIndex).getPlayerName() + " rolled a " + rollTot + "\n");

		// Moving player to next block on the board according to their dice roll.
		playerList.get(playerIndex).updatePlayerPosition(rollTot);

		// -1 to account for zero indexing.
		System.out.println(
				"They landed on block " + board.get(playerList.get(playerIndex).getPlayerPosition() - 1).getBlockID()
						+ ". " + board.get(playerList.get(playerIndex).getPlayerPosition() - 1).getBlockTitle() + "\n");

		// Display current block information
		System.out.println("Current Block Status: ");
		board.get(playerList.get(playerIndex).getPlayerPosition() - 1).displayAll();

		// Check if player passed Start (Go) block. Resources will already have been
		// added if they have.
		if (playerList.get(playerIndex).getPassedGo()) {
			System.out.println("Passed " + board.get(0).getBlockTitle() + board.get(0).getBlockDesc());
		}

		// Statements determine if player is on Action block or other
		if (playerList.get(playerIndex).getPlayerPosition() == 1) {

			System.out.println(board.get(playerList.get(playerIndex).getPlayerPosition() - 1).getBlockTitle());
			System.out.println("\nNothing to do here Houston. Collect resources and wait until next turn");
			System.out.println("50 hours added to " + playerList.get(playerIndex).getPlayerName());

		} else if (playerList.get(playerIndex).getPlayerPosition() == 7) {
			System.out.println(board.get(playerList.get(playerIndex).getPlayerPosition() - 1).getBlockTitle());
			System.out.println("\nFunding Freeze! Nothing you can do. Wait until next turn\n");

		} else {

			// Cast into an Action Block to use its methods
			ActionBlock actionBlock = (ActionBlock) board.get(playerList.get(playerIndex).getPlayerPosition() - 1);

			int playerTurnInput;
			boolean validInput = true;
			boolean chargeSettled = false;

			do {

				sb = new StringBuilder();

				// Logic here to determine which to show the user.
				if (!actionBlock.isInvest()) {
					sb.append("1: Invest in this System \n");
				}

				if (playerList.get(playerIndex).isDeveloper()) {
					sb.append("2: Development options \n");
				}

				sb.append("3: End Turn \n");
				sb.append("4: End Game \n");
				sb.append("5: Show all players positions on the board \n");
				sb.append("6: Display Game Rules \n");

				if (actionBlock.getOwnerID() != 0
						&& actionBlock.getOwnerID() != playerList.get(playerIndex).getPlayerID() && !chargeSettled) {

					sb.append(actionBlock.getOwnerName() + " owns this block. The service charge for this block is "
							+ actionBlock.getServiceCharge() + ".\n " + actionBlock.getOwnerName()
							+ " do you wish to collect this charge? \n\n");
					sb.append(playerList.get(playerIndex).getPlayerName() + "'s current hours: "
							+ playerList.get(playerIndex).getHours());
					sb.append(playerList.get(actionBlock.getOwnerID() - 1).getPlayerName() + "'s current hours: "
							+ playerList.get(actionBlock.getOwnerID() - 1).getHours());

					int resourcesAfterCharge = playerList.get(playerIndex).getHours() - actionBlock.getServiceCharge();

					if (resourcesAfterCharge <= 0) {
						sb.append("This will bankrupt " + playerList.get(playerIndex).getPlayerName()
								+ " and end the whole game! Are you sure you want to take the charge?");
					}
					sb.append("\n Enter 8 to make the charge, or 9 to not make the charge.");
				}

				System.out.println();
				System.out.println(playerList.get(playerIndex).getPlayerName() + "'s hours: "
						+ playerList.get(playerIndex).getHours() + "\n");
				System.out.println();
				System.out.println("What would you like to do? Select option from below:");

				// Now into the options the player may choose
				System.out.println(sb.toString());

				while (!scanner.hasNextInt()) {
					System.out.println("Incorrect input type... Please enter a NUMBER");
					scanner.next();
				}
				playerTurnInput = scanner.nextInt();

				switch (playerTurnInput) {
				case 1:
					// Invest in system
					investInSystem(playerIndex);
					break;

				case 2:
					// Develop block
					developBlock(playerIndex);
					break;

				case 3:
					System.out.println("Ending " + playerList.get(playerIndex).getPlayerName() + "'s turn...\n");
					return;
					
				case 5:
					System.out.println();
					System.out.printf("%s %15s\n", "Player name:", "Block");
					for(Player p : playerList) {
						System.out.printf("%s %15d\n", p.getPlayerName(), p.getPlayerPosition());
					}
					System.out.println();
					break;
					
				case 6: 
					System.out.println();
					displayGameManual();
					System.out.println("\n");
					break;

				case 8:
					playerList.get(playerIndex).decreasePlayerHours(actionBlock.getServiceCharge());
					playerList.get(actionBlock.getOwnerID() - 1).increasePlayerHours(actionBlock.getServiceCharge());

					System.out.println("Service charge made.");
					System.out.println(playerList.get(playerIndex).getPlayerName() + "'s hours is now: "
							+ playerList.get(playerIndex).getHours());
					System.out.println(playerList.get(actionBlock.getOwnerID() - 1).getPlayerName()
							+ "'s hours is now: " + playerList.get(actionBlock.getOwnerID() - 1).getHours());
					chargeSettled = true;

					if (playerList.get(playerIndex).getHours() <= 0) {
						System.out.println("...." + playerList.get(playerIndex).getPlayerName()
								+ " bankrupt. Artemis mission grounded until further notice... ");
						endGame = true;
						return;
					}
					break;

				case 9:
					System.out
							.println("Service charge on " + playerList.get(playerIndex).getPlayerName() + " forgiven.");
					playerList.get(actionBlock.getOwnerID() - 1)
							.setCharity(playerList.get(actionBlock.getOwnerID() - 1).getCharity() + 75);
					chargeSettled = true;
					break;

				case 4:
					System.out.println("This will end the game for everyone, are you sure you want to end the game?");
					System.out.println("Enter y/n");
					boolean validEndInput;
					do {
						validEndInput = true;
						String confirmEndGame = scanner.next();

						if (confirmEndGame.equals("y")) {
							System.out.println("Finishing game...");
							endGame = true;
							return;

						} else if (confirmEndGame.equals("n")) {
							System.out.println("Play on...");
							break;
						} else {
							System.out.println("Incorrect input. Please enter either 'y' for Yes or 'n' for No:");
							validEndInput = false;
						}

					} while (!validEndInput);
					break;

				default:
					System.out.println("Incorrect input... Please enter specified numbers for desired option.");
				}

			} while (playerTurnInput != 3);

		}

	} // end of method

	/**
	 * Performs player investment in a system.
	 * 
	 * @param player
	 */
	public static void investInSystem(int player) {

		// cast into an Action Block to get at its methods
		ActionBlock actionBlock = (ActionBlock) board.get(playerList.get(player).getPlayerPosition() - 1);

		System.out.println("This is the " + actionBlock.getSystemNames() + " system.");
		System.out.println("Cost of system investment is: " + actionBlock.getInitialInvestmentCost());

		int hoursAfterInvest = playerList.get(player).getHours() - actionBlock.getInitialInvestmentCost();

		System.out.printf("%-15s  %-20s %-15s\n", "Your resources are:", "Current", "After Investment");
		System.out.printf("%-19s  %-20d %-15d\n", " ", playerList.get(player).getHours(), hoursAfterInvest);

		// if current resources are not enough

		if (hoursAfterInvest <= 0) {
			System.out.println("You do not have enough hours to invest in this system.");
			int hoursNeeded = actionBlock.getInitialInvestmentCost() + 1 - playerList.get(player).getHours();
			System.out.println("You need at least " + hoursNeeded
					+ " more hours to invest in and own this system, and avoid bankruptcy.");
			System.out.println("Returning to player turn menu....");
			return;

		} else {

			System.out.println(
					"Would you like to invest in this system? Enter 'y' or 'n'. (Only you will be able to develop blocks in this system)");
			String userInvestInput;
			boolean validInput;

			do {
				validInput = true;
				userInvestInput = scanner.next();

				if (userInvestInput.equals("y")) {

					playerList.get(player).decreasePlayerHours(actionBlock.getInitialInvestmentCost());
					playerList.get(player).setDeveloper(true);
					int pointstoAdd = 0;

					for (Block block : board) {
						if (block instanceof ActionBlock) {
							if (((ActionBlock) block).getSystemNames().equals(actionBlock.getSystemNames())) {

								ActionBlock systemBlock = (ActionBlock) block;
								systemBlock.setOwnerID(playerList.get(player).getPlayerID());
								systemBlock.setOwnerName(playerList.get(player).getPlayerName());
								systemBlock.setInvest(true);
								pointstoAdd += 50;

							}
						}
					}

					System.out.println("Investment complete: " + playerList.get(player).getPlayerName()
							+ " now owns the " + actionBlock.getSystemNames() + " system.");
					System.out.println(
							pointstoAdd + " points added to " + playerList.get(player).getPlayerName() + "'s score.");

				} else if (userInvestInput.equals("n")) {
					System.out.println("Investment cancelled... returning to player turn menu.");
					return;
				} else {
					System.out.println("Incorrect input. Please enter either 'y' for Yes or 'n' for No:");
					validInput = false;
				}

			} while (!validInput);
		}
	} // end of invest method

	/**
	 * Performs player development in a block.
	 * 
	 * @param player
	 */
	public static void developBlock(int player) {

		System.out.println("---------------------\n" + "Development\n" + "---------------------");

		System.out.println("Which of your Blocks would you like to develop?\n");

		int option;
		int devIndex;
		int userDevInput;
		String userConfirmInput;
		boolean validInput;
		String system;
		List<Integer> developmentsID = new ArrayList<Integer>();

		do {
			developmentsID.clear();
			option = 1;
			devIndex = 0;

			System.out.printf("%-15s %-15s %-15s %-15s %15s \n", "Option:", "Block No.", "Block Title",
				"Development Tier", "System");

			for (Block block : board) {
				if (block instanceof ActionBlock) {
					ActionBlock devBlock = (ActionBlock) block;
					if (devBlock.getOwnerID() == playerList.get(player).getPlayerID()) {
						developmentsID.add(devBlock.getBlockID());
						system = devBlock.getSystemNames().toString();

						if (devBlock.getDevelopmentTier() == 4) {
							System.out.printf("%-15s %-15d %-15s %-15s %15s \n", " ",
									board.get(developmentsID.get(devIndex) - 1).getBlockID(),
									board.get(developmentsID.get(devIndex) - 1).getBlockTitle(), "Fully Developed",
									system);
						} else {
							System.out.printf("%-15d %-15d %-15s %-15d %15s \n", option,
									board.get(developmentsID.get(devIndex) - 1).getBlockID(),
									board.get(developmentsID.get(devIndex) - 1).getBlockTitle(),
									devBlock.getDevelopmentTier(), system);
						}
						option++;
						devIndex++;
						if (devIndex > 11) {
							devIndex = 0;
						}
					} else {
						continue;
					}
				}
			}

			System.out.println("Press '0' to exit development and return to the player turn menu.");

			do {
				validInput = true;
				while (!scanner.hasNextInt()) {
					System.out.println("Incorrect input type... Please enter a NUMBER.");
					scanner.next();
				}
				userDevInput = scanner.nextInt();

				if (userDevInput >= 1 && userDevInput <= developmentsID.size()) {

					if (((ActionBlock) board.get(developmentsID.get(userDevInput - 1) - 1)).getDevelopmentTier() == 3) {

						int hoursAfterMajorDev = playerList.get(player).getHours()
								- ((ActionBlock) board.get(developmentsID.get(userDevInput - 1) - 1))
										.getMajorDevelopmentCost();

						System.out.println("This block is ready for a major development.");
						System.out.println("Major Development will cost "
								+ ((ActionBlock) board.get(developmentsID.get(userDevInput - 1) - 1))
										.getMajorDevelopmentCost());
						System.out.printf("%-15s  %-20s %-15s\n", "Your resources are:", "Current",
								"After Major Development");
						System.out.printf("%-19s  %-20d %-15d\n", " ", playerList.get(player).getHours(),
								hoursAfterMajorDev);

						if (hoursAfterMajorDev <= 0) {
							System.out
									.println("You do not have enough hours for a major development of this component.");
							int hoursNeeded = ((ActionBlock) board.get(developmentsID.get(userDevInput - 1)))
									.getMajorDevelopmentCost() + 1 - playerList.get(player).getHours();
							System.out.println("You need at least " + hoursNeeded
									+ " more hours to perform a major development of this component, and avoid bankruptcy.");
							System.out.println("Returning to development menu....");
							validInput = false;
							break;
						} else {

							do {
								System.out.println("Would you like to proceed? y/n");

								// Major Development logic.
								userConfirmInput = scanner.next();

								if (userConfirmInput.equals("y")) {
									// Sets dev to 4
									((ActionBlock) board.get(developmentsID.get(userDevInput - 1) - 1))
											.setDevelopmentTier(
													((ActionBlock) board.get(developmentsID.get(userDevInput - 1) - 1))
															.getDevelopmentTier() + 1);
									//Increase Service Charge
									((ActionBlock) board.get(developmentsID.get(userDevInput - 1) - 1)).setServiceCharge(
											((ActionBlock) board.get(developmentsID.get(userDevInput - 1) - 1)).getServiceCharge()+10);
									
									// decrease player resources by cost
									playerList.get(player).decreasePlayerHours(
											((ActionBlock) board.get(developmentsID.get(userDevInput - 1) - 1))
													.getMajorDevelopmentCost());
									System.out.println("Major Development complete: "
											+ playerList.get(player).getPlayerName() + " has fully developed the "
											+ ((ActionBlock) board.get(developmentsID.get(userDevInput - 1) - 1))
													.getBlockTitle()
											+ " block!");
									System.out.println(200 + " points added to "
											+ playerList.get(player).getPlayerName() + "'s score.");
									
									totMajDevs++;
									break;

								} else if (userConfirmInput.equals("n")) {
									System.out.println("Development cancelled. Going back to development menu.");
									validInput = true;
									break;

								} else {
									System.out.println("Incorrect Input: Enter 'y' for Yes or 'n' for No");
								}

							} while (!userConfirmInput.equals("y") || !userConfirmInput.equals("n"));

						}

					} else if (((ActionBlock) board.get(developmentsID.get(userDevInput - 1) - 1))
							.getDevelopmentTier() == 3) {
						System.out.println("Block " + board.get(developmentsID.get(userDevInput - 1) - 1).getBlockID()
								+ " " + board.get(developmentsID.get(userDevInput - 1) - 1).getBlockTitle()
								+ ", is already fully developed");

					} else {

						int hoursAfterDev = playerList.get(player).getHours()
								- ((ActionBlock) board.get(developmentsID.get(userDevInput - 1) - 1))
										.getDevelopmentCost();

						System.out.printf("Block %d %s has a %d tier development cost of %d \n",
								board.get(developmentsID.get(userDevInput - 1) - 1).getBlockID(),
								board.get(developmentsID.get(userDevInput - 1) - 1).getBlockTitle(),
								((ActionBlock) board.get(developmentsID.get(userDevInput - 1) - 1)).getDevelopmentTier()
										+ 1,
								((ActionBlock) board.get(developmentsID.get(userDevInput - 1) - 1))
										.getDevelopmentCost());
						System.out.printf("%-15s  %-20s %-15s\n", "Your resources are:", "Current",
								"After Development");
						System.out.printf("%-19s  %-20d %-15d\n", " ", playerList.get(player).getHours(),
								hoursAfterDev);

						if (hoursAfterDev <= 0) {

							System.out.println("You do not have enough hours to develop this component.");
							int hoursNeeded = ((ActionBlock) board.get(developmentsID.get(userDevInput - 1) - 1))
									.getDevelopmentCost() + 1 - playerList.get(player).getHours();
							System.out.println("You need at least " + hoursNeeded
									+ " more hours to make this development in this component, and avoid bankruptcy.");
							System.out.println("Returning to development menu....\n");
							break;
						} else {
							System.out.println("Are you sure you want to develop this block? y/n");
							do {
								userConfirmInput = scanner.next();
								if (userConfirmInput.equals("y")) {

									// increase development tier by 1
									((ActionBlock) board.get(developmentsID.get(userDevInput - 1) - 1)).setDevelopmentTier(
											((ActionBlock) board.get(developmentsID.get(userDevInput - 1) - 1))
													.getDevelopmentTier() + 1);
									//Increase Service Charge
									((ActionBlock) board.get(developmentsID.get(userDevInput - 1) - 1)).setServiceCharge(
											((ActionBlock) board.get(developmentsID.get(userDevInput - 1) - 1)).getServiceCharge()+5);
									// decrease player resources by cost
									playerList.get(player).decreasePlayerHours(
											((ActionBlock) board.get(developmentsID.get(userDevInput - 1) - 1))
													.getDevelopmentCost());
									System.out.println("Development complete: " + playerList.get(player).getPlayerName()
											+ " has developed the "
											+ ((ActionBlock) board.get(developmentsID.get(userDevInput - 1) - 1))
													.getBlockTitle()
											+ " block!");
									System.out.println(50 + " points added to " + playerList.get(player).getPlayerName()
											+ "'s score.");
									System.out.println(((ActionBlock) board.get(developmentsID.get(userDevInput - 1) - 1))
											.getBlockTitle() + " block new development tier: "
											+ ((ActionBlock) board.get(developmentsID.get(userDevInput - 1) - 1))
													.getDevelopmentTier());
									break;

								} else if (userConfirmInput.equals("n")) {
									System.out.println("Development cancelled. Going back to development menu.");
									break;
								} else {
									System.out.println("Incorrect Input: Enter 'y' for Yes or 'n' for No");
									validInput = false;
								}
							
						} while (!validInput);

						}
					}

				} else if (userDevInput == 0) {

					System.out.println("Exiting Development and returning to player turn menu.");
					return;

				} else {
					System.out.println("Incorrect input. Please an int from 1 to " + developmentsID.size());
					validInput = false;
				}

			} while (!validInput);

		} while (userDevInput != 0);

	} // end of develop method

} // end of ArtemisLite class
