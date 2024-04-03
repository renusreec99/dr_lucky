package game;


/**
 * Items are scattered throughout the world. Each item has an associated damage.
 * For now the only items are weapons
 *
 */
public interface Item {
  /**
   * This returns the damage of the item.
   * 
   * A positive value reduces the health, a negative value is said to heal the character.
   * Items which heal have a negative damage. Other items which do neither can have zero damage.
   *   
   * @return the damage done by the item as an integer
   */
  public int getDamage();
  
  /**
   * Get the room no in which this item is located. 
   * 
   * @return the index of the room in which the item is located
   */
  public int getRoomNo();
  
  /**
   * Get the name the item. 
   * 
   * @return the name of the item
   */
  public String getName();
  
}
