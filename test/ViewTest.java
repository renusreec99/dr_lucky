import game.Coordinates;
import game.Coordinates2D;
import game.Item;
import game.MansionSpace;
import game.Space;
import game.View;
import game.ViewImpl;
import game.World;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the View class.
 *
 */
public class ViewTest {

  private World mockerror;
  private StringBuilder modellog;
  private View testview;

  /**
   * Setting up the values for tests.
   */
  @Before
  public void setup() {
    modellog = new StringBuilder();
    Coordinates upper = new Coordinates2D(0, 0);
    Coordinates lower = new Coordinates2D(2, 2);
    Space uniquespace = new MansionSpace(0, "rogueroom", upper, 
        lower, new ArrayList<Item>(),
        new ArrayList<Space>());

    List<String> uniquestringlist = new ArrayList<>();
    uniquestringlist.add("roguelistval1");
    uniquestringlist.add("roguelistval2");
    uniquestringlist.add("true");

    List<String> uniquestringlist2 = new ArrayList<>();
    uniquestringlist2.add("roguelistval1");
    uniquestringlist2.add("roguelistval2");
    uniquestringlist2.add("false");
    List<Space> uniquespacelist = new ArrayList<>();
    uniquespacelist.add(uniquespace);

    mockerror = new MockErrorModel(modellog);
    testview = new ViewImpl(mockerror);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetFeatures() {
    testview.setFeatures(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShowMessage() {
    testview.showMessage(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShowCurrentPlayerDetails() {
    testview.showCurrentPlayerDetails(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShowDropDown() {
    testview.showDropDown(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddPlayerDrop() {
    testview.addPlayerDrop(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShowErrorMessage() {
    testview.showErrorMessage(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShowSelectedPlayerDetails() {
    testview.showSelectedPlayerDetails(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShowGameEnd() {
    testview.showGameEnd(null);
  }

}
