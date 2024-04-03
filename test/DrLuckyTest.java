import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import game.Character;
import game.DrLucky;
import game.Pet;
import org.junit.Before;
import org.junit.Test;


/**
 * Class for testing the character Dr Lucky.
 *
 */
public class DrLuckyTest {

  
  private Character drlucky;
  
  /**
   * Setting up the values for the tests.
   */
  @Before
  public void setup() {
    drlucky = new DrLucky("Dr Lucky", 50, new Pet("Sloppy"));
  }
  
  @Test
  public void testGetName() {
    assertEquals("Dr Lucky", drlucky.getName());
  }
  
  @Test
  public void testGetHealth() {
    assertEquals(50, drlucky.getHealth());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testZeroHealth() {
    new DrLucky("Dr Lucky", 0, new Pet("Sloppy"));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeHealth() {
    new DrLucky("Dr Lucky", -50, new Pet("Sloppy"));
  }
  
  @Test
  public void testToString() {
    assertEquals("Character: Dr Lucky , Health: 50", drlucky.toString());
  }
  
  @Test
  public void testHealthUpdate() {
    
    assertEquals(50, drlucky.getHealth());
    
    drlucky.healthUpdate(-10);
    
    assertEquals(40, drlucky.getHealth());
    
    drlucky.healthUpdate(-50);
    
    assertEquals(0, drlucky.getHealth());
    
    drlucky.healthUpdate(10);
    
    assertEquals(10, drlucky.getHealth());
  }
  
  @Test
  public void testEquals() {
    assertTrue(drlucky.equals(drlucky));
    
    assertTrue(drlucky.equals(new DrLucky("Dr Lucky", 50, new Pet("Sloppy"))));
    
    assertFalse(drlucky.equals(new DrLucky("Dr Unlucky", 50, new Pet("Sloppy"))));
  }
  
  @Test
  public void testHashCode() {
    assertTrue(drlucky.hashCode() == new DrLucky("Dr Lucky", 50, new Pet("Sloppy")).hashCode());
    
    assertFalse(drlucky.hashCode() == new DrLucky("Dr Unlucky", 50, new Pet("Sloppy")).hashCode());
  }

}
