package artemisLite;

import java.util.Random;

/**
 * Represents a dice. Generates a random number from 1 - 6.
 * Group 3
 * @author Michelle Oakes
 */
public class RollDice {

	private int dieRoll;

	/**
	 * Generates random number 1-6 to represent roll of a die.
	 */
	public int rollDie() {

		Random random = new Random();
		dieRoll = random.nextInt(6) + 1;
		return dieRoll;
	}

}
