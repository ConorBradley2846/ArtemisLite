/**
 * 
 */
package artemisLite;

import java.util.Comparator;

/**
 * Group 3
 * @author Conor Bradley 40108536 Compares initial dice roll for the start of
 *         the game. Sorts list in Descending order.
 */
public class CompareStartDiceRoll implements Comparator<Player> {

	@Override
	public int compare(Player o1, Player o2) {
		return o2.getStartDiceRoll() - o1.getStartDiceRoll();
	}
}
