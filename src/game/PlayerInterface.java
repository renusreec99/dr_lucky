package game;


/**
 * 
 * The interface which defines all the characteristics that can be retrieved from a player.
 * 
 * The players in the world can be computer controlled or human controlled. 
 *
 */
public interface PlayerInterface {

  /**
   * Gets the name of the current player.
   * 
   * @return the String value of the Player
   */
  public String getName(); 
  
  /**
   * Gets the number of items that the inventory of the current player can hold.
   * 
   * @return the integer number of inventory size
   */
  public int getInventorySize(); 
  
  /**
   * Identifies whether a player is a computer controller player or not.
   * 
   * @return true if it is a computer player, false if it is not
   */
  public boolean isBot(); 

}
