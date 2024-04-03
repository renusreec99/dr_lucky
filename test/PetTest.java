import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import game.Pet;
import org.junit.Before;
import org.junit.Test;


/**
 * Test class for testing Pet class.
 *
 */
public class PetTest {

  private Pet buddy;
  
  /**
   * Setting up the values for the tests.
   */
  @Before
  public void setup() {
    buddy = new Pet("Snoopy");
  }
  
  @Test
  public void testPet() {
    assertEquals("Pet: Snoopy", buddy.toString());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testNullPet() {
    new Pet(null);
  }

  @Test
  public void testGetName() {
   
    assertEquals("Snoopy", buddy.getName());
  }

  @Test
  public void testToString() {
    
    assertEquals("Pet: Snoopy", buddy.toString());
  }

  @Test
  public void testEquals() {
    assertTrue(buddy.equals(buddy));
    
    assertTrue(buddy.equals(new Pet("Snoopy")));
    
    assertFalse(buddy.equals(new Pet("Garfield")));
  }
  
  @Test
  public void testHashCode() {
    assertTrue(buddy.hashCode() == new Pet("Snoopy").hashCode());
    
    assertFalse(buddy.hashCode() == new Pet("Garfield").hashCode());
  }

}
