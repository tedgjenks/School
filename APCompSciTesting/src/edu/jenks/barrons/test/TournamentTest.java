/**
 * 
 */
package edu.jenks.barrons.test;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import edu.jenks.dist.barrons.tournament.AbstractTournament;
import edu.jenks.dist.barrons.tournament.Player;
import edu.jenks.test.Testable;
import edu.jenks.util.ReflectionUtil;

/**
 * @author Ted Jenks
 *
 */
public class TournamentTest extends Testable {
	private static final Class<?>[] CONSTRUCTOR_PARAM_TYPES = {Player[].class};
	private static final byte NUM_SLOTS = 100, RANDOM_GROUP_SIZE = 5, RANDOM_GROUPS = NUM_SLOTS / RANDOM_GROUP_SIZE;
	private static final Random RANDOM = new Random(System.currentTimeMillis());
	private Player[] expSlots = new Player[NUM_SLOTS];
	private List<String> expWaitingList = new LinkedList<String>();
	private AbstractTournament studentTournament;

	/**
	 * 
	 */
	public TournamentTest() {}
	
	// 10 points
	public void test04CancelAndReassignSlot() {
		int points = 5, startIndex;
		String method = "cancelAndReassignSlot - waiting list empty";
		boolean passAll = true;
		Player playerToRemove, actWaitingPlayer;
		
		startIndex = RANDOM.nextInt(20) * RANDOM_GROUP_SIZE;
		playerToRemove = findRandomPlayer(startIndex, startIndex + RANDOM_GROUP_SIZE);
		expSlots[playerToRemove.getPlayerNumber()] = null;
		actWaitingPlayer = studentTournament.cancelAndReassignSlot(playerToRemove);
		if(actWaitingPlayer != null || !expEqualsAct(expSlots, expWaitingList, studentTournament, method + " - first", points)) {
			continueTesting = passAll = false;
			return;
		}
		/*for(int index = expSlots.length - 1, nullCount = 0; index >= 0; index--) {
			if(expSlots[index] == null) {
				System.out.println("nullCount: " + ++nullCount);
			}
			assert(nullCount < 2);
		}
		System.out.print("Start indexes: ");*/
		for(int count = 99; count > 0; count--) {
			startIndex = RANDOM.nextInt(RANDOM_GROUPS) * RANDOM_GROUP_SIZE;
			//System.out.print(startIndex + ", ");
			playerToRemove = findRandomPlayer(startIndex, expSlots.length);
			expSlots[playerToRemove.getPlayerNumber()] = null;
			studentTournament.cancelAndReassignSlot(playerToRemove);
		}
		//System.out.println("End cancels");
		if(!expEqualsAct(expSlots, expWaitingList, studentTournament, method + " - first", points)) {
			continueTesting = passAll = false;
			return;
		}
		
		if(passAll)
			logPass(method);
	}
	
	private Player findRandomPlayer(int startIndex, int endIndex) {
		if(startIndex < 0)
			startIndex = 0;
		Player p = null;
		for(int index = startIndex; p == null && index < endIndex; index++)
			p = expSlots[index];
		
		if(p != null || startIndex <= 0)
			return p;
		else
			return findRandomPlayer(startIndex - 5, startIndex);
	}
	
	// 10 points
	public void test03CancelAndReassignSlot() {
		int points = 5, index;
		String method = "cancelAndReassignSlot";
		boolean passAll = true;
		
		index = RANDOM.nextInt(NUM_SLOTS);
		expSlots[index] = new Player(expWaitingList.remove(0), index);
		Player playerToRemove = studentTournament.getSlots()[index];
		Player actPlayer = studentTournament.cancelAndReassignSlot(playerToRemove);
		if(actPlayer == null || !expEqualsAct(expSlots, expWaitingList, studentTournament, method + " - first cancel", points)) {
			continueTesting = passAll = false;
			return;
		}
		
		for(int waitingPlayers = expWaitingList.size(); waitingPlayers > 0; waitingPlayers--) {
			index = RANDOM.nextInt(NUM_SLOTS);
			expSlots[index] = new Player(expWaitingList.remove(0), index);
			playerToRemove = studentTournament.getSlots()[index];
			actPlayer = studentTournament.cancelAndReassignSlot(playerToRemove);
		}
		if(!expEqualsAct(expSlots, expWaitingList, studentTournament, method + " - cancel and add all waiting list", points)) {
			continueTesting = passAll = false;
			return;
		}
		
		
		if(passAll)
			logPass(method);
	}
	
	// 10 points
	public void test02RequestNoAvailableSlot() {
		int points = 5;
		String method = "requestNoAvailableSlot", playerName;
		boolean passAll = true;
		
		playerName = "W0";
		expWaitingList.add(playerName);
		Player actPlayer = studentTournament.requestSlot(playerName);
		if(actPlayer != null || !expEqualsAct(expSlots, expWaitingList, studentTournament, method + " - first waiting list request", points)) {
			continueTesting = passAll = false;
			return;
		}
		
		for(int count = 1; count < 20; count++) {
			playerName = "W" + count;
			expWaitingList.add(playerName);
			studentTournament.requestSlot(playerName);
		}
		if(!expEqualsAct(expSlots, expWaitingList, studentTournament, method + " - multiple waiting list requests", points))
			continueTesting = passAll = false;
		
		if(passAll)
			logPass(method);
	}
	
	// 10 points
	public void test01RequestAvailableSlot() {
		int points = 5, index;
		String method = "requestAvailableSlot", playerName;
		boolean passAll = true;
		Player actPlayer;
		
		index = 0;
		playerName = createPlayerName(index);
		Player newPlayer = new Player(playerName, index);
		expSlots[index] = newPlayer;
		actPlayer = studentTournament.requestSlot(playerName);
		if(!newPlayer.equals(actPlayer) || !expEqualsAct(expSlots, expWaitingList, studentTournament, method + " - first request", points)) {
			continueTesting = passAll = false;
			return;
		}
		
		for(index = 1; index < NUM_SLOTS; index++) {
			playerName = createPlayerName(index);
			expSlots[index] = new Player(playerName, index);
			studentTournament.requestSlot(playerName);
		}
		
		if(!expEqualsAct(expSlots, expWaitingList, studentTournament, method + " - fill slots", points))
			continueTesting = passAll = false;
		
		if(passAll)
			logPass(method);
	}
	
	private boolean expEqualsAct(Player[] expSlots, List<String> expWaitingList, AbstractTournament act, String message, int points) {
		boolean pass = true;
		Player[] actSlots = act.getSlots();
		if(!Arrays.equals(expSlots, actSlots)) {
			logFail(message + " - slots", Arrays.toString(expSlots), Arrays.toString(actSlots), points);
			pass = false;
		}
		
		List<String> actWaitingList = act.getWaitingList();
		if(!expWaitingList.equals(actWaitingList)) {
			logFail(message = " - waitingList", Arrays.toString(expWaitingList.toArray()), Arrays.toString(actWaitingList.toArray()), points);
			pass = false;
		}
		
		if(pass)
			totalPoints += points;
		return pass;
	}
	
	private String createPlayerName(int index) {
		return "P" + index;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#getPointsAvailable()
	 */
	@Override
	public int getPointsAvailable() {
		return 100;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#buildStudentClassNameToSuperclassName()
	 */
	@Override
	public Map<String, String> buildStudentClassNameToSuperclassName() {
		Map<String, String> map = new HashMap<String, String>(4);
		map.put(studentPackage + ".Tournament", AbstractTournament.class.getName());
		return map;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#setUp()
	 */
	@Override
	public void setUp() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object[] args = {new Player[NUM_SLOTS]};
		studentTournament = (AbstractTournament)ReflectionUtil.newInstance(studentPackage + ".Tournament", CONSTRUCTOR_PARAM_TYPES, args);
		totalPoints += 60;
	}

}
