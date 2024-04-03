import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import game.Coordinates;
import game.Coordinates2D;
import org.junit.Before;
import org.junit.Test;


/**
 * Test class for testing Coordinates class.
 *
 */
public class Coordinates2dTest {
  
  private Coordinates two5;
  
  /**
   * Setting up the values for the tests.
   */
  @Before
  public void setup() {
    two5 = new Coordinates2D(2, 5);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeY() {
    new Coordinates2D(2, -5);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeX() {
    new Coordinates2D(-2, 5);
  }
  
  @Test
  public void testZeroX() {
    Coordinates zeroX =   new Coordinates2D(0, 1);
    assertEquals("(0, 1)", zeroX.toString());
  }
  
  @Test
  public void testZeroY() {
    Coordinates zeroY =   new Coordinates2D(1, 0);
    assertEquals("(1, 0)", zeroY.toString());
  }
  
  @Test
  public void testGetX() {
    assertEquals(2, two5.getX());
  }
  
  @Test
  public void testGetY() {
    assertEquals(5, two5.getY());
  }
  
  @Test
  public void testToString() {
    assertEquals("(2, 5)", two5.toString());
  }
  
  @Test
  public void testEquals() {
    assertTrue(two5.equals(two5));
    
    assertTrue(two5.equals(new Coordinates2D(2, 5)));
    
    assertFalse(two5.equals(new Coordinates2D(3, 3)));
  }
  
  @Test
  public void testHashCode() {
    assertTrue(two5.hashCode() == new Coordinates2D(2, 5).hashCode());
    
    assertFalse(two5.hashCode() == new Coordinates2D(2, 2).hashCode());
  }

}
