import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import game.Coordinates;
import game.Coordinates2D;
import game.Item;
import game.MansionSpace;
import game.Space;
import game.Weapon;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Class for testing the MansionSpace class.
 *
 */
public class MansionSpaceTest {
  
  private Space space;

  /**
   * Setup the space object for every test.
   */
  @Before
  public void setUp() {
    
    Item weapon = new Weapon(0, "Knife", 10, 2);
    Item weapon0 = new Weapon(1, "Bat", 10, 2);
    
    Coordinates upper0 = new Coordinates2D(1, 3);
    Coordinates lower0 = new Coordinates2D(4, 4);
    
    List<Item> items0 = new ArrayList<>();
    items0.add(weapon0);
    List<Space> neighbors0 = new ArrayList<>();
    
    Space space0 = new MansionSpace(0, "Garage", upper0, lower0, items0, neighbors0);
    
    Coordinates upper = new Coordinates2D(0, 0);
    Coordinates lower = new Coordinates2D(2, 2);
    
    List<Item> items = new ArrayList<>();
    items.add(weapon);
    List<Space> neighbors = new ArrayList<>();
    neighbors.add(space0);
    space = new MansionSpace(1, "Kitchen", upper, lower, items, neighbors);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeIndex() {
    Coordinates upper = new Coordinates2D(0, 0);
    Coordinates lower = new Coordinates2D(0, 2);
    
    new MansionSpace(-2, "Some room", upper, lower, new ArrayList<Item>(),
        new ArrayList<Space>());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testNullName() {
    Coordinates upper = new Coordinates2D(0, 0);
    Coordinates lower = new Coordinates2D(2, 2);
    
    new MansionSpace(1, null, upper, lower, new ArrayList<Item>(), new ArrayList<Space>());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testNullNeighbors() {
    Coordinates upper = new Coordinates2D(0, 0);
    Coordinates lower = new Coordinates2D(2, 2);
    
    new MansionSpace(1, "Some room", upper, lower, new ArrayList<Item>(), null);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testNullItemsList() {
    Coordinates upper = new Coordinates2D(0, 0);
    Coordinates lower = new Coordinates2D(2, 2);
    
    new MansionSpace(1, "Some room", upper, lower, null, new ArrayList<Space>());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testNullUpperCoordinate() {
    Coordinates lower = new Coordinates2D(2, 2);
    
    new MansionSpace(1, "Some room", null, lower, new ArrayList<Item>(),
        new ArrayList<Space>());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testNullLowerCoordinate() {
    Coordinates upper = new Coordinates2D(0, 0);
    
    new MansionSpace(1, "Some room", upper, null, new ArrayList<Item>(),
        new ArrayList<Space>());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testSameCoordinates() {
    Coordinates upper = new Coordinates2D(0, 0);
    Coordinates lower = new Coordinates2D(0, 0);
    
    new MansionSpace(1, "Some room", upper, lower, new ArrayList<Item>(),
        new ArrayList<Space>());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalLowerCoordainate() {
    Coordinates upper = new Coordinates2D(0, 0);
    Coordinates lower = new Coordinates2D(0, 2);
    
    new MansionSpace(1, "Some room", lower, upper, new ArrayList<Item>(),
        new ArrayList<Space>());
  }


  @Test
  public void testGetName() {
    assertEquals("Kitchen", space.getSpaceName());
  }
    
  @Test
  public void testtoString() {
    assertEquals("Room Kitchen @ (0, 0) (2, 2)", space.toString());
  }
  
  @Test
  public void testGetCoordinates() {
    
    List<Coordinates> coordinates = space.getCoordinates();
    
    assertEquals("(0, 0)", coordinates.get(0).toString());
    assertEquals("(2, 2)", coordinates.get(1).toString());
    
  }
  
  @Test
  public void testGetSpaceIndex() {

    assertEquals(1, space.getSpaceIndex());
    
  }
  
  @Test
  public void testGetSpaceName() {

    assertEquals("Kitchen", space.getSpaceName());
    
  }
  
  @Test
  public void testGetNeighbors() {
    
    List<Space> currentneighbors;
    currentneighbors = space.getNeighbours();
    
    assertEquals("Garage", currentneighbors.get(0).getSpaceName());
    
  }
  
  @Test
  public void testGetItems() {
    
    List<Item> currentitems;
    currentitems = space.getItems();
    
    assertEquals("Knife", currentitems.get(0).getName());
    
  }
  
  @Test
  public void testAddNeighbors() {
    
    List<Space> currentneighbors;
    currentneighbors = space.getNeighbours();
    
    // Currently it would only have one neighbor which was added in the setup stage
    assertEquals(1, currentneighbors.size()); 
    
    Coordinates upper = new Coordinates2D(0, 0);
    Coordinates lower = new Coordinates2D(2, 2);
    
    Space newSpace = new MansionSpace(2, "Some Room", upper, lower, 
        new ArrayList<Item>(), new ArrayList<Space>());
    
    space.addNeighbour(newSpace);
    currentneighbors = space.getNeighbours();
    
    assertEquals(2, currentneighbors.size()); 
    
  }
  
  @Test
  public void testAddItems() {
    
    List<Item> currentitems;
    currentitems = space.getItems();
    
    // Currently it would only have one item which was added in the setup stage
    assertEquals(1, currentitems.size()); 
    
    Item weapon0 = new Weapon(1, "Bat", 10, 2);
    
    space.addItem(weapon0);;
    
    currentitems = space.getItems();
    
    assertEquals(2, currentitems.size()); 
    
  }
  
  @Test
  public void testEquals() {
      
    assertTrue(space.equals(space));
    
    Item weapon = new Weapon(0, "Knife", 10, 2);
    Item weapon0 = new Weapon(1, "Bat", 10, 2);
    
    Coordinates upper0 = new Coordinates2D(1, 3);
    Coordinates lower0 = new Coordinates2D(4, 4);
    
    List<Item> items0 = new ArrayList<>();
    items0.add(weapon0);
    List<Space> neighbors0 = new ArrayList<>();
    
    Space space0 = new MansionSpace(0, "Garage", upper0, lower0, items0, neighbors0);
    
    List<Item> items = new ArrayList<>();
    items.add(weapon);
    List<Space> neighbors = new ArrayList<>();
    neighbors.add(space0);
    
    Coordinates upper = new Coordinates2D(0, 0);
    Coordinates lower = new Coordinates2D(2, 2);
    
    Space space2 = new MansionSpace(1, "Kitchen", upper, lower, items, neighbors);
    
    assertTrue(space.equals(space2));
    
    assertFalse(space.equals(new MansionSpace(1, "Some room", upper, lower,
        new ArrayList<Item>(), new ArrayList<Space>())));
  }
  
  @Test
  public void testHashCode() {
    
    Item weapon = new Weapon(0, "Knife", 10, 2);
    Item weapon0 = new Weapon(1, "Bat", 10, 2);
    
    Coordinates upper0 = new Coordinates2D(1, 3);
    Coordinates lower0 = new Coordinates2D(4, 4);
    
    List<Item> items0 = new ArrayList<>();
    items0.add(weapon0);
    List<Space> neighbors0 = new ArrayList<>();
    
    Space space0 = new MansionSpace(0, "Garage", upper0, lower0, items0, neighbors0);
    
    List<Item> items = new ArrayList<>();
    items.add(weapon);
    List<Space> neighbors = new ArrayList<>();
    neighbors.add(space0);
    
    Coordinates upper = new Coordinates2D(0, 0);
    Coordinates lower = new Coordinates2D(2, 2);
    
    Space space2 = new MansionSpace(1, "Kitchen", upper, lower, items, neighbors);
    
    assertTrue(space.hashCode() == space2.hashCode());
    
    assertFalse(space.hashCode() == new MansionSpace(1, "Some room", upper, lower,
        new ArrayList<Item>(), new ArrayList<Space>()).hashCode());
  }

}
