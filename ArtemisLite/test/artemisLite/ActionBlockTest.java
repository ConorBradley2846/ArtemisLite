package artemisLite;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Group 3
 * @author Sancha O'Neill
 *
 */
class ActionBlockTest {

	// test data

	String blockTitle;
	String blockDescription;
	int blockID;
	String blockColour;
	SystemNames systemName;
	int initialInvestmentCost;
	int developmentTier;
	int developmentCost;
	int majorDevelopmentCost;
	int ownerID;
	String ownerName;
	int serviceCharge;
	boolean develop;
	boolean invest;

	ActionBlock block;

	@BeforeEach
	void setUp() throws Exception {

		blockTitle = "Lunar Landers";
		blockDescription = "Lunar landers, an element of the Gateway and Lunar landers system";
		blockID = 10;
		blockColour = "green";
		systemName = SystemNames.GATEWAYANDLUNARLANDERS;
		initialInvestmentCost = 10;
		developmentTier = 3;
		developmentCost = 20;
		majorDevelopmentCost = 30;
		ownerID = 123;
		ownerName = "validOwnerName";
		serviceCharge = 5;
		develop = false;
		invest = false;

		block = new ActionBlock();

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

	@Test
	void testActionBlockDeaultCon() {
		ActionBlock b = new ActionBlock();
		assertNotNull(b);

	}

	@Test
	void testActionBlockConWithArgs() {

		ActionBlock b = new ActionBlock(blockTitle, blockDescription, blockID, blockColour, systemName,
				initialInvestmentCost, developmentTier, developmentCost, majorDevelopmentCost, serviceCharge);

		assertEquals(blockTitle, b.getBlockTitle());
		assertEquals(blockDescription, b.getBlockDesc());
		assertEquals(blockID, b.getBlockID());
		assertEquals(blockColour, b.getBlockColour());
		assertEquals(systemName, b.getSystemNames());
		assertEquals(initialInvestmentCost, b.getInitialInvestmentCost());
		assertEquals(developmentTier, b.getDevelopmentTier());
		assertEquals(developmentCost, b.getDevelopmentCost());
		assertEquals(majorDevelopmentCost, b.getMajorDevelopmentCost());
		assertEquals(serviceCharge, b.getServiceCharge());
		assertEquals("No owner yet", b.getOwnerName());
		assertEquals(invest, b.isInvest());
		assertEquals(develop, b.isDevelop());
		assertEquals(0, b.getOwnerID());

	}

	@Test
	void testGetSetSystemNames() {
		block.setSystemNames(systemName);
		assertEquals(systemName, block.getSystemNames());
	}

	@Test
	void testGetSetInitialInvestmentCost() {
		block.setInitialInvestmentCost(initialInvestmentCost);
		assertEquals(initialInvestmentCost, block.getInitialInvestmentCost());
	}

	@Test
	void testGetSetDevelopmentTier() {
		block.setDevelopmentTier(developmentTier);
		assertEquals(developmentTier, block.getDevelopmentTier());
	}

	@Test
	void testGetSetDevelopmentCost() {
		block.setDevelopmentCost(developmentCost);
		assertEquals(developmentCost, block.getDevelopmentCost());
	}

	@Test
	void testGetSetMajorDevelopmentCost() {
		block.setMajorDevelopmentCost(majorDevelopmentCost);
		assertEquals(majorDevelopmentCost, block.getMajorDevelopmentCost());
	}

	@Test
	void testGetSetOwnerID() {
		block.setOwnerID(ownerID);
		assertEquals(ownerID, block.getOwnerID());
	}

	@Test
	void testGetSetOwnerName() {
		block.setOwnerName(ownerName);
		assertEquals(ownerName, block.getOwnerName());
	}

	@Test
	void testGetSetServiceCharge() {
		block.setServiceCharge(serviceCharge);
		assertEquals(serviceCharge, block.getServiceCharge());
	}

	@Test
	void testIsSetInvest() {
		block.setInvest(invest);
		assertEquals(invest, block.isInvest());
	}

	@Test
	void testIsSetDevelop() {
		block.setDevelop(develop);
		assertEquals(develop, block.isDevelop());
	}

	@Test
	void testActionBlockToString() {

		ActionBlock b = new ActionBlock(blockTitle, blockDescription, blockID, blockColour, systemName,
				initialInvestmentCost, developmentTier, developmentCost, majorDevelopmentCost, serviceCharge);
		assertEquals(
				"ActionBlock. System:GATEWAYANDLUNARLANDERS. Initial Investment Cost:10. Development Tiers:3. Development Cost:20. Major Development Cost:30. Owner ID:0. Owner Name:No owner yet. Service Charge:5. Invest?false. Develop?false.",
				b.toString());

	}

}
