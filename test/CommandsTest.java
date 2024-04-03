import commands.AddPlayer;
import commands.Attack;
import commands.LookAround;
import commands.MovePet;
import commands.Pick;
import commands.WorldCommands;
import game.Coordinates;
import game.Coordinates2D;
import game.Item;
import game.MansionSpace;
import game.Space;
import game.View;
import game.World;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;


/**
 * Test class for the Commands in the world.
 *
 */
public class CommandsTest {

  private World mockworld;
  private View mockview;
  private StringBuilder modellog;
  private StringBuilder viewlog;

  @Test
  public void testMove() {
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
    List<Space> uniquespacelist = new ArrayList<>();
    boolean uniquebool = false;
    String uniqueString = "roguecontroller";
    Coordinates upper = new Coordinates2D(0, 0);
    Coordinates lower = new Coordinates2D(2, 2);
    Space uniquespace = new MansionSpace(0, "rogueroom", upper, lower, new ArrayList<Item>(),
        new ArrayList<Space>());
    uniquespacelist.add(uniquespace);


    mockworld = new MockWorld(modellog, uniqueString, uniquespace, uniquebool, uniquestringlist,
        uniquespacelist);
    mockview = new MockView(viewlog, uniqueString, uniquestringlist);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testPickNullView() {
    WorldCommands cmd = new Pick();
    cmd.execute(mockworld, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPickNullModel() {
    WorldCommands cmd = new Pick();
    cmd.execute(null, mockview);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMovePetNullView() {
    WorldCommands cmd = new MovePet();
    cmd.execute(mockworld, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMovePetNullModel() {
    WorldCommands cmd = new MovePet();
    cmd.execute(null, mockview);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAttackNullView() {
    WorldCommands cmd = new Attack();
    cmd.execute(mockworld, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAttackNullModel() {
    WorldCommands cmd = new Attack();
    cmd.execute(null, mockview);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLookAroundNullView() {
    WorldCommands cmd = new LookAround();
    cmd.execute(mockworld, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLookAroundNullModel() {
    WorldCommands cmd = new LookAround();
    cmd.execute(null, mockview);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddPlayerNullView() {
    WorldCommands cmd = new AddPlayer();
    cmd.execute(mockworld, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddPlayerNullModel() {
    WorldCommands cmd = new AddPlayer();
    cmd.execute(null, mockview);
  }

}
