
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import game.Character;
import game.Coordinates;
import game.Coordinates2D;
import game.DrLucky;
import game.Item;
import game.MansionSpace;
import game.MansionWorld;
import game.Pet;
import game.Space;
import game.Weapon;
import game.World;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Class to test the MansionWorld.
 *
 */
public class MansionWorldTest {

  private World testworld;

  /**
   * Setup the world object for every test.
   */
  @Before
  public void setUp() {

    Item weapon = new Weapon(0, "Knife", 10, 0);
    Item weapon0 = new Weapon(1, "Bat", 10, 1);

    Coordinates upper0 = new Coordinates2D(1, 3);
    Coordinates lower0 = new Coordinates2D(4, 4);

    List<Item> items0 = new ArrayList<>();
    items0.add(weapon0);

    Space space0 = new MansionSpace(0, "Garage", upper0, lower0, new ArrayList<Item>(),
        new ArrayList<Space>());

    List<Item> items = new ArrayList<>();
    items.add(weapon);
    List<Space> neighbors = new ArrayList<>();
    neighbors.add(space0);

    Coordinates upper = new Coordinates2D(0, 0);
    Coordinates lower = new Coordinates2D(2, 2);

    Space space = new MansionSpace(1, "Kitchen", upper, lower, new ArrayList<Item>(),
        new ArrayList<Space>());

    List<Space> allspaces = new ArrayList<>();
    allspaces.add(space);
    allspaces.add(space0);

    List<Item> allitems = new ArrayList<>();
    allitems.add(weapon0);
    allitems.add(weapon);

    Character drlucky = new DrLucky("Dr Lucky", 50, new Pet("Sloppy"));

    testworld = new MansionWorld("House", 50, 50, 2, 2, allitems, allspaces, drlucky, 10);
  }

  @Test
  public void testWorldSpecifications() {

    List<Space> allspaces = new ArrayList<>();
    List<Item> allitems = new ArrayList<>();
    Character drlucky = new DrLucky("Dr Lucky", 50, new Pet("Sloppy"));
    StringReader input = new StringReader("20 30 Doctor Lucky's Mansion \n");

    testworld = new MansionWorld("House", 20, 30, 2, 2, allitems, allspaces, drlucky, 10);

    String result = testworld.scanFile(input);
    String expected = "World Specifications \nlength: 20\nWidth: 30\nWorld Name: "
        + "Doctor Lucky's Mansion\n";

    assertEquals(expected, result);

  }

  @Test(expected = IllegalStateException.class)
  public void testInvalidWorldSpecifications() {

    List<Space> allspaces = new ArrayList<>();
    List<Item> allitems = new ArrayList<>();
    Character drlucky = new DrLucky("Dr Lucky", 50, new Pet("Sloppy"));
    StringReader input = new StringReader("twenty 30 Doctor Lucky's Mansion \n");

    testworld = new MansionWorld("House", 20, 30, 2, 2, allitems, allspaces, drlucky, 10);

    testworld.scanFile(input);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeLength() {

    List<Space> allspaces = new ArrayList<>();
    List<Item> allitems = new ArrayList<>();
    Character drlucky = new DrLucky("Dr Lucky", 50, new Pet("Sloppy"));

    new MansionWorld("House", -50, 50, 2, 2, allitems, allspaces, drlucky, 10);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testZeroWidth() {

    List<Space> allspaces = new ArrayList<>();
    List<Item> allitems = new ArrayList<>();
    Character drlucky = new DrLucky("Dr Lucky", 50, new Pet("Sloppy"));

    new MansionWorld("House", 50, 0, 2, 2, allitems, allspaces, drlucky, 10);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeWidth() {

    List<Space> allspaces = new ArrayList<>();
    List<Item> allitems = new ArrayList<>();
    Character drlucky = new DrLucky("Dr Lucky", 50, new Pet("Sloppy"));

    new MansionWorld("House", 50, -50, 2, 2, allitems, allspaces, drlucky, 10);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullName() {

    List<Space> allspaces = new ArrayList<>();
    List<Item> allitems = new ArrayList<>();
    Character drlucky = new DrLucky("Dr Lucky", 50, new Pet("Sloppy"));

    new MansionWorld(null, 50, 50, 2, 2, allitems, allspaces, drlucky, 10);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullSpaceList() {

    List<Item> allitems = new ArrayList<>();
    Character drlucky = new DrLucky("Dr Lucky", 50, new Pet("Sloppy"));

    new MansionWorld("House", 50, 50, 2, 2, allitems, null, drlucky, 10);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullItemsList() {

    List<Space> allspaces = new ArrayList<>();
    Character drlucky = new DrLucky("Dr Lucky", 50, new Pet("Sloppy"));

    new MansionWorld("House", 50, 50, 2, 2, null, allspaces, drlucky, 10);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullCharacter() {

    List<Space> allspaces = new ArrayList<>();
    List<Item> allitems = new ArrayList<>();

    new MansionWorld("House", 50, 50, 2, 2, allitems, allspaces, null, 10);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeSpaceCount() {

    List<Space> allspaces = new ArrayList<>();
    List<Item> allitems = new ArrayList<>();
    Character drlucky = new DrLucky("Dr Lucky", 50, new Pet("Sloppy"));

    new MansionWorld("House", 50, 50, -2, 2, allitems, allspaces, drlucky, 10);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeItemsCount() {

    List<Space> allspaces = new ArrayList<>();
    List<Item> allitems = new ArrayList<>();
    Character drlucky = new DrLucky("Dr Lucky", 50, new Pet("Sloppy"));

    new MansionWorld("House", 50, 50, 2, -2, allitems, allspaces, drlucky, 10);

  }

  @Test()
  public void testCreatePlayer() {

    testworld = createTestWorld(0);

    // Initially the current player value will be non-exisitng
    assertEquals(testworld.getPlayerInfo(), "Current Player: --");

    String result = testworld.createPlayer("John", "Kitchen", 5, false);

    assertEquals("Player John was created succesfully\n", result);

    // The resulting player information should contain the name of the player
    assertTrue(testworld.getPlayerInfo().contains("John"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateNoNamePlayer() {

    testworld = createTestWorld(0);

    testworld.createPlayer(null, "Kitchen", 5, false);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateNoSpacePlayer() {

    testworld = createTestWorld(0);

    testworld.createPlayer("John", null, 5, false);

  }

  @Test()
  public void testCreatePlayerWrongSpace() {

    testworld = createTestWorld(0);

    String result = testworld.createPlayer("John", "Restroom", 5, false);

    assertEquals("Space Restroom was not found\n", result);

  }

  @Test()
  public void testCreateBotFirst() {

    testworld = createTestWorld(0);

    String result = testworld.createPlayer("Bot", "Kitchen", 5, true);

    assertEquals("Please create a human player first\n", result);

  }

  @Test()
  public void testCreateBotSecond() {

    testworld = createTestWorld(0);

    String result = testworld.createPlayer("John", "Kitchen", 5, false);

    assertEquals("Player John was created succesfully\n", result);

    result = testworld.createPlayer("Bot", "Kitchen", 5, true);

    assertEquals("Player Bot was created succesfully\n", result);

  }

  @Test()
  public void testGetSpaceInfo() {

    testworld = createTestWorld(0);

    // Testing if a player will be seen in the space after putting it
    testworld.createPlayer("John", "Kitchen", 5, false);
    String result = testworld.displaySpaceInformation();
    String expected = "You are in the Kitchen\nItems Present in this room: Knife, Pan, "
        + "\nSpaces seen from this room: Garage, \nPeople in this room: John, \n"
        + "Target Character at: Garage\n";

    assertEquals(expected, result);

  }

  @Test
  public void testGetCurrentPlayer() {

    testworld = createTestWorld(0);

    // Initially when no players are created
    assertEquals("--", testworld.getCurrentPlayer());

    // Seeing if the first player in the list is set as the current character
    testworld.createPlayer("John", "Kitchen", 5, false);

    assertEquals("John", testworld.getCurrentPlayer());
  }

  @Test
  public void testGetPlayerInfo() {

    testworld = createTestWorld(0);

    // Initially when no players are created
    assertEquals(testworld.getPlayerInfo(), "Current Player: --");

    testworld.createPlayer("John", "Kitchen", 5, false);

    // Creating a human player
    String result = testworld.getPlayerInfo();
    String expected = "Player Name: John\nPlayer Inventory: EMPTY\n\nRoom Name : Kitchen\n\n";

    assertEquals(expected, result);

    testworld.pickItem("Knife");

    result = testworld.getPlayerInfo();
    expected = "Player Name: John\nPlayer Inventory: Knife \nRoom Name : Kitchen\n\n";

    assertEquals(expected, result);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPickItem() {

    testworld = createTestWorld(2);

    testworld.createPlayer("John", "Garage", 1, false);

    testworld.pickItem(null);
  }

  @Test
  public void testPickItem() {

    testworld = createTestWorld(0);

    // Initially when no players are created
    assertEquals("Please create a player first\n", testworld.pickItem("Knife"));

    testworld.createPlayer("John", "Kitchen", 1, false);

    // Make sure the current player is the first created player
    assertEquals("John", testworld.getCurrentPlayer());

    testworld.createPlayer("Mary", "Garage", 2, false);
    String result = testworld.pickItem("Knife");
    String expected = "Item " + "Knife" + " has been added to your inventory\n";

    assertEquals(expected, result);
    // Make sure the turn changes after picking an item
    assertEquals("Mary", testworld.getCurrentPlayer());

    testworld.pickItem("Bat");

    // Picking up more than inventory size
    result = testworld.pickItem("Pan");
    expected = "Inventory Full!\n";

    assertEquals(expected, result);

    // Picking up non-existent item.
    // This line also tests if item is removed from space after picking up
    result = testworld.pickItem("Knife");
    expected = "Item " + "Knife" + " was not found in this space\n";

    assertEquals(expected, result);
  }

  @Test
  public void testBotPickItem() {

    testworld = createTestWorld(1);

    testworld.createPlayer("John", "Kitchen", 1, false);
    testworld.createPlayer("Bot1", "Garage", 1, true);

    String result = testworld.pickItem("Knife");
    String expected = "Item Knife has been added to your inventory\n"
        + "Bot1 Decides to pick item: Bat\n";

    assertEquals(expected, result);

  }

  @Test
  public void testBotMove() {

    testworld = createTestWorld(0);

    testworld.createPlayer("John", "Garage", 1, false);
    testworld.createPlayer("Bot1", "Courtyard", 1, true);

    String result = testworld.movePlayer("Bedroom");
    String expected = "Player has moved into Bedroom\nBot1 Decides to move to Gate House\n";

    assertEquals(expected, result);
  }

  @Test
  public void testHumanMove() {

    testworld = createTestWorld(0);

    // Initially when no players are created
    assertEquals("Please create a player first\n", testworld.movePlayer("John"));

    testworld.createPlayer("John", "Garage", 1, false);

    // Make sure the current player is the first created player
    assertEquals("John", testworld.getCurrentPlayer());

    testworld.createPlayer("Mary", "Courtyard", 2, false);

    String result = testworld.movePlayer("Bedroom");
    String expected = "Player has moved into Bedroom\n";

    assertEquals(expected, result);

    // Make sure the turn changes after picking an item
    assertEquals("Mary", testworld.getCurrentPlayer());

  }

  @Test
  public void testHumanWrongMove() {

    testworld = createTestWorld(0);

    testworld.createPlayer("John", "Garage", 1, false);

    String result = testworld.movePlayer("Courtyard");
    String expected = "You cannot move into a non-neighboring space \n";

    assertEquals(expected, result);

    // Ensuring that the player is still in the same space
    result = testworld.getPlayerInfo();
    expected = "Player Name: John\nPlayer Inventory: EMPTY\n\nRoom Name : Garage\n\n";

    assertEquals(expected, result);
  }

  @Test
  public void testLookAround() {

    testworld = createTestWorld(0);

    // Initially when no players are created
    assertEquals("Please create a player first\n", testworld.lookAround());

    testworld.createPlayer("John", "Garage", 1, false);
    testworld.createPlayer("Mary", "Courtyard", 1, false);
    testworld.createPlayer("Merlin", "Bedroom", 1, false);

    String result = testworld.lookAround();
    String expected = "Current Space Name: Garage\nItems Present in this room:"
        + " Bat, \nPeople in this room: Dr Lucky, Sloppy, John, \n\nNeighboring rooms:\nSpace Name:"
        + " Kitchen\nItems Present in this room: Knife, Pan, \nPeople in this room: None\n\nSpace "
        + "Name: Bedroom\nItems Present in this room: None\nPeople in this room: Merlin, \n\n";

    assertEquals(expected, result);

    result = testworld.lookAround();
    expected = "Current Space Name: Courtyard\nItems Present in this room: None\nPeople in this "
        + "room: Mary, \n\nNeighboring rooms:\nSpace Name: Gate House\nItems Present in this "
        + "room: None\nPeople in this room: None\n\n";

    assertEquals(expected, result);

    testworld.movePlayer("Garage");

    result = testworld.lookAround();
    expected = "Current Space Name: Garage\nItems Present in this room:"
        + " Bat, \nPeople in this room: Sloppy, John, Merlin, \n\nNeighboring rooms:\nSpace Name:"
        + " Kitchen\nItems Present in this room: Knife, Pan, \nPeople in this room: None\n\nSpace "
        + "Name: Bedroom\nItems Present in this room: None\nPeople in this room: Dr Lucky, \n\n";

    assertEquals(expected, result);

    testworld.movePet("Gate House");

  }

  @Test
  public void testBotLookAround() {

    testworld = createTestWorld(2);

    testworld.createPlayer("John", "Kitchen", 1, false);
    testworld.createPlayer("Bot1", "Bedroom", 1, true);

    String result = testworld.pickItem("Knife");
    String expected = "Item " + "Knife" + " has been added to your inventory\n" + "Bot1"
        + " Decides to look Around..." + "\n";

    assertEquals(expected, result);

  }

  @Test
  public void testGameOver() {

    testworld = createTestWorld(2);

    assertEquals(false, testworld.isGameOver());

    // The case when Doctor Lucky Escapes out alive
    testworld.createPlayer("John", "Kitchen", 1, false);
    testworld.createPlayer("Bot1", "Bedroom", 1, true);
    testworld.createPlayer("Bot2", "Bedroom", 1, true);
    testworld.createPlayer("Bot3", "Bedroom", 1, true);

    String result = testworld.pickItem("Knife");
    String expected = "Item " + "Knife" + " has been added to your inventory\n" + "Bot1"
        + " Decides to look Around..." + "\n" + "Bot2" + " Decides to look Around..." + "\n"
        + "Bot3" + " Decides to look Around..." + "\n";

    assertEquals(expected, result);

    assertEquals(true, testworld.isGameOver());

  }

  @Test
  public void testHumanKills() {

    testworld = createTestWorld(3);

    assertEquals(false, testworld.isGameOver());

    testworld.createPlayer("John", "Garage", 1, false);

    String result = testworld.attackTarget("Finger");
    String expected = "ATTEMPT SUCCESS: Doctor Lucky has been hurt\n"
        + "Doctor Lucky's health has dropped from 1 to 0\n"
        + "Player: John has killed Doctor Lucky!\n";

    assertEquals(expected, result);

    assertEquals(true, testworld.isGameOver());
    assertEquals("John", testworld.getWinner());

  }

  @Test
  public void testBotKills() {

    testworld = createTestWorld(3);

    assertEquals(false, testworld.isGameOver());

    testworld.createPlayer("John", "Courtyard", 1, false);
    testworld.createPlayer("Killer", "Kitchen", 1, true);

    // Attacking target character with a finger
    String result = testworld.movePet("Courtyard");
    String expected = "You have moved the pet into Courtyard\n"
        + "Killer Decides to attack the target by poking the eye\n"
        + "Doctor Lucky's health has dropped from 1 to 0\n"
        + "Player: Killer has killed Doctor Lucky!\n";

    assertEquals(expected, result);

    assertEquals(true, testworld.isGameOver());
    assertEquals("Killer", testworld.getWinner());

  }

  @Test
  public void testAttackTurnChange() {

    testworld = createTestWorld(2);
    testworld.createPlayer("John", "Kitchen", 1, false);
    testworld.createPlayer("Mary", "Kitchen", 1, false);

    assertEquals("John", testworld.getCurrentPlayer());

    testworld.pickItem("Knife");

    assertEquals("Mary", testworld.getCurrentPlayer());

    testworld.attackTarget("Finger");

    assertEquals("John", testworld.getCurrentPlayer());

  }

  @Test
  public void testHumanAttack() {

    testworld = createTestWorld(2);
    testworld.createPlayer("John", "Kitchen", 1, false);

    testworld.pickItem("Knife");

    // The player description will now have Knife under it's inventory list
    assertTrue(testworld.getPlayerInfo().contains("Knife"));

    // Attacking target character with a weapon
    String result = testworld.attackTarget("Knife");
    String expected = "ATTEMPT SUCCESS: Doctor Lucky has been hurt\n"
        + "Doctor Lucky's health has dropped from 50 to 40\n"
        + "Item Knife has removed from inventory and added to evidences\n";

    assertEquals(expected, result);

    // The player description should now not have Knife in it's inventory since it's
    // in evidence
    assertFalse(testworld.getPlayerInfo().contains("Knife"));

    // See if the item is removed from the space as well
    result = testworld.pickItem("Knife");
    expected = "Item " + "Knife" + " was not found in this space\n";

    assertEquals(expected, result);

    testworld.movePlayer("Garage");
    // Waiting for DrLucky to enter Garage
    testworld.lookAround();
    testworld.lookAround();

    // Attacking target character by poking the eye
    result = testworld.attackTarget("finger");
    expected = "ATTEMPT SUCCESS: Doctor Lucky has been hurt\n"
        + "Doctor Lucky's health has dropped from 40 to 39\n";

    assertEquals(expected, result);
  }

  @Test
  public void testBotAttack() {

    testworld = createTestWorld(1);
    testworld.createPlayer("John", "Courtyard", 1, false);
    testworld.createPlayer("Killer", "Kitchen", 1, true);

    // Attacking target character with a finger
    String result = testworld.movePet("Courtyard");
    String expected = "You have moved the pet into Courtyard\n"
        + "Killer Decides to attack the target by poking the eye\n"
        + "Doctor Lucky's health has dropped from 50 to 49\n";

    testworld.movePet("Courtyard");

    // Attacking target character with a weapon
    result = testworld.movePet("Courtyard");
    expected = "You have moved the pet into Courtyard\n"
        + "Killer Decides to attack the target using Knife\n"
        + "Item Knife has removed from inventory and added to evidences\n"
        + "Doctor Lucky's health has dropped from 49 to 39\n";

    assertEquals(expected, result);
  }

  @Test
  public void testNeighborSeenAttacks() {

    testworld = createTestWorld(2);

    testworld.createPlayer("John", "Garage", 1, false);
    testworld.createPlayer("Killer", "Bedroom", 1, true);

    testworld.movePet("Courtyard");
    testworld.movePet("Courtyard");

    // Attacking target character with a weapon when there is a player in the next
    // room
    String result = testworld.attackTarget("finger");
    String expected = "ATTEMPT FAILED: Seen by a player in the next room!\n";

    assertEquals(expected, result);

    testworld.movePet("Bedroom");
    testworld.movePet("Bedroom");

    result = testworld.attackTarget("finger");
    expected = "ATTEMPT FAILED: Seen by a player in the next room!\n"
        + "Killer Decides to look Around...\n";

    assertEquals(expected, result);

  }

  @Test
  public void testSameRoomSeenAttacks() {

    testworld = createTestWorld(2);

    testworld.createPlayer("John", "Garage", 1, false);
    testworld.createPlayer("Killer", "Garage", 1, true);

    // Attacking target character with a weapon when there is a player in the same
    // room
    String result = testworld.attackTarget("finger");
    String expected = "ATTEMPT FAILED: Seen by other player in the room!\n"
        + "Killer Decides to look Around...\n";

    assertEquals(expected, result);

  }

  @Test
  public void testAttackDifferentRoom() {

    testworld = createTestWorld(2);

    testworld.createPlayer("John", "Courtyard", 1, false);

    // Attacking target character not bring in the same room
    String result = testworld.attackTarget("finger");
    String expected = "ATTEMPT FAILED: Character not in this room!\n";

    assertEquals(expected, result);

  }

  @Test
  public void testAttackNoWeapon() {

    testworld = createTestWorld(2);

    testworld.createPlayer("John", "Garage", 1, false);

    // Attacking target character with a weapon when you don't have the weapon
    String result = testworld.attackTarget("Knife");
    String expected = "You do not have the item: Knife\n" + "Using fingers to poke eye instead\n"
        + "ATTEMPT SUCCESS: Doctor Lucky has been hurt\n"
        + "Doctor Lucky's health has dropped from 50 to 49\n";

    assertEquals(expected, result);

  }

  @Test
  public void testMovePet() {

    testworld = createTestWorld(2);

    testworld.createPlayer("John", "Garage", 1, false);

    String result = testworld.movePet("Bedroom");
    String expected = "You have moved the pet into Bedroom\n";

    assertEquals(expected, result);

    // For a non-existing room in the world, an appropriate message is shown
    result = testworld.movePet("Bathroom");
    expected = "The space Bathroom to move the pet into was not found in the mansion\n";

    assertEquals(expected, result);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMovePet() {

    testworld = createTestWorld(2);

    testworld.createPlayer("John", "Garage", 1, false);

    testworld.movePet(null);
  }

  @Test
  public void testPetStartingPosition() {

    testworld = createTestWorld(2);

    testworld.createPlayer("John", "Garage", 1, false);

    // Doctor Lucky and his pet, Sloppy should be in the same room at start
    // Garage is the starting room in this test world
    String result = testworld.lookAround();

    assertTrue(result.contains("Garage"));
    assertTrue(result.contains("Dr Lucky"));
    assertTrue(result.contains("Sloppy"));

  }

  @Test
  public void testPetHidingRoom() {

    testworld = createTestWorld(2);

    testworld.createPlayer("John", "Kitchen", 1, false);

    // Looking around, the next room in which the pet is there (Garage) shouldn't be
    // seen
    String result = testworld.lookAround();
    String expected = "Current Space Name: Kitchen\n" + "Items Present in this room: Knife, Pan, \n"
        + "People in this room: John, \n\n" + "Neighboring rooms:\n";

    assertEquals(expected, result);

  }

  @Test
  public void testPetHidingAttacks() {

    testworld = createTestWorld(2);

    testworld.createPlayer("John", "Garage", 1, false);
    testworld.createPlayer("Observer", "Kitchen", 1, false);

    // Since the pet is in the same room, it should be invisible to the second
    // player
    // Allowing a successful attack
    String result = testworld.attackTarget("Finger");
    String expected = "ATTEMPT SUCCESS: Doctor Lucky has been hurt\n"
        + "Doctor Lucky's health has dropped from 50 to 49\n";

    assertEquals(expected, result);

  }

  @Test
  public void testDisplayInformation() {

    testworld = createTestWorld(2);

    testworld.createPlayer("John", "Garage", 1, false);

    // We check the function which displays the information about the current player
    String result = testworld.displaySpaceInformation();
    String expected = "You are in the Garage\n" + "Items Present in this room: Bat, \n"
        + "Spaces seen from this room: Kitchen, Bedroom, \n"
        + "People in this room: Dr Lucky, Sloppy, John, \n" + "Target Character at: Garage\n";

    assertEquals(expected, result);

  }

  @Test
  public void testGetSpace() {

    assertEquals("Kitchen", testworld.getSpace("Kitchen").getSpaceName());

    assertEquals(null, testworld.getSpace("Bathroom"));

  }

  @Test
  public void testToString() {
    assertEquals("World House of length: 50 and width: 50", testworld.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSpaceFromCoordinatesNull() {
    testworld = createTestWorld(2);
    testworld.getSpaceFromCoordinates(null);
  }

  @Test
  public void testSpaceFromCoordinates() {

    testworld = createTestWorld(2);
    String result = testworld.getSpaceFromCoordinates(new Coordinates2D(1, 1));

    assertEquals("Kitchen", result);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPlayersInSpaceNull() {
    testworld = createTestWorld(2);
    testworld.getPlayersInSpace(null);
  }

  @Test
  public void testGetPlayersInSpace() {

    testworld = createTestWorld(2);
    testworld.createPlayer("John", "Courtyard", 1, false);
    testworld.createPlayer("Killer", "Kitchen", 1, true);

    List<String> result = testworld.getPlayersInSpace(testworld.getSpace("Kitchen"));

    String expected = "Killer";

    assertEquals(1, result.size());
    assertEquals(expected, result.get(0));

    result = testworld.getPlayersInSpace(testworld.getSpace("Courtyard"));

    expected = "John";

    assertEquals(1, result.size());
    assertEquals(expected, result.get(0));

  }

  @Test
  public void testGetAllSpaces() {

    testworld = createTestWorld(2);

    List<Space> actualspaces = testworld.getAllSpaces();
    List<Space> expectedspaces = new ArrayList<>();
    expectedspaces.add(testworld.getSpace("Garage"));
    expectedspaces.add(testworld.getSpace("Kitchen"));
    expectedspaces.add(testworld.getSpace("Courtyard"));
    expectedspaces.add(testworld.getSpace("Bedroom"));
    expectedspaces.add(testworld.getSpace("Gate House"));

    for (int i = 0; i < expectedspaces.size(); i++) {
      assertEquals(expectedspaces.get(i), actualspaces.get(i));
    }

  }

  @Test
  public void testGetAllSpaceItems() {

    testworld = createTestWorld(2);
    testworld.createPlayer("John", "Kitchen", 1, false);

    List<String> actualitems = testworld.getAllSpaceItems();
    List<String> expecteditems = new ArrayList<>();
    expecteditems.add("Knife");
    expecteditems.add("Pan");

    for (int i = 0; i < expecteditems.size(); i++) {
      assertEquals(expecteditems.get(i), actualitems.get(i));
    }

  }

  @Test
  public void testGetAllPlayerItems() {

    testworld = createTestWorld(2);
    testworld.createPlayer("John", "Kitchen", 1, false);

    List<String> actualitems = testworld.getAllPlayerItems();
    List<String> expecteditems = new ArrayList<>();
    expecteditems.add("finger");

    for (int i = 0; i < expecteditems.size(); i++) {
      assertEquals(expecteditems.get(i), actualitems.get(i));
    }

    testworld.pickItem("Knife");

    actualitems = testworld.getAllPlayerItems();
    expecteditems = new ArrayList<>();
    expecteditems.add("Knife");
    expecteditems.add("finger");

    for (int i = 0; i < expecteditems.size(); i++) {
      assertEquals(expecteditems.get(i), actualitems.get(i));
    }

  }

  @Test
  public void testGetAllSpaceNames() {

    testworld = createTestWorld(2);

    List<String> actualspacename = testworld.getAllSpaceNames();
    List<String> expectedspacenames = new ArrayList<>();
    expectedspacenames.add("Garage");
    expectedspacenames.add("Kitchen");
    expectedspacenames.add("Courtyard");
    expectedspacenames.add("Bedroom");
    expectedspacenames.add("Gate House");

    for (int i = 0; i < expectedspacenames.size(); i++) {
      assertEquals(expectedspacenames.get(i), actualspacename.get(i));
    }

  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullResetWorld() {

    testworld = createTestWorld(2);
    testworld.resetWorld(null);

  }

  @Test
  public void testResetWorld() {

    testworld = createTestWorld(2);
    List<String> actualspacename = testworld.getAllSpaceNames();
    List<String> expectedspacenames = new ArrayList<>();
    expectedspacenames.add("Garage");
    expectedspacenames.add("Kitchen");
    expectedspacenames.add("Courtyard");
    expectedspacenames.add("Bedroom");
    expectedspacenames.add("Gate House");

    testworld.createPlayer("John", "Courtyard", 1, false);
    testworld.createPlayer("Killer", "Kitchen", 1, true);

    List<String> result = testworld.getPlayersInSpace(testworld.getSpace("Kitchen"));

    String expected = "Killer";

    assertEquals(1, result.size());
    assertEquals(expected, result.get(0));

    for (int i = 0; i < expectedspacenames.size(); i++) {
      assertEquals(expectedspacenames.get(i), actualspacename.get(i));
    }

    testworld.resetWorld(new StringReader(".\\src\\assets\\mansiontest.txt"));

    actualspacename = testworld.getAllSpaceNames();
    expectedspacenames = new ArrayList<>();
    expectedspacenames.add("Armory");
    expectedspacenames.add("Billiard Room");
    expectedspacenames.add("Carriage House");

    for (int i = 0; i < expectedspacenames.size(); i++) {
      assertEquals(expectedspacenames.get(i), actualspacename.get(i));
    }

    // The new world will not have any players in this space and all other players
    // are reset
    result = testworld.getPlayersInSpace(testworld.getSpace("Billiard Room"));
    assertEquals(0, result.size());

  }

  private World createTestWorld(int type) {

    Item weapon = new Weapon(0, "Knife", 10, 1);
    Item weapon0 = new Weapon(1, "Bat", 10, 0);

    Coordinates upper0 = new Coordinates2D(1, 3);
    Coordinates lower0 = new Coordinates2D(4, 4);

    List<Item> items0 = new ArrayList<>();
    items0.add(weapon0);

    Space space0 = new MansionSpace(0, "Garage", upper0, lower0, new ArrayList<Item>(),
        new ArrayList<Space>());

    List<Item> items = new ArrayList<>();
    items.add(weapon);
    List<Space> neighbors = new ArrayList<>();
    neighbors.add(space0);

    Coordinates upper = new Coordinates2D(0, 0);
    Coordinates lower = new Coordinates2D(2, 2);

    Space space = new MansionSpace(1, "Kitchen", upper, lower, new ArrayList<Item>(),
        new ArrayList<Space>());

    Coordinates upper3 = new Coordinates2D(20, 25);
    Coordinates lower3 = new Coordinates2D(26, 29);

    Space space3 = new MansionSpace(2, "Courtyard", upper3, lower3, new ArrayList<Item>(),
        new ArrayList<Space>());

    Coordinates upper4 = new Coordinates2D(0, 5);
    Coordinates lower4 = new Coordinates2D(5, 5);

    Space space4 = new MansionSpace(3, "Bedroom", upper4, lower4, new ArrayList<Item>(),
        new ArrayList<Space>());

    Coordinates upper5 = new Coordinates2D(20, 30);
    Coordinates lower5 = new Coordinates2D(26, 30);

    Space space5 = new MansionSpace(4, "Gate House", upper5, lower5, new ArrayList<Item>(),
        new ArrayList<Space>());

    List<Space> allspaces = new ArrayList<>();
    allspaces.add(space0);
    allspaces.add(space);
    allspaces.add(space3);
    allspaces.add(space4);
    allspaces.add(space5);

    Item weapon2 = new Weapon(2, "Pan", 10, 1);
    List<Item> allitems = new ArrayList<>();
    allitems.add(weapon0);
    allitems.add(weapon);
    allitems.add(weapon2);

    Character drlucky = new DrLucky("Dr Lucky", 50, new Pet("Sloppy"));

    if (type == 0) {
      // Computer player which chooses to move and choose the first neighbor
      return new MansionWorld("House", 50, 50, 4, 3, allitems, allspaces, drlucky, 10, 0, 0);
    } else if (type == 1) {
      // Computer player which chooses to pick the first item in the room if present
      return new MansionWorld("House", 50, 50, 4, 3, allitems, allspaces, drlucky, 10, 1, 0);
    } else if (type == 2) {
      // Computer player which chooses to always look around
      return new MansionWorld("House", 50, 50, 4, 3, allitems, allspaces, drlucky, 4, 2);
    } else {
      // Computer player which chooses to always look around and Dr lucky with health
      // 1
      drlucky = new DrLucky("Dr Lucky", 1, new Pet("Sloppy"));
      return new MansionWorld("House", 50, 50, 4, 3, allitems, allspaces, drlucky, 4, 2);
    }

  }

}
