import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import game.Item;
import game.Weapon;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the Weapons class.
 *
 */
public class WeaponTest {

  private Item knife;
  
  /**
   * Setting up the values for the tests.
   */
  @Before
  public void setUp() {
    knife = new Weapon(0, "Knife", 10, 2);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeRoomNo() {
    new Weapon(0, "Knife", 10, -2);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeDamage() {
    new Weapon(0, "Knife", -10, 2);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testZeroDamage() {
    new Weapon(0, "Knife", 0, 2);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testNullName() {
    new Weapon(0,  null, 10, 2);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeIndex() {
    new Weapon(-1, "Knife", 10, 2);
  }

  @Test
  public void testGetRoomNo() {
    assertEquals(2, knife.getRoomNo());
  }
  
  @Test
  public void testDamage() {
    assertEquals(10, knife.getDamage());
  }
  
  @Test
  public void testGetName() {
    assertEquals("Knife", knife.getName());
  }
  
  @Test
  public void testToString() {
    assertEquals("Knife - Damage : 10 Room : 2", knife.toString());
  }
  
  @Test
  public void testZeroRoomNo() {
    Item weapon = new Weapon(1, "Knife", 10, 0);
    assertEquals("Knife - Damage : 10 Room : 0", weapon.toString());  
  }
  
  @Test
  public void testEquals() {
    assertTrue(knife.equals(knife));
    
    assertTrue(knife.equals(new Weapon(0, "Knife", 10, 2)));
    
    assertFalse(knife.equals(new Weapon(0, " Big Knife", 10, 2)));
  }
  
  @Test
  public void testHashCode() {
    assertTrue(knife.hashCode() == new Weapon(0, "Knife", 10, 2).hashCode());
    
    assertFalse(knife.hashCode() == new Weapon(0, "Big Knife", 10, 2).hashCode());
  }

}
