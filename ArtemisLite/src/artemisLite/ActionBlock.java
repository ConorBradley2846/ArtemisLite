/**
 * 
 */
package artemisLite;

/**
 * This class represents an action block A player can invest and take charge of
 * it It can be developed and after 3 developments a major development can occur
 * These blocks will make up the 4 systems
 * 
 * @author Sancha O'Neill
 * @author Conor Bradley 40108536
 *
 */
public class ActionBlock extends Block {

	private SystemNames systemName;

	private int initialInvestmentCost;

	private int developmentTier;

	private int developmentCost;

	private int majorDevelopmentCost;

	private int ownerID;

	private String ownerName;

	private int serviceCharge;

	private boolean invest;

	// changed to boolean to allow for y/n re player actions
	private boolean develop;

	/**
	 * Default Constructor
	 */
	public ActionBlock() {

	}

	/**
	 * Arguments Constructor
	 * 
	 * @param blockTitle
	 * @param blockDesc
	 * @param blockID
	 * @param blockColour
	 * @param systemName
	 * @param initialInvestmentCost
	 * @param developmentTier
	 * @param developmentCost
	 * @param majorDevelopmentCost
	 * @param serviceCharge
	 */
	public ActionBlock(String blockTitle, String blockDesc, int blockID, String blockColour, SystemNames systemName,
			int initialInvestmentCost, int developmentTier, int developmentCost, int majorDevelopmentCost,
			int serviceCharge) {

		super(blockTitle, blockDesc, blockID, blockColour);

		this.systemName = systemName;
		this.initialInvestmentCost = initialInvestmentCost;
		this.developmentTier = developmentTier;
		this.developmentCost = developmentCost;
		this.majorDevelopmentCost = majorDevelopmentCost;
		this.serviceCharge = serviceCharge;

		ownerName = "No owner yet";
		invest = false;
		develop = false;
		ownerID = 0;

	}

	// private Set<Integer> playersOn will need added to con when we
	// figure out how to display all players on block

	public SystemNames getSystemNames() {
		return this.systemName;
	}

	public void setSystemNames(SystemNames systemName) {
		this.systemName = systemName;
	}

	public int getInitialInvestmentCost() {
		return initialInvestmentCost;
	}

	public void setInitialInvestmentCost(int initialInvestmentCost) {
		this.initialInvestmentCost = initialInvestmentCost;
	}

	public int getDevelopmentTier() {
		return developmentTier;
	}

	public void setDevelopmentTier(int developmentTier) {

		this.developmentTier = developmentTier;

	}

	public int getDevelopmentCost() {
		return developmentCost;
	}

	public void setDevelopmentCost(int developmentCost) {
		this.developmentCost = developmentCost;
	}

	public int getMajorDevelopmentCost() {
		return majorDevelopmentCost;
	}

	public void setMajorDevelopmentCost(int majorDevelopmentCost) {
		this.majorDevelopmentCost = majorDevelopmentCost;
	}

	public int getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(int ownerID) {
		this.ownerID = ownerID;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public int getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(int serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public boolean isInvest() {
		return invest;
	}

	public void setInvest(boolean invest) {
		this.invest = invest;
	}

	public boolean isDevelop() {
		return develop;
	}

	public void setDevelop(boolean develop) {
		this.develop = develop;
	}

	@Override
	public String toString() {
		return "ActionBlock. System:" + systemName + ". Initial Investment Cost:" + initialInvestmentCost
				+ ". Development Tiers:" + developmentTier + ". Development Cost:" + developmentCost
				+ ". Major Development Cost:" + majorDevelopmentCost + ". Owner ID:" + ownerID + ". Owner Name:"
				+ ownerName + ". Service Charge:" + serviceCharge + ". Invest?" + invest + ". Develop?" + develop + ".";
	}

	@Override
	public void displayAll() {
		super.displayAll();
		System.out.println("System :                       " + this.systemName);
		System.out.println("Owner name :                   " + this.ownerName);
		System.out.println("Initial investment cost :      " + this.initialInvestmentCost);
		System.out.println("Current Development tier :     " + this.developmentTier);
		System.out.println("Development cost :             " + this.developmentCost);
		System.out.println("Major development cost :       " + this.majorDevelopmentCost);
		System.out.println("Service charge :               " + this.serviceCharge);

	}

}
