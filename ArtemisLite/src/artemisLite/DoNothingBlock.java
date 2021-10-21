/**
 * 
 */
package artemisLite;

/**
 * This class represents a do nothing block It cannot be developed nor invested in.
 * Group 3
 * @author Sancha O'Neill
 *
 */
public class DoNothingBlock extends Block {

	/**
	 * Default Constructor
	 */
	public DoNothingBlock() {

	}

	/**
	 * Con with args
	 * 
	 * @param blockTitle
	 * @param blockDesc
	 * @param blockID
	 * @param blockColour
	 */
	public DoNothingBlock(String blockTitle, String blockDesc, int blockID, String blockColour) {
		super(blockTitle, blockDesc, blockID, blockColour);
	}

	@Override
	public String toString() {
		return "DoNothingBlock. Block Title:" + getBlockTitle() + ". Block Description:" + getBlockDesc()
				+ ". Block ID:" + getBlockID() + ". Block Colour:" + getBlockColour() + ".";
	}

	@Override
	public void displayAll() {
		super.displayAll();
	}

}
