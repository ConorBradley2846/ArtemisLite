package artemisLite;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Group 3
 * @author Sancha O'Neill
 *
 */
class DoNothingBlockTest {

	// test data

	String blockTitle;
	String blockDescription;
	int blockID;
	String blockColour;

	DoNothingBlock block;

	@BeforeEach
	void setUp() throws Exception {

		blockTitle = "Lunar Landers";
		blockDescription = "Lunar landers, an element of the Gateway and Lunar landers system";
		blockID = 10;
		blockColour = "green";

		block = new DoNothingBlock();

	}

	@Test
	void testDoNothingBlockToString() {
		DoNothingBlock b3 = new DoNothingBlock(blockTitle, blockDescription, blockID, blockColour);
		assertEquals(
				"DoNothingBlock. Block Title:Lunar Landers. Block Description:Lunar landers, an element of the Gateway and Lunar landers system. Block ID:10. Block Colour:green.",
				b3.toString());
	}

	@Test
	void testDoNothingBlockDefaultCon() {
		DoNothingBlock b1 = new DoNothingBlock();
		assertNotNull(b1);
	}

	@Test
	void testDoNothingBlockConWithArgs() {
		DoNothingBlock b2 = new DoNothingBlock(blockTitle, blockDescription, blockID, blockColour);
		assertEquals(blockTitle, b2.getBlockTitle());
		assertEquals(blockDescription, b2.getBlockDesc());
		assertEquals(blockID, b2.getBlockID());
		assertEquals(blockColour, b2.getBlockColour());

	}

	@Test
	void testGetSetBlockTitle() {
		block.setBlockTitle(blockTitle);
		assertEquals(blockTitle, block.getBlockTitle());
	}

	@Test
	void testGetSetBlockDesc() {
		block.setBlockDesc(blockDescription);
		assertEquals(blockDescription, block.getBlockDesc());
	}

	@Test
	void testGetSetBlockID() {
		block.setBlockID(blockID);
		assertEquals(blockID, block.getBlockID());
	}

	@Test
	void testGetSetBlockColour() {
		block.setBlockColour(blockColour);
		assertEquals(blockColour, block.getBlockColour());
	}

}
