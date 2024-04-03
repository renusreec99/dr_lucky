import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import game.Controller;
import game.Coordinates;
import game.Coordinates2D;
import game.Item;
import game.MansionSpace;
import game.Space;
import game.View;
import game.World;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the Controller class.
 *
 */
public class ControllerTest {
  private World mockworld;
  private View mockview;
  private World mockerror;
  private World mockworld2;
  private World mockworld3;
  private StringBuilder modellog;
  private StringBuilder viewlog;

  /**
   * Setting up the values for the tests.
   */
  @Before
  public void setup() {
    modellog = new StringBuilder();
    viewlog = new StringBuilder();

    List<String> uniquestringlist = new ArrayList<>();
    uniquestringlist.add("roguelistval1");
    uniquestringlist.add("roguelistval2");
    uniquestringlist.add("true");

    List<String> uniquestringlist2 = new ArrayList<>();
    uniquestringlist2.add("roguelistval1");
    uniquestringlist2.add("roguelistval2");
    uniquestringlist2.add("false");
    
    String uniqueString = "roguecontroller";
    
    Coordinates upper = new Coordinates2D(0, 0);
    Coordinates lower = new Coordinates2D(2, 2);
    Space uniquespace = new MansionSpace(0, "rogueroom", upper, lower, new ArrayList<Item>(),
        new ArrayList<Space>());

    boolean uniquebool = false;
    boolean uniquebool2 = true;
    String uniqueEmptyString = "";
    List<Space> uniquespacelist = new ArrayList<>();
    uniquespacelist.add(uniquespace);

    mockworld = new MockWorld(modellog, uniqueString, uniquespace, uniquebool, uniquestringlist,
        uniquespacelist);
    mockerror = new MockErrorModel(modellog);
    mockview = new MockView(viewlog, uniqueString, uniquestringlist);

    mockworld2 = new MockWorld(modellog, uniqueString, uniquespace, uniquebool2, uniquestringlist2,
        uniquespacelist);

    mockworld3 = new MockWorld(modellog, uniqueEmptyString, uniquespace, uniquebool2,
        uniquestringlist, uniquespacelist);
  }

  @Test
  public void testController() {
    new Controller(mockview, mockworld);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testControllerNullWorld() {

    StringBuilder viewlog = new StringBuilder();
    String uniqueString = "roguecontroller";
    Coordinates upper = new Coordinates2D(0, 0);
    Coordinates lower = new Coordinates2D(2, 2);
    Space uniquespace = new MansionSpace(0, "rogueroom", upper, lower, new ArrayList<Item>(),
        new ArrayList<Space>());

    List<String> uniquestringlist = new ArrayList<>();
    uniquestringlist.add("roguelistval1");
    List<Space> uniquespacelist = new ArrayList<>();
    uniquespacelist.add(uniquespace);

    View mockview = new MockView(viewlog, uniqueString, uniquestringlist);

    new Controller(mockview, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testControllerNullView() {

    StringBuilder viewlog = new StringBuilder();
    String uniqueString = "roguecontroller";
    List<String> uniquestringlist = new ArrayList<>();
    uniquestringlist.add("roguelistval1");

    View mockview = new MockView(viewlog, uniqueString, uniquestringlist);

    new Controller(mockview, null);
  }

  @Test
  public void testStartGame() {
    Controller testcontroller = new Controller(mockview, mockworld);

    String expectedviewlog = "Show View called\n"
        + "Get Set Feature called with Game Controller\n"
        + "Refresh called\n";
    testcontroller.startGame();

    assertEquals(expectedviewlog, viewlog.toString());

    String expectedmodellog = "";

    assertEquals(expectedmodellog, modellog.toString());
  }

  @Test
  public void testMovePlayer() {
    Controller testcontroller = new Controller(mockview, mockworld);

    String expectedviewlog = "Show message called with : roguecontroller\n" + "Refresh called\n"
        + "Show current player details called with : roguecontroller\n" + "Refresh called\n";
    testcontroller.movePlayer(1, 1);

    assertEquals(expectedviewlog, viewlog.toString());

    String expectedmodellog = "Get Space from Coordinates called with: (1, 1)\n"
        + "Move Player Input: roguecontroller\n" + "Is Game Over Called\n"
        + "Get Player Info called\n";

    assertEquals(expectedmodellog, modellog.toString());
  }

  @Test
  public void testMovePlayerGameOverWin() {
    Controller testcontroller = new Controller(mockview, mockworld2);

    String expectedviewlog = "Show message called with : roguecontroller\n" + "Refresh called\n"
        + "Show Game End called with roguecontroller\n" + "Exit game called\n";
    testcontroller.movePlayer(1, 1);

    assertEquals(expectedviewlog, viewlog.toString());

    String expectedmodellog = "Get Space from Coordinates called with: (1, 1)\n"
        + "Move Player Input: roguecontroller\n" + "Is Game Over Called\n"
        + "Game Over Status called\n";

    assertEquals(expectedmodellog, modellog.toString());
  }

  @Test
  public void testMovePlayerGameOverLose() {
    Controller testcontroller = new Controller(mockview, mockworld3);

    String expectedviewlog = "";
    testcontroller.movePlayer(1, 1);

    assertEquals(expectedviewlog, viewlog.toString());

    String expectedmodellog = "Get Space from Coordinates called with: (1, 1)\n";

    assertEquals(expectedmodellog, modellog.toString());
  }

  @Test
  public void testMoveWrongCoordinates() {
    Controller testcontroller = new Controller(mockview, mockworld3);

    String expectedviewlog = "Show Error Message Called\n" + "Refresh called\n";
    testcontroller.movePlayer(-1, -1);

    assertEquals(expectedviewlog, viewlog.toString());

    String expectedmodellog = "";

    assertEquals(expectedmodellog, modellog.toString());
  }

  @Test
  public void testLoadNewWorld() {
    Controller testcontroller = new Controller(mockview, mockworld);

    String expectedviewlog = "Get File called with\n" + "Show New View called\n"
        + "Refresh called\n";
    testcontroller.loadNewWorld();

    assertEquals(expectedviewlog, viewlog.toString());

    String expectedmodellog = "Reset World called with java.io.StringReader";

    assertTrue(modellog.toString().contains(expectedmodellog));
  }

  @Test
  public void testLoadNewWorldError() {
    Controller testcontroller = new Controller(mockview, mockerror);

    String expectedviewlog = "Get File called with\n" + "Show Error Message Called\n"
        + "Refresh called\n";
    testcontroller.loadNewWorld();

    assertEquals(expectedviewlog, viewlog.toString());

    String expectedmodellog = "";

    assertEquals(expectedmodellog, modellog.toString());
  }

  @Test
  public void testMovePet() {
    Controller testcontroller = new Controller(mockview, mockworld);

    String expectedviewlog = "Show Drop Down called with roguelistval1 roguelistval2 true \n"
        + "Show message called with : roguecontroller\n" + "Refresh called\n"
        + "Show current player details called with : roguecontroller\n" + "Refresh called\n";
    testcontroller.movePet();

    assertEquals(expectedviewlog, viewlog.toString());

    String expectedmodellog = "Get All Space called\n" + "Move Pet called with: roguecontroller\n"
        + "Is Game Over Called\n" + "Get Player Info called\n";

    assertEquals(expectedmodellog, modellog.toString());

  }

  // TODO change the null value
  @Test
  public void testMovePetError() {
    Controller testcontroller = new Controller(mockview, mockerror);

    String expectedviewlog = "Show Drop Down called with \n"
        + "Show message called with : Invalid Space name to move pet\n" + "Refresh called\n"
        + "Show current player details called with : No Player Information to show\n"
        + "Refresh called\n";
    testcontroller.movePet();

    assertEquals(expectedviewlog, viewlog.toString());

    String expectedmodellog = "";

    assertEquals(expectedmodellog, modellog.toString());

  }

  @Test
  public void testAddHumanPlayer() {
    Controller testcontroller = new Controller(mockview, mockworld);

    String expectedviewlog = "Show Add Player Drop called with roguelistval1 roguelistval2 true \n"
        + "Show message called with : roguecontroller\n"
        + "Show current player details called with : roguecontroller\n" + "Refresh called\n";

    testcontroller.addPlayer();

    assertEquals(expectedviewlog, viewlog.toString());

    String expectedmodellog = "Get All Space called\n"
        + "Create Player called with roguelistval1 roguelistval2 true\n"
        + "Get Player Info called\n";

    assertEquals(expectedmodellog, modellog.toString());
  }

  @Test
  public void testAddComputerPlayer() {
    Controller testcontroller = new Controller(mockview, mockworld2);

    String expectedviewlog = "Show Add Player Drop called with roguelistval1 roguelistval2 false \n"
        + "Show message called with : roguecontroller\n"
        + "Show current player details called with : roguecontroller\n" + "Refresh called\n";

    testcontroller.addPlayer();

    assertEquals(expectedviewlog, viewlog.toString());

    String expectedmodellog = "Get All Space called\n"
        + "Create Player called with roguelistval1 roguelistval2 true\n"
        + "Get Player Info called\n";

    assertEquals(expectedmodellog, modellog.toString());

  }

  @Test
  public void testPickItem() {
    Controller testcontroller = new Controller(mockview, mockworld);

    String expectedviewlog = "Show Drop Down called with roguelistval1 roguelistval2 true \n"
        + "Show message called with : roguecontroller\n" + "Refresh called\n"
        + "Show current player details called with : roguecontroller\n" + "Refresh called\n";

    testcontroller.pickItem();

    assertEquals(expectedviewlog, viewlog.toString());

    String expectedmodellog = "Get All Space Items called\n"
        + "Pick Item called with roguecontroller\n" + "Is Game Over Called\n"
        + "Get Player Info called\n";

    assertEquals(expectedmodellog, modellog.toString());

  }

  @Test
  public void testPickItemError() {
    Controller testcontroller = new Controller(mockview, mockerror);

    String expectedviewlog = "Show Drop Down called with \n" + "Show Error Message Called\n"
        + "Refresh called\n"
        + "Show current player details called with : No Player Information to show\n"
        + "Refresh called\n";
    testcontroller.pickItem();

    assertEquals(expectedviewlog, viewlog.toString());

    String expectedmodellog = "";

    assertEquals(expectedmodellog, modellog.toString());

  }

  @Test
  public void testLookAround() {
    Controller testcontroller = new Controller(mockview, mockworld);

    String expectedviewlog = "Show message called with : roguecontroller\n" + "Refresh called\n"
        + "Show current player details called with : roguecontroller\n" + "Refresh called\n";

    testcontroller.lookAround();

    assertEquals(expectedviewlog, viewlog.toString());

    String expectedmodellog = "Look Around called\n" + "Is Game Over Called\n"
        + "Get Player Info called\n";

    assertEquals(expectedmodellog, modellog.toString());
  }

  @Test
  public void testAttack() {
    Controller testcontroller = new Controller(mockview, mockworld);

    String expectedviewlog = "Show Drop Down called with roguelistval1 roguelistval2 true \n"
        + "Show message called with : roguecontroller\n" + "Refresh called\n"
        + "Show current player details called with : roguecontroller\n" + "Refresh called\n";

    testcontroller.attack();

    assertEquals(expectedviewlog, viewlog.toString());

    String expectedmodellog = "Get All Player Items called\n"
        + "Attack Target with: roguecontroller\n" + "Is Game Over Called\n"
        + "Get Player Info called\n";

    assertEquals(expectedmodellog, modellog.toString());
  }

  @Test
  public void testExitProgram() {
    Controller testcontroller = new Controller(mockview, mockworld);

    String expectedviewlog = "Exit game called\n";

    testcontroller.exitProgram();

    assertEquals(expectedviewlog, viewlog.toString());

    String expectedmodellog = "";

    assertEquals(expectedmodellog, modellog.toString());
  }

  @Test
  public void testLoadWorld() {
    Controller testcontroller = new Controller(mockview, mockworld);

    String expectedviewlog = "Show New View called\n" + "Refresh called\n";

    testcontroller.loadWorld();

    assertEquals(expectedviewlog, viewlog.toString());

    String expectedmodellog = "";

    assertEquals(expectedmodellog, modellog.toString());
  }

  @Test
  public void testGetPlayerDetails() {
    Controller testcontroller = new Controller(mockview, mockworld);

    String expectedviewlog = "Show Selected Player Details called with roguecontroller\n";

    testcontroller.getPlayerDetails();

    assertEquals(expectedviewlog, viewlog.toString());

    String expectedmodellog = "Get Player Info called\n";

    assertEquals(expectedmodellog, modellog.toString());
  }

}
