/**
 * 
 */
package artemisLite;



/**
 * This class represents a block in the game system
 * Group 3
 * @author Sancha O'Neill
 * @author Conor Bradley 40108536
 *
 */
public abstract class Block {

	private String blockTitle;
	private String blockDesc;
	private int blockID;
	private String blockColour;

	/**
	 * Default constructor
	 */
	public Block() {

	}

	/**
	 * Constructor with args
	 * 
	 * @param blockTitle
	 * @param blockDesc
	 * @param blockID
	 * @param blockColour
	 */
	public Block(String blockTitle, String blockDesc, int blockID, String blockColour) {
		this.blockTitle = blockTitle;
		this.blockDesc = blockDesc;
		this.blockID = blockID;
		this.blockColour = blockColour;
	}

	public String getBlockTitle() {
		return blockTitle;
	}

	public void setBlockTitle(String blockTitle) {
		this.blockTitle = blockTitle;
	}

	public String getBlockDesc() {
		return blockDesc;
	}

	public void setBlockDesc(String blockDesc) {
		this.blockDesc = blockDesc;
	}

	public int getBlockID() {
		return blockID;
	}

	public void setBlockID(int blockID) {
		this.blockID = blockID;
	}

	public String getBlockColour() {
		return blockColour;
	}

	public void setBlockColour(String colour) {
		this.blockColour = colour;
	}

	@Override
	public String toString() {
		return "Block title:" + blockTitle + ". Block Description:" + blockDesc + ". Block ID:" + blockID
				+ ". Block colour:" + blockColour + ".";
	}

	/**
	 * display all method that can print all attributes to screen
	 */
	public void displayAll() {

		System.out.printf("Block %d: %s. %s\n", this.blockID, this.blockTitle, this.blockDesc);
		System.out.println("Block colour :                 " + this.blockColour);

	}

}
