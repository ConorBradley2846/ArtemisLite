package artemisLite;

/**
 * Group 3
 *  @author LauraGAF 0604
 */
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerTest {

	// test data
	String playerNameValid, playerNameInvalid;
	int playerValidIdLw, playerValidIdMd, playerValidIdUp, playerInvalidLw, playerInvalidIdUp;

	boolean isBankrupt, isDeveloper;

	int playerBP;

	int hours;
	int hoursNone;
	int charity;
	int playerPosition;
	int highscore;
	int startDiceRoll;

	Player player1, player2;

	@BeforeEach
	void setUp() throws Exception {

		playerNameValid = "validUser";
		playerNameInvalid = "";
		playerValidIdLw = 1;
		playerValidIdMd = 3;
		playerValidIdUp = 4;
		playerInvalidLw = 0;
		playerInvalidIdUp = 5;
		playerPosition = 9;
		charity = 200;

		isBankrupt = false;
		isDeveloper = true;

		playerBP = 11;
		highscore = 1000;

		startDiceRoll = 5;

		hours = 5000;

		hoursNone = 0;

		player1 = new Player(playerNameValid, playerValidIdLw, hours, playerPosition);

		player2 = new Player(playerNameValid, playerValidIdLw, hours, playerPosition);

	}

	@Test
	void testPlayerConstructorWArgsValid() {

		assertEquals(playerNameValid, player1.getPlayerName());
		assertEquals(playerValidIdLw, player1.getPlayerID());
		assertEquals(hours, player1.getHours());
		assertEquals(playerPosition, player1.getPlayerPosition());
		assertEquals(0, player1.getCharity());
		assertEquals(false, player1.isBankrupt());
		assertEquals(false, player1.isDeveloper());

	}

	@Test
	void testPlayerConWArgsInvalid() {
		IllegalArgumentException illegalArgumentException;

		illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
			Player player = new Player(playerNameInvalid, playerValidIdLw, hours, playerPosition);
		});
		illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
			Player player = new Player(playerNameValid, playerInvalidLw, hours, playerPosition);
		});

	}
//
//	@Test
//	void testPlayerConstructorWArgsValidBankrupt() {
//
//		assertEquals(playerNameValid, player2.getPlayerName());
//		assertEquals(playerValidIdUp, player2.getPlayerID());
//		assertEquals(isBankrupt, player2.isBankrupt());
//		assertEquals(hoursNone, player2.getHours());
//		assertEquals(playerBP, player2.getPlayerBP());
//		assertEquals(isDeveloper, player2.isDeveloper());
//
//	}

//	@Test
//	void testPlayerConstructorWargsInvalid() {
//
//		IllegalArgumentException illegalArgumentException;
//
//		illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
//			Player player = new Player(playerNameInvalid, playerValidIdUp, isBankrupt, hours, playerBP, isDeveloper);
//		});
//		illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
//			Player player = new Player(playerNameValid, playerInvalidIdUp, isBankrupt, hoursNone, playerBP,
//					isDeveloper);
//		});
//
//	}

	@Test
	void testDefaultCon() {
		Player player3 = new Player(playerNameValid, playerValidIdLw, hours, playerPosition);
		assertNotNull(player3);

	}

	@Test
	void testSetGetPlayerName() {

		player1.setPlayerName(playerNameValid);
		assertEquals(playerNameValid, player1.getPlayerName());
	}

	@Test
	void testSetGetPlayerInvalidName() {

		Player player = new Player();
		IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
				() -> player.setPlayerName(playerNameInvalid));
		// check for exception message
		assertEquals("Enter at least 1 character for user name", illegalArgumentException.getMessage());

	}

	@Test
	void testSetGetPlayerValidID() {

		player1.setPlayerID(playerValidIdLw);
		assertEquals(playerValidIdLw, player1.getPlayerID());

		player1.setPlayerID(playerValidIdMd);
		assertEquals(playerValidIdMd, player1.getPlayerID());

		player1.setPlayerID(playerValidIdUp);
		assertEquals(playerValidIdUp, player1.getPlayerID());

	}

	@Test
	void testSetGetPlayerInvalidID() {
		Player player = new Player();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
				() -> player.setPlayerID(playerInvalidIdUp));
		assertEquals("Invalid player ID Should be between 1 -4 players", e.getMessage());

		e = assertThrows(IllegalArgumentException.class, () -> player.setPlayerID(playerInvalidLw));
		assertEquals("Invalid player ID Should be between 1 -4 players", e.getMessage());

	}

	@Test
	void testNotBankrupt() {

		player1.setBankrupt(isBankrupt);
		assertEquals(false, player1.isBankrupt());
	}

	@Test
	void testIsBankruptIsTrueIfPositionIs0() {
		player2.setPlayerPosition(0);
		player2.setBankrupt(true);
		assertEquals(true, player2.isBankrupt());
	}

	@Test
	void testIsBankruptIsFalseIfPositionIsNot0() {
		player2.setPlayerPosition(1);

		player2.setBankrupt(isBankrupt);
		assertEquals(isBankrupt, player2.isBankrupt());
	}

	@Test
	void testIsDeveloper() {
		player2.setDeveloper(isDeveloper);
		assertEquals(true, player2.isDeveloper());
	}

	@Test
	void testGetPlayerHours() {

		player1.setHours(hours);
		assertEquals(hours, player1.getHours());
	}

	@Test
	void testGetPlayerPointsNone() {

		player2.setHours(hours);
		assertEquals(hours, player2.getHours());
	}

//	@Test
//	void testGetPlayerBP() {
//		player1.setPlayerBP(playerBP);
//		assertEquals(playerBP, player1.getPlayerBP());
//	}

	/**
	 * Test if over max squares - 12 then test if tempoPos = 1 playerHours += 50
	 * (assertion) else passedGo = true (assertion) playerHours += 50 (assertion)
	 * else new playerposition = tempposition (assertion)
	 */
	@Test
	void testUpdatePlayerPositionIfRollTotalLessThanSquares() {
		// Create unique player, assert their current position
		Player player = new Player(playerNameValid, playerValidIdLw, hours, playerPosition);
		player.setPlayerPosition(1);
		player.setHours(1);

		// Give player a roll which totals under 12 (max)
		player.updatePlayerPosition(2);
		assertEquals(3, player.getPlayerPosition());
		assertEquals(1, player.getHours());
		assertFalse(player.getPassedGo());
	}

	@Test
	void testUpdatePlayerPositionIfRollTotalLandsAfterGo() {
		// Create unique player, assert their current position
		Player player = new Player(playerNameValid, playerValidIdLw, hours, playerPosition);
		player.setPlayerPosition(12);
		player.setHours(2);

		// Give player a roll which totals over 12 and lands at GO + 2
		player.updatePlayerPosition(2);
		assertEquals(2, player.getPlayerPosition());
		assertEquals(52, player.getHours());
		assertTrue(player.getPassedGo());
	}

	@Test
	void testGetSetCharity() {

		player1.setCharity(charity);
		assertEquals(charity, player1.getCharity());

	}

	@Test
	void testGetSetHighScore() {

		player1.setHighScore(highscore);
		assertEquals(highscore, player1.getHighScore());

	}

	@Test
	void testGetSetStartDiceRoll() {

		player1.setStartDiceRoll(startDiceRoll);
		assertEquals(startDiceRoll, player1.getStartDiceRoll());

	}

	@Test
	void testGetSetPlayerPosition() {

		player1.setPlayerPosition(playerPosition);
		assertEquals(playerPosition, player1.getPlayerPosition());

	}

	@Test
	void testIncreasePlayerHours() {
		Player player = new Player(playerNameValid, playerValidIdLw, hours, playerPosition);
		player.setHours(0);

		player.increasePlayerHours(10);
		assertEquals(10, player.getHours());

	}

	@Test

	void testDecreasePlayerHours() {
		Player player = new Player(playerNameValid, playerValidIdLw, hours, playerPosition);
		player.setHours(10);

		player.decreasePlayerHours(5);
		assertEquals(5, player.getHours());

	}
}