/**
 * 
 */
package artemisLite;

import java.util.Comparator;

/**
 * Group 3
 * @author Conor Bradley 40108536 Compares high score of each player. Sorts list
 *         in Descending order.
 */
public class CompareHighScore implements Comparator<Player> {

	@Override
	public int compare(Player o1, Player o2) {
		return o2.getHighScore() - o1.getHighScore();
	}
}
