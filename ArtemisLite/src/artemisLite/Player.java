/**
 * 
 */
package artemisLite;

/**
 * Group 3
 * @author Laura Gaffey 0604
 * @author Conor Bradley 40108536
 */
public class Player {

	// instance vars for player details
	private String playerName;
	private int playerID;
	private int startDiceRoll;
	private int highScore;

	// resources
	private boolean isBankrupt;
	private int hours;
	private int charity;

	// players current position on the board and player state
	private int playerPosition;
	private boolean passedGo;
	private boolean isDeveloper;

	// CONSTANTS
	// number of players btwn 1 -4. Player ID can then be playerID=1, playerID=2
	// etc..
	private final static int MIN_PLAYER = 1;
	private final static int MAX_PLAYER = 4;
	// number of squares on board (to update position)
	private final static int MAX_SQUARES = 12;

	/**
	 * default constructor
	 */
	public Player() {

	}

	/**
	 * Argument Constructor
	 * 
	 * @param playerName
	 * @param playerID
	 * @param isBankrupt
	 * @param playerPoints
	 * @param softwareEng
	 * @param mechanicalEng
	 * @param playerBP
	 * @param isDeveloper
	 */
	public Player(String playerName, int playerID, int hours, int playerPosition) {
		this.setPlayerName(playerName);
		this.setPlayerID(playerID);
		this.setHours(hours);
		this.setPlayerPosition(playerPosition);
		this.charity = 0;
		isBankrupt = false;
		isDeveloper = false;
	}

	/**
	 * @return the playerName
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * @param playerName the playerName to set
	 */
	public void setPlayerName(String playerName) throws IllegalArgumentException {

		if (playerName.length() > 0) {
			this.playerName = playerName;
		} else {
			throw new IllegalArgumentException("Enter at least 1 character for user name");
		}
	}

	/**
	 * @return the playerID
	 */
	public int getPlayerID() {
		return playerID;
	}

	/**
	 * @param playerID the playerID to set
	 */
	public void setPlayerID(int playerID) throws IllegalArgumentException {

		if (playerID >= MIN_PLAYER && playerID <= MAX_PLAYER) {
			this.playerID = playerID;
		} else {
			throw new IllegalArgumentException("Invalid player ID Should be between 1 -4 players");
		}
	}

	/**
	 * @return the isBankrupt
	 */
	public boolean isBankrupt() {
		return isBankrupt;
	}

	/**
	 * @param isBankrupt the isBankrupt to set
	 */
	public void setBankrupt(boolean isBankrupt) {

		this.isBankrupt = true;

		if (playerPosition == 0) {
			this.isBankrupt = isBankrupt;
		} else {
			this.isBankrupt = false;
		}
	}

	/**
	 * @return the hours (resources)
	 */
	public int getHours() {
		return hours;
	}

	/**
	 * @param hours (resources)
	 */
	public void setHours(int hours) {
		this.hours = hours;
	}

	/**
	 * TEST this method works in main Artemis class flag true= player passes go
	 * amount = amount of resources added to player's resources
	 * 
	 * @param ammount
	 */
	public void increasePlayerHours(int amount) {
		hours = hours + amount;
	}

	/**
	 * TEST this method works in main Artemis class method to decrease player
	 * resources when they pay another or player or spend resources developing
	 * player points
	 * 
	 * @param ammount
	 */
	public void decreasePlayerHours(int amount) {
		hours = hours - amount;
	}

	/**
	 * @return the playerBP
	 */
	public int getPlayerPosition() {
		return playerPosition;
	}

	/**
	 * @param playerBP the playerBP to set
	 */
	public void setPlayerPosition(int playerPosition) {
		this.playerPosition = playerPosition;
	}

	/**
	 * Updates players position according to the dice roll and allocates resources
	 * if player has passed the start block.
	 * 
	 * @param rollTot
	 */
	public void updatePlayerPosition(int rollTot) {
		passedGo = false;
		int tempPosition;
		tempPosition = rollTot + playerPosition;
		if (tempPosition > MAX_SQUARES) {
			playerPosition = tempPosition - MAX_SQUARES;
			if (tempPosition == 1) {
				this.increasePlayerHours(50);
			} else {
				passedGo = true;
				this.increasePlayerHours(50);
			}
		} else {
			playerPosition = tempPosition;
		}
	}

	/**
	 * Returns boolean value to the caller to show weather or not the player has
	 * passed the Go block.
	 * 
	 * @return passedGo
	 */
	public boolean getPassedGo() {
		return passedGo;
	}

	/**
	 * @return the isDeveloper
	 */
	public boolean isDeveloper() {
		return isDeveloper;
	}

	/**
	 * @param isDeveloper the isDeveloper to set
	 */
	public void setDeveloper(boolean isDeveloper) {
		this.isDeveloper = isDeveloper;
	}

	/**
	 * Gets the beginning roll of the game to determine order of players turns.
	 * 
	 * @return the startDiceRoll
	 */
	public int getStartDiceRoll() {
		return startDiceRoll;
	}

	/**
	 * Sets the beginning roll of the game to determine order of players turns.
	 * 
	 * @param startDiceRoll the startDiceRoll to set
	 */
	public void setStartDiceRoll(int startDiceRoll) {
		this.startDiceRoll = startDiceRoll;
	}

	/**
	 * @return the charity
	 */
	public int getCharity() {
		return charity;
	}

	/**
	 * @param charity the charity to set
	 */
	public void setCharity(int charity) {
		this.charity = charity;
	}

	/**
	 * @return the highScore
	 */
	public int getHighScore() {
		return highScore;
	}

	/**
	 * @param highScore the highScore to set
	 */
	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}

} // end of player class.
